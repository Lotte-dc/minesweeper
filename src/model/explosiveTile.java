package model;

public class explosiveTile extends Tile{

    public explosiveTile() {
        super(true);
    }

    @Override
    public boolean open(){
        System.out.println("Stepped on a mine");
        return false;
    }
}
