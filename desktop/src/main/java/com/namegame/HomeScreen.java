package com.namegame;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
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
    JPanel basePanel = new JPanel();
    JLabel title = new JLabel();
    JButton startButton = new JButton("New Game");
    JButton continueButton = new JButton("Continue"); 

    NameList nameList;

    public HomeScreen (JPanel basePanel) {

        this.panel = this;
        this.basePanel = basePanel;
        
        setUpTitle();

        // START BUTTON
        this.startButton.addActionListener(new ActionListener() {
            
            // TODO: ADD POSSIBILITY TO CANCEL
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(panel,
                        "Welcome! What is your name?", null);

                String [] optionsGender = {"Boy", "Girl", "Both"};
                int genderChoice = JOptionPane.showOptionDialog(panel, 
                            "For which gender are you looking for a name?", 
                            "Gender Preference", 
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, optionsGender, optionsGender[2]);

                String [] optionsBinary = {"Yes", "No"};
                int rarityChoice = JOptionPane.showOptionDialog(panel, 
                            "Do you want to filter out very uncommon names (10 or less people)?", 
                            "Rarity Preference",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, optionsBinary, optionsBinary[1]);
                
                nameList = new NameList(name, genderChoice, rarityChoice);
                
            }
        });
        

        // CONTINUE BUTTON

        this.continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    nameList = new NameList("namelist.json");

                    String msg = ("Continue as " + nameList.getUsername() + "?");
                    String [] optionsBinary = {"Yes", "No"};
                    int continueChoice = JOptionPane.showOptionDialog(panel, 
                                msg, "Continue?", 
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, optionsBinary, optionsBinary[0]);

                    if (continueChoice == 0) {
                        System.out.println("Continuing");
                        //this.basePanel = (JPanel) panel.getParent();
                        CardLayout cl = (CardLayout) basePanel.getLayout();
                        NameScreen nameScreen = new NameScreen(nameList, basePanel);
                        basePanel.add(nameScreen, "nameScreen");
                        cl.show(basePanel, "nameScreen");
                        

                    }
                } catch (IOException notFoundE) {
                    System.out.println("No name list initialised");
                    JOptionPane.showMessageDialog(panel, "Progress not found.");
                }   
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
    
}
