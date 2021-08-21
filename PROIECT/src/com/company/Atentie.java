package com.company;

import javax.swing.JOptionPane;

public class Atentie
{

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage,
                titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}