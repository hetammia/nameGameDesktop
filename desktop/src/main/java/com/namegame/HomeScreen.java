package com.namegame;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;

/**
 * This class defines the first screen the user sees, aka the Home Screen.
 * It's primary functionalities are 
 * - providing the user with enough information to be able to use the application,
 * - setting up a new user's name queue, and
 * - retrieving a returning user's name queue.
 */

public class HomeScreen extends JPanel {

    JPanel panel = new JPanel();
    JLabel title = new JLabel();
    JButton startButton = new JButton("Start");
    JButton continueButton = new JButton("Continue"); 

    int [] nameQueue;

    public HomeScreen () {

        this.panel = this;
        
        setUpTitle();

        this.startButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(panel,
                        "Welcome! What is your name?", null);

                String [] optionsGender = {"Boy", "Girl", "Both"};
                int genderChoice = JOptionPane.showOptionDialog(panel, 
                            "For which gender are you looking for a name?", 
                            "Gender Preference", 
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, optionsGender, optionsGender[0]);

                String [] optionsBinary = {"Yes", "No"};
                int rarityChoice = JOptionPane.showOptionDialog(panel, 
                            "Do you want to filter out very uncommon names (10 or less people)?", 
                            "Rarity Preference",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, optionsBinary, optionsBinary[0]);
                
                

                NameList nameList = new NameList(name, genderChoice, rarityChoice);
            }
        });

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 2;

        this.add(this.title, c);

        c.insets = new Insets(10,10,0,10);
        c.weightx = 1.0;

        c.gridy = 1;
        //c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;

        this.add(startButton, c);

        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        this.add(continueButton, c);
    }

    private void setUpTitle() {
        this.title = new JLabel("Welcome to NameGame");
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setHorizontalAlignment(JLabel.CENTER);

        return;
    }
    
    private void setUpNewUser() {

    }
}
