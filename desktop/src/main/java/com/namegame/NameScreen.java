package com.namegame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NameScreen extends JPanel{

    NameList nameList;
    JPanel panel = new JPanel();

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

    public NameScreen(NameList nameList) {
        this.nameList = nameList;
        System.out.println(nameList);

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
        this.buttonPanel.add(this.backButton);
        this.buttonPanel.add(this.dislikeButton);
        this.buttonPanel.add(this.likeButton);
        this.buttonPanel.add(this.saveButton);

        this.buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        this.panel.add(buttonPanel);
    }

    public void switchNextName(int current) {
        
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
        JLabel headerText = new JLabel("What do you think of ");
        this.headerPanel.add(headerText);

        this.name = new JLabel(nameList.getNextName().name);
        this.namePanel.add(name);

        this.infoData = new JLabel("[Info]");
        this.infoPanel.add(infoData);

        this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));

        this.contentPanel.add(headerPanel);
        this.contentPanel.add(namePanel);
        this.contentPanel.add(infoPanel);

        this.contentPanel.setBorder(new EmptyBorder(50, 50, 20, 50));
        this.panel.add(contentPanel);
    }
}
