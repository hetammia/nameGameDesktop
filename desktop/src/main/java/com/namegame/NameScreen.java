package com.namegame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NameScreen extends JPanel{

    NameList nameList;
    JPanel panel = new JPanel();
    JPanel basePanel = new JPanel();

    // for containing the header, name, & info
    JPanel contentPanel = new JPanel();

    JPanel headerPanel = new JPanel();
    JPanel namePanel = new JPanel();
    JLabel name = new JLabel();
    JPanel infoPanel = new JPanel();
    JLabel infoData = new JLabel();

    // button panel
    JPanel buttonPanel = new JPanel();
    JButton backButton = new JButton("previous");
    JButton dislikeButton = new JButton("dislike");
    JButton likeButton = new JButton("like");
    JButton saveButton = new JButton("save");

    public NameScreen(NameList nameList, JPanel basePanel) {
        this.nameList = nameList;
        this.basePanel = basePanel;

        this.setLayout(new GridBagLayout());
        
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        setUpBackground(0.5);
        setUpContentPanel();
        setUpButtonPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        this.add(panel, gbc);
        
    }

    private void setUpButtonPanel() {
        // TODO: add functionality to back button
        this.buttonPanel.add(this.backButton);
        this.buttonPanel.add(this.dislikeButton);
        setDislikeAction();
        this.buttonPanel.add(this.likeButton);
        setLikedAction();
        this.buttonPanel.add(this.saveButton);
        setSaveAction();

        this.buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        this.panel.add(buttonPanel);
    }

    private void setDislikeAction () {
        this.dislikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchNextName(nameList.getCurrent());
            }
        });
    }

    private void setLikedAction() {
        this.likeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameList.switchLiked(nameList.getCurrent());
                switchNextName(nameList.getCurrent());
            }
        });
    }

    private void setSaveAction() {
        this.saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameList.getLikedNames();

                nameList.updateJSONList();
                // pop up with continue or show list
                String [] options = {"Continue", "View Liked Names"};
                int saveChoice = JOptionPane.showOptionDialog(contentPanel,
                            "Saved! Do you want to keep swiping?",
                            "Saving progress...",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0]);
                
                if (saveChoice == 1) {
                    // switch to the list view
                    System.out.println("Switching to the list");
                    CardLayout cl = (CardLayout) basePanel.getLayout();
                    ListScreen listScreen = new ListScreen(nameList);
                    basePanel.add(listScreen, "listScreen");
                    cl.show(basePanel, "listScreen");

                }
            }
        });
    }

    public void switchNextName(int current) {
        Name newName = nameList.getNextName();
        this.name.setText(newName.name);
        this.infoData.setText(formInfoString(newName));
        this.setUpBackground(newName.getGenderRatio());
    }

    private void setUpBackground(double genderRatio){

        int girlRed = 227;
        int girlGreen = 109;
        int girlBlue = 109;

        int boyRed = 109;
        int boyGreen = 197;
        int boyBlue = 227;


        int redChange = (int)((boyRed-girlRed) * genderRatio);
        int greenChange = (int)((boyGreen-girlGreen) * genderRatio);
        int blueChange = (int)((boyBlue-girlBlue) * genderRatio);

        Color anypoint = new Color(girlRed+redChange, girlGreen+greenChange, girlBlue+blueChange);

        this.setBackground(anypoint);

    }

    private void setUpContentPanel(){
        // TODO: change so that it will immediately display a name instead.
        // if starting from the beginning, move from -1, if continuing, get current.

        JLabel headerText = new JLabel("What do you think of ");
        this.headerPanel.add(headerText);

        this.name = new JLabel("[Name]");
        name.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        this.namePanel.add(name);

        this.infoData = new JLabel("[Info]");
        infoData.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        this.infoPanel.add(infoData);

        this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));

        this.contentPanel.add(headerPanel);
        this.contentPanel.add(namePanel);
        this.contentPanel.add(infoPanel);

        this.contentPanel.setBorder(new EmptyBorder(50, 0, 20, 0));
        this.panel.add(contentPanel);
    }

    private String formInfoString(Name name) {

        String gender;
        String firstRatio;

        if (name.getGenderRatio() < 0.25) {
            gender = "Most people with this name are <b>women</b>. <br>";
        } else if (name.getGenderRatio() > 0.75) {
            gender = "Most people with this name are <b>men</b>. <br>";
        } else {
            gender = "This name is quite <b>unisex</b>. <br>";
        }

        if (name.getFirstNameRatio() < 0.33) {
            firstRatio = "It is commonly a <b>middle name</b>.";
        } else if (name.getFirstNameRatio() > 0.66){
            firstRatio = "It is commonly a <b>first name</b>.";
        } else {
            firstRatio = "It is used as <b>both first and middle name</b>.";
        }
        
        return "<html><div style='text-align:center'>" + gender + firstRatio + "</div></html>";
    }
}
