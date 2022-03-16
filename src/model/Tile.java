package model;

public class Tile extends AbstractTile{
    boolean flagged;
    boolean opened;
    boolean mine;
    int nrNeighboringMines;

    public Tile(boolean newMine){
        flagged = false;
        opened = false;
        mine = newMine;
    }

    @Override
    public boolean open() {
        opened = true;
        if (mine){
            System.out.println("Stepped on a mine");
            return false;// return false if we stepped on a mine
        }
        return true;// return true if we did not step on a mine
    }

    @Override
    public void setExplosive() {
        mine = true;
    }


    @Override
    public void flag() {
        flagged = true;
    }

    @Override
    public void unflag() {
        flagged = false;
    }

    @Override
    public boolean isFlagged() {
        return flagged;
    }

    @Override
    public boolean isExplosive() {
        return mine;
    }

    @Override
    public boolean isOpened() {
        return opened;
    }
}
