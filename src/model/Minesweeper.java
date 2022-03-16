package model;

import java.util.ArrayList;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper{
    AbstractTile[][] field;
    int width;
    int height;
    int nrRemainingMines;
    int timeElapsed;

    public Minesweeper(){
        width = 0;
        height = 0;
        nrRemainingMines = 0;
        timeElapsed = 0;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void startNewGame(Difficulty level) {
        switch(level){
            case EASY:
                startNewGame(8,8,10);
                break;
            case MEDIUM:
                startNewGame(16,16,40);
                break;
            case HARD:
                startNewGame(16,30,99);
                break;
        }
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        field = new AbstractTile[row][col]; //creates 2D matrix of size row x col
        nrRemainingMines = explosionCount;
        width = col;
        height = row;
        setWorld(field);
        // place mines in the field and compute how many neighboring mines normal tiles have
        int nrMines = nrRemainingMines;
        Random randX = new Random();
        Random randY = new Random();
        int x,y;
        while (nrMines > 0){
            x = randX.nextInt(width);
            y = randY.nextInt(height);
            while(field[y][x].isExplosive()){
                x = randX.nextInt(width);
                y = randY.nextInt(height);
            }
            AbstractTile mineTile = getTile(x,y);
            mineTile.setExplosive();
            nrMines--;
        }
        viewNotifier.notifyNewGame(row,col);
    }

    @Override
    public void toggleFlag(int x, int y) {
        AbstractTile tile = getTile(x, y);
        boolean flag = tile.isFlagged();
        if (flag){
            tile.unflag();
        } else{
            tile.flag();
        }
    }

    @Override
    public AbstractTile getTile(int x, int y) {
        if (x < 0) {
            x = 0;
        } else if (x >= width){
            x = width - 1;
        }
        if (y < 0){
            y = 0;
        } else if (y >= height){
            y = height - 1;
        }

        AbstractTile foundTile = field[y][x];
        return foundTile;
    }

    @Override
    public void setWorld(AbstractTile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[j][i] = new Tile(false);
            }
        }
    }

    @Override
    public void open(int x, int y) {
        AbstractTile tile = getTile(x,y);
        tile.open();
    }

    @Override
    public void flag(int x, int y) {
        AbstractTile tile = getTile(x,y);
        tile.flag();
    }

    @Override
    public void unflag(int x, int y) {
        AbstractTile tile = getTile(x,y);
        tile.unflag();
    }

    @Override
    public void deactivateFirstTileRule() {


    }

    @Override
    public AbstractTile generateEmptyTile() {
        AbstractTile tile = new Tile(false);
        return tile;
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        AbstractTile tile = new explosiveTile();
        return tile;
    }
}
