/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.colourpicker;

import javax.swing.SwingUtilities;

/**
 *
 * @author casa
 */
public class ColourPicker {

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            new Colour().setVisible(true); 
        }); 
    }
}
