package com.snake.gierka.enity;

import java.awt.*;

public class Bodysnake {
    private int xCoor,yCoor,width,height;
    public Bodysnake(int xCoor, int yCoor,int tileSize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    public void tick(){

    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(xCoor*width,yCoor*height,width,height);
        g.setColor(Color.ORANGE);
        g.fillRect(xCoor*width+2,yCoor*height+2,width-2,height-2);
    }
}
