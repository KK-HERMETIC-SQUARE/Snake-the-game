package com.snake.gierka;
import javax.swing.JFrame;
import java.awt.*;

import com.snake.gierka.graphics.Screen;

public class frame extends JFrame {
    public void initialization(){
        setLayout(new GridLayout(1,1,0,0));
        Screen s = new Screen();
        add(s);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake the game");
        setResizable(false);
        initialization();
    }
    public static void main(String[] args){
        new frame();
    }
}
