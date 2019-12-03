package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.behavior.move.ia.PositionIA;
import model.dungeon.tile.Tile;
import model.global.Cmd;
import model.global.Position;

import java.util.ArrayList;
import java.util.Stack;

public class MoveGobelin implements Move {


    private ArrayList<PositionIA> checkedPositions;
    private ArrayList<PositionIA> unCheckedPositions;
    private Position startPosition;
    private Position endPosition;
    private PositionIA[][] matrix;
    private int mazeWidth;
    private int mazeHeight;
    private Tile[][] mazeTile;
    private Cmd movement = Cmd.IDLE;

    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {

        // creating matrix for postion calcuation
        // adding positions neighbours
        // adding obstacles
        mazeWidth = maze.getWidth();
        mazeHeight = maze.getHeight();
        mazeTile = maze.getTiles();
        matrix = new PositionIA[mazeWidth][mazeHeight];
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                matrix[i][j] = new PositionIA(new Position(j, i, Cmd.IDLE));
                matrix[i][j].addVoisins(mazeHeight, mazeWidth, mazeTile);
                if (!mazeTile[i][j].canBeCrossed()) {
                    matrix[i][j].setNromalTile(false);
                }
            }
        }


        // setting the start pos == gobelin pos
        startPosition = matrix[entity.getPosition().getY()][entity.getPosition().getX()].getPos();
        // getting hero postion
        // setting the end pos == hero pos
        endPosition = maze.getHero().getPosition();

        // checked pos = position that aleardy has been tested
        // to reach monster pos
        checkedPositions = new ArrayList<>();
        // unchecked pos
        unCheckedPositions = new ArrayList<>();
        unCheckedPositions.add(matrix[entity.getPosition().getY()][entity.getPosition().getX()]);

        Position pos = a_Star();

        // setting Goeblin new position
        // if he is facing the new pos then add the new pos
        // else turn it to the same pos
        if (pos != null) {
            pos = finalPos(entity, pos, endPosition );
            this.movement = pos.getCmd();
            System.out.println(pos.getCmd());
        } else {
            System.out.println("calculted");
            faceDirection();
            entity.setPosition(new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    this.movement
            ));
        }

        return this.movement;
    }


    /**
     * @return
     */
    public Position a_Star() {
        // condition
        while (this.unCheckedPositions.size() > 0) {

            // continue looking for solution
            int winner = 0;
            for (int i = 0; i < this.unCheckedPositions.size(); i++) {
                //if we found a lowest way we take it
                if (this.unCheckedPositions.get(i).getF() < this.unCheckedPositions.get(winner).getF()) {
                    winner = i;
                }
            }

            PositionIA currentPos = this.unCheckedPositions.get(winner);

            if (currentPos.getPos().getX() == endPosition.getX()
                    && currentPos.getPos().getY() == endPosition.getY()) {
//                System.out.println("Algo finished");
                PositionIA tempP = currentPos;
                Stack<PositionIA> pilePath = new Stack();
                while (tempP.getPreviousVoisin() != null) {
                    pilePath.push(tempP.getPreviousVoisin());
                    tempP = tempP.getPreviousVoisin();
                }
                if (pilePath.size() == 1)
                    return pilePath.peek().getPos();
                else if (pilePath.size() > 1)
                    return pilePath.get(pilePath.size() - 2).getPos();
            }

            // if the current it is not the end then we continue
            // we remove it from unchecked pos
            // then we add it to checked pos

            this.unCheckedPositions.remove(winner);
            this.checkedPositions.add(currentPos);

            //adding voisis of current to unchecked pos
            ArrayList<PositionIA> voisinlist = getVoisins(currentPos);

            for (int i = 0; i < voisinlist.size(); i++) {
                PositionIA p = voisinlist.get(i);
                //  System.out.println(p.getNromalTile() + " / "+ p.getPos().getX() + " / " + p.getPos().getY());
                if ((!this.checkedPositions.contains(p)) && (p.getNromalTile())) {
                    //System.out.println("************");
                    int tempG = currentPos.getG() + 1;
                    if (this.unCheckedPositions.contains(p)) {
                        //   System.out.println("************");
                        if (tempG < p.getG()) {
                            p.setG(tempG);
                        }
                    } else {
                        p.setG(tempG);
                        this.unCheckedPositions.add(p);
                    }

                    p.setH(heuristique(p));
                    p.setF(p.getG() + p.getH());
                    p.setPreviousVoisin(currentPos);
                }
            }
        }
        return null;
    }

    /**
     * en utilisant la distance de Manhattan
     * pour calculer la distance
     *
     * @param p
     * @return
     */
    public int heuristique(PositionIA p) {
        int distance = Math.abs(endPosition.getX() - p.getPos().getX())
                + Math.abs(endPosition.getY() - p.getPos().getY());
        return distance;
    }

    public ArrayList getVoisins(PositionIA pos) {
        return matrix[pos.getPos().getY()][pos.getPos().getX()].getVoisinList();
    }

    public Position finalPos(Entity entity, Position pos, Position heroP) {
        // if the new position is to right then go to right
        if (entity.getPosition().getX() + 1 == pos.getX()) {
            return new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    Cmd.RIGHT
            );
        } else if (entity.getPosition().getX() - 1 == pos.getX()) {
            // if the new position is to the left then go to left
            return new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    Cmd.LEFT
            );
        } else if (entity.getPosition().getY() - 1 == pos.getY()) {
            // if the new position is the Up then go Up
            return new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    Cmd.UP
            );
        } else if (entity.getPosition().getY() + 1 == pos.getY()){
            // if the new position is to down then go to down
            return new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    Cmd.DOWN
            );
        }else {
            // if gobelion is aleardy in front on the hero
            // then just face hero
            Cmd c = finalFaceDirection(entity , heroP);
            return new Position(
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    c
            );
        }
    }

    public Cmd finalFaceDirection (Entity entity, Position p){

        if (entity.getPosition().getX() + 1 == p.getX()) {
            return Cmd.RIGHT;
        }
        else if (entity.getPosition().getX() - 1 == p.getX()) {
            return Cmd.LEFT;
        }
        else if (entity.getPosition().getY() - 1 == p.getY()) {
            return Cmd.UP;
        }
        else {
            return Cmd.DOWN;
        }
    }


    public void faceDirection() {
        int rand = (int) (Math.round(Math.random() * 4));
        switch (rand) {
            case 1:
                this.movement = Cmd.LEFT;
                break;
            case 2:
                this.movement = Cmd.UP;
                break;
            case 3:
                this.movement = Cmd.RIGHT;
                break;
            case 4:
                this.movement = Cmd.DOWN;
                break;
            default:
                this.movement = Cmd.IDLE;
                break;
        }
    }
}
