/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.colourpicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author casa
 */
public class Colour extends JFrame {
    
    private JPanel colourPanel; 
    private JTextField colourCodeField; 
    
    public Colour(){
        setTitle("Colour"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(400, 300); 
        setLayout(new BorderLayout()); 
        
        colourPanel = new JPanel(); 
        colourPanel.setBackground(Color.WHITE); 
        
        colourCodeField = new JTextField("#FFFFFF");
        colourCodeField.setHorizontalAlignment(JTextField.CENTER);
        colourCodeField.setBackground(Color.WHITE); 
        
        colourCodeField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                applyColour(colourCodeField.getText()); 
            }
        }); 
        
        JButton chooseButton = new JButton("Choose the colour"); 
        
        chooseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Color selectedColour = JColorChooser.showDialog(
                        Colour.this, 
                        "Select a colour", 
                        colourPanel.getBackground() 
                );
                
                if(selectedColour != null){
                    updateGUI(selectedColour); 
                }
            }
        }); 
        
        JButton okButton = new JButton("OK"); 
        okButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
               applyColour(colourCodeField.getText()); 
           }
        });
        
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(chooseButton);
        bottomPanel.add(colourCodeField); 
        bottomPanel.add(okButton); 
        
        add(bottomPanel, BorderLayout.SOUTH);
        add(colourPanel, BorderLayout.CENTER); 
        
        setLocationRelativeTo(null); 
    }
 
    private void updateGUI(Color colour){
        colourPanel.setBackground(colour); 
        int r = colour.getRed(); 
        int g = colour.getGreen(); 
        int b = colour.getBlue(); 
        String hex = String.format("#%02X%02X%02X", r, g, b);
        colourCodeField.setText(hex); 
    }
    
    private void applyColour(String text){
        text = text.trim(); 
        Color newColour = null; 
        
        try {
            if(text.startsWith("#") || text.matches("^[0-9A-Fa-f]{6}$")){
                if(!text.startsWith("#")){
                    text = "#" + text; 
                }
                newColour = Color.decode(text); 
            } else if (text.contains(",")){
                String[] parts = text.replace("RGB", "").replace("rgb", "").replaceAll("[()]", "").split(",");
                if(parts.length == 3){
                    int r = Integer.parseInt(parts[0].trim());
                    int g = Integer.parseInt(parts[1].trim());
                    int b = Integer.parseInt(parts[2].trim()); 
                    
                    if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255){
                        newColour = new Color(r, g, b); 
                    }
                }
            }
            if(newColour != null){
                colourPanel.setBackground(newColour); 
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid format! Use Hex (ex: #FF5733) ou RGB (ex: 255, 87, 51).",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,
                    "The colour code could not be interpreted",
                    "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
}
