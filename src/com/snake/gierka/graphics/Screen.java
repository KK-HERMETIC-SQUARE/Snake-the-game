package com.snake.gierka.graphics;
import com.snake.gierka.enity.Bodysnake;
import com.snake.gierka.enity.Points;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel implements Runnable {
    public static final int WIDTH = 800, HEIGHT = 800;
    private Thread thread;
    private boolean running = false;
    private Bodysnake b;
    private ArrayList<Bodysnake> snake;
    private Points points;
    private ArrayList<Points> pointsS;
    private Random r;
    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    private boolean right=true,left=false,up=false,down=false;
    private int ticks=0;
    private Key key;
    public Screen() {
        setFocusable(true);
        key = new Key();
        addKeyListener(key);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        r = new Random();
        snake = new ArrayList<Bodysnake>();
        pointsS = new ArrayList<Points>();
        start();
    }
    public void tick(){
        if(snake.size()==0){
            b = new Bodysnake(xCoor,yCoor,10);
            snake.add(b);}
            if(pointsS.size()==0){
                int xCoor = r.nextInt(79);
                int yCoor = r.nextInt(79);
                points = new Points(xCoor,yCoor,10);
                pointsS.add(points);
            }
            int i;
            for(i = 0; i<pointsS.size(); i++)
                if (xCoor == pointsS.get(i).getxCoor() && yCoor == pointsS.get(i).getyCoor()) {
                    size++;
                    pointsS.remove(i);
                    i--;
                }

        ticks++;
        if(ticks>700000){
            if(right)xCoor++;
            if(left)xCoor--;
            if(up)yCoor--;
            if(down)yCoor++;
            ticks =0;
            b = new Bodysnake(xCoor,yCoor,10);
            snake.add(b);
            if(snake.size()>size){
                snake.remove(0);
            }
        }

    }
    public void paint(Graphics g){
        g.clearRect(0,0,WIDTH,HEIGHT);
        for(int i=0; i<WIDTH / 10;i++){
            g.drawLine(i*10, 0,i*10,HEIGHT);
        }
        for(int i=0;i<HEIGHT/10; i++){
            g.drawLine(0,i*10,WIDTH,i*10);
        }
        for(int i=0;i<snake.size();i++){
            snake.get(i).draw(g);
        }
        for(int i=0;i<pointsS.size();i++){
            pointsS.get(i).draw(g);
            }
    }
    public void start(){
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
    }
    public void stop(){

    }
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }
    private class Key implements KeyListener{


        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
                    if(key == KeyEvent.VK_RIGHT&&!left){
                        up=false;
                        down=false;
                        right=true;
                    }
            if(key == KeyEvent.VK_LEFT&&!right){
                up=false;
                down=false;
                left=true;
            }
            if(key == KeyEvent.VK_UP&&!down){
                right=false;
                left=false;
                up=true;
            }
            if(key == KeyEvent.VK_DOWN&&!up){
                right=false;
                left=false;
                down=true;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}
