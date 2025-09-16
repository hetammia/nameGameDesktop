package com.namegame;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Font;

public class NameGUI {

    public NameGUI(){
        JFrame frame = new JFrame("NameGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        /*
        JPanel buttonPanel = new JPanel();
                
        JButton dislike = new JButton("Dislike");
        //dislike.setBounds(100, 50, 80, 50);
        buttonPanel.add(dislike);

        JButton unsure = new JButton("Unsure");
        //unsure.setBounds(200, 50, 80, 50);
        buttonPanel.add(unsure);

        JButton like = new JButton("Like it!");
        //like.setBounds(300, 50, 80, 50);
        buttonPanel.add(like);
        
        frame.add(buttonPanel);
        */
        JPanel basePanel = new JPanel(new CardLayout());

        basePanel.add(new HomeScreen(), "home");
        //HomeScreen home = new HomeScreen();
        
        frame.setSize(600,480);
        frame.add(basePanel);
        
        //frame.setLayout(null);
        
        frame.setVisible(true);
    }
    public static void main(String[] args){
        new NameGUI();
    }
}