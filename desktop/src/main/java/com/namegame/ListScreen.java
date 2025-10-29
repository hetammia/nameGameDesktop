package com.namegame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListScreen extends JPanel {

    NameList nameList;
    JPanel panel = new JPanel();
    
    public ListScreen(NameList nameList) {
        this.nameList = nameList;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel header = new JLabel("<html><b>So far You have liked:</b></html>", javax.swing.SwingConstants.CENTER);
        header.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        header.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, header.getPreferredSize().height));

        addLikedNames();

        JButton exportButton = new JButton("Export list");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameList.exportLikedList();
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
        this.add(header, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(exportButton, BorderLayout.SOUTH);
        
    }

    public void addLikedNames() {
        ArrayList<Name> likedNames = nameList.getLikedNames();

        for (Name name : likedNames) {
            JLabel nameLabel = formatNameLabel(name.name);
            this.panel.add(nameLabel);
        }
    }


    public JLabel formatNameLabel (String name) {
        JLabel nameLabel = new JLabel(name, javax.swing.SwingConstants.CENTER);
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nameLabel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, nameLabel.getPreferredSize().height));
        return nameLabel;
    }
}
