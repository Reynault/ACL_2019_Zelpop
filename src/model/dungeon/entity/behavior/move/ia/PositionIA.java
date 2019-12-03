package model.dungeon.entity.behavior.move.ia;

import model.dungeon.tile.Tile;
import model.global.Cmd;
import model.global.Position;

import java.io.Serializable;
import java.util.ArrayList;

public class PositionIA implements Serializable {

    private Position pos;
    private int f;
    private int g;
    private int h;
    private ArrayList<PositionIA> nextVoisinList;
    private PositionIA previousVoisin;
    private Boolean nromalTile ;

    public PositionIA(Position pos) {
        this.pos = pos;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        nextVoisinList = new ArrayList<>();
        previousVoisin = null;
        nromalTile = true;
    }

    public PositionIA(Position pos, Boolean nromalTile) {
        this(pos);
        this.nromalTile = nromalTile;
    }

    public Position getPos() {
        return pos;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void addVoisins(int l , int c, Tile[][] mazeTile){
        nextVoisinList.clear();
        if (this.getPos().getX() > 0 ){
            if (!mazeTile[this.getPos().getY()][this.getPos().getX() - 1].canBeCrossed())
            nextVoisinList.add(new PositionIA(
                    new Position(this.getPos().getX() - 1, this.getPos().getY(), Cmd.IDLE)
                    , false));
            else
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX() - 1, this.getPos().getY(), Cmd.IDLE)));
        }
        if (this.getPos().getX() < c - 1){
            if (!mazeTile[this.getPos().getY()][this.getPos().getX() + 1].canBeCrossed())
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX() + 1, this.getPos().getY(), Cmd.IDLE)
                        ,false));
            else
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX() + 1, this.getPos().getY(), Cmd.IDLE)));
        }
        if (this.getPos().getY() < l - 1){
            if (!mazeTile[this.getPos().getY()+1][this.getPos().getX()].canBeCrossed())
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX(), this.getPos().getY() + 1, Cmd.IDLE)
                        ,false));
            else
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX(), this.getPos().getY() + 1, Cmd.IDLE)));
        }
        if (this.getPos().getY() > 0){
            if (!mazeTile[this.getPos().getY() - 1][this.getPos().getX()].canBeCrossed())
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX(), this.getPos().getY() - 1, Cmd.IDLE)
                        ,false));
            else
                nextVoisinList.add(new PositionIA(
                        new Position(this.getPos().getX(), this.getPos().getY() - 1, Cmd.IDLE)));
        }
    }

    public ArrayList<PositionIA> getVoisinList() {
        return nextVoisinList;
    }

    public PositionIA getPreviousVoisin() {
        return previousVoisin;
    }

    public void setPreviousVoisin(PositionIA previousVoisin) {
        this.previousVoisin = previousVoisin;
    }

    public Boolean getNromalTile() {
        return nromalTile;
    }

    public void setNromalTile(Boolean nromalTile) {
        this.nromalTile = nromalTile;
    }
}
