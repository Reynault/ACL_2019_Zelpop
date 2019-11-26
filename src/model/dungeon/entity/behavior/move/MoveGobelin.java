package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.behavior.move.ia.PositionIA;
import model.dungeon.tile.Tile;
import model.global.Cmd;
import model.global.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MoveGobelin implements Move {


    private ArrayList<PositionIA> checkedPositions ;
    private ArrayList<PositionIA> unCheckedPositions ;
    private Position startPosition;
    private Position endPosition;
    private ArrayList<PositionIA> colonnes;
    private ArrayList< ArrayList<PositionIA>> lignes ;
    private PositionIA [][] matrix;
    private int mazeWidth;
    private int mazeHeight;
    private Tile[][] mazeTile;

    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {

        System.out.println("***********************************************");
        // creating matrix for calcuation
        mazeWidth = maze.getWidth();
        mazeHeight =  maze.getHeight();
        mazeTile = maze.getTiles();
        matrix = new PositionIA[mazeWidth][mazeHeight];
        for (int i = 0 ; i < mazeHeight ; i++ ){
            for (int j = 0 ; j < mazeWidth ; j++ ){
                matrix[i][j] = new PositionIA(new Position(j , i , Cmd.IDLE));
                matrix[i][j].addVoisins(mazeHeight , mazeWidth, mazeTile);
                //System.out.println(mazeTile[i][j].canBeCrossed() + " / "+ j + " / " + i);
                if (!mazeTile[i][j].canBeCrossed()){
                    //System.out.println(mazeTile[i][j].canBeCrossed() + " / "+ j + " / " + i);
                    matrix[i][j].setNromalTile(false);
                }
            }
        }

        for (int i = 0 ; i < mazeHeight ; i ++){
            for (int j = 0 ; j < mazeWidth ; j++){
                System.out.println( matrix[i][j].getNromalTile() + " / ");
            }
        }
        // adding voisins
        // adding obstacles
//        lignes = new ArrayList();
//       for (int i = 0 ; i < mazeHeight ; i++ ){
//           colonnes = new ArrayList<>();
//           for (int j = 0 ; j < mazeWidth ; j++ ){
//               colonnes.add(new PositionIA(new Position(j , i , Cmd.IDLE)));
//               colonnes.get(j).addVoisins(mazeHeight , mazeWidth, mazeTile);
//               if (!mazeTile[i][j].canBeCrossed()){
//                   //System.out.println(mazeTile[i][j].canBeCrossed() + " / "+ j + " / " + i);
//                   colonnes.get(j).setNromalTile(false);
//               }
//           }
//           lignes.add(colonnes);
//       }

        Cmd movement = null;

        // seting the start pos == gobelin pos
        startPosition = matrix[entity.getPosition().getY()][entity.getPosition().getX()].getPos();
        // getting hero postion
        // setting the end pos == hero pos
        endPosition = maze.getHero().getPosition();

        // cheked pos
        checkedPositions = new ArrayList<>();
        // inchecked pos
        unCheckedPositions = new ArrayList<>();
        unCheckedPositions.add(matrix[entity.getPosition().getY()][entity.getPosition().getX()]);

        Position pos = a_Star();
        System.out.println(pos.getX() + " / " + pos.getY());
        // setting Goeblin new position
        // if he is facing the new pos then add the new pos
        // else turn it to the same pos
        pos = finalPos(entity , pos);
        entity.setPosition(pos);

        movement = Cmd.IDLE;
        System.out.println("***********************************************");

        return movement ;
    }


    /**
     *
     * @return
     */
    public Position a_Star(){
        // condition
        while(this.unCheckedPositions.size() > 0){

            // continue lookinf for solution
            int winner = 0;
            for (int i = 0 ; i < this.unCheckedPositions.size(); i++){
                //if we found a lowest way we take it
                if (this.unCheckedPositions.get(i).getF() < this.unCheckedPositions.get(winner).getF()){
                    winner = i ;
                }
            }

            PositionIA currentPos = this.unCheckedPositions.get(winner);

            if (currentPos.getPos().getX() == endPosition.getX()
                    && currentPos.getPos().getY() == endPosition.getY()){
                System.out.println("Algo finished");
                PositionIA tempP = currentPos;
                Stack<PositionIA> pilePath = new Stack();
                while(tempP.getPreviousVoisin() != null){
                    pilePath.push(tempP.getPreviousVoisin());
                    tempP = tempP.getPreviousVoisin();
                }
                if (pilePath.size() == 1)
                    return pilePath.peek().getPos();
                else if(pilePath.size() > 1)
                    return pilePath.get(pilePath.size()-2).getPos();
            }

            // if the current it is not the end then we continue
            // we remove it from unchecked pos
            // then we add it to checked pos

            this.unCheckedPositions.remove(winner);
            this.checkedPositions.add(currentPos);

            //adding voisis of current to unchecked pos

            ArrayList<PositionIA> voisinlist =  getVoisins(currentPos);;

            for (int i = 0; i < voisinlist.size(); i++){
                PositionIA p = voisinlist.get(i);
                System.out.println(p.getNromalTile() + " / "+ p.getPos().getX() + " / " + p.getPos().getY());
                //      System.out.println(p.getNromalTile());
                if ((!this.checkedPositions.contains(p)) && (p.getNromalTile())){
                    System.out.println("************");
                    int tempG = currentPos.getG() + 1 ;
                    if (this.unCheckedPositions.contains(p)){
                        System.out.println("************");
                        if (tempG < p.getG()){
                            p.setG(tempG);
                        }
                    }else {
                        p.setG(tempG);
                        this.unCheckedPositions.add(p);
                    }

                    p.setH(heuristique(p));
                    p.setF(p.getG() + p.getH());
                    p.setPreviousVoisin(currentPos);
                }
            }
            System.out.println("//////////////");
           // System.exit(0);
        }

        System.out.println("hate");
        return null;
    }

    /**
     * en utilisant la distance de Manhattan
     * pour calculer la distance
     * @param p
     * @return
     */
    public int heuristique( PositionIA p){

        int distance = Math.abs(endPosition.getX() - p.getPos().getX())
                       +  Math.abs(endPosition.getY() - p.getPos().getY());

        return distance;
    }


    public ArrayList getVoisins(PositionIA pos){
        return matrix[pos.getPos().getY()][pos.getPos().getX()].getVoisinList();
    }

    public Position finalPos(Entity entity , Position pos ){
        Cmd c ;
        System.out.println(pos.getX());
        if (entity.getPosition().getX() + 1 == pos.getX()){

                return new Position(
                        entity.getPosition().getX(),
                        entity.getPosition().getY(),
                        Cmd.RIGHT
                );
        }else if(entity.getPosition().getX() - 1 == pos.getX()){

                System.out.println("--------------------------");
                return new Position(
                        pos.getX(),
                        pos.getY(),
                        Cmd.LEFT
                );

        }else if (entity.getPosition().getY() - 1 == pos.getY()){

                System.out.println("--------------------------");
                return new Position(
                        pos.getX(),
                        pos.getY(),
                        Cmd.UP
                );

        }else {

                System.out.println("--------------------------");
                return new Position(
                        pos.getX(),
                        pos.getY(),
                        Cmd.DOWN
                );

        }

    }

    public Cmd faceDirection (Entity g , Entity h){
        if(g.getPosition().getX()+ 1 == h.getPosition().getX())
            return Cmd.RIGHT;
        else if (g.getPosition().getX() - 1 == h.getPosition().getX())
            return Cmd.LEFT;
        else if (g.getPosition().getY() + 1 == h.getPosition().getY())
            return Cmd.UP;
        else
            return Cmd.DOWN;

    }


}
