package com.company;

import javax.swing.*;
import java.awt.*;

public class EducatieRender extends JPanel implements ListCellRenderer<Education>{
    JLabel Nume = new JLabel();
    JLabel Start_Date = new JLabel();
    JLabel End_Date= new JLabel();
    JLabel Level = new JLabel();
    JLabel Grade = new JLabel();
    JPanel mijloc = new JPanel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Education> list,
                                                  Education value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        mijloc.setLayout(new GridLayout(2,2));
        Nume.setText("Nume institutie: " + value.name);
        add(Nume);
        Start_Date.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        End_Date.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Level.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Grade.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Start_Date.setText("Inceputa pe: " + value.start_date);
        End_Date.setText("Terminata pe: " + value.end_date);
        Level.setText("Nivel de educatie: " + value.level);
        Grade.setText("Nota: " + value.grade);
        mijloc.add(Start_Date);
        mijloc.add(End_Date);
        mijloc.add(Level);
        mijloc.add(Grade);
        add(mijloc);
        if(isSelected){
            setBackground(Color.red);
            mijloc.setBackground(Color.red);
        }
        else{
            setBackground(new Color(0x00FFF8));
            mijloc.setBackground(new Color(0x00FFF8));
        }
        return this;
    }
}
