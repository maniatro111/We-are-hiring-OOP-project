package com.company;

import javax.swing.*;
import java.awt.*;

public class ExperienceRender extends JPanel implements ListCellRenderer<Experience> {
    JLabel Nume = new JLabel();
    JLabel Start_Date = new JLabel();
    JLabel End_Date= new JLabel();
    JLabel Level = new JLabel();
    JLabel Grade = new JLabel();
    JPanel mijloc = new JPanel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Experience> list,
                                                  Experience value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        mijloc.setLayout(new GridLayout(2,2));
        Nume.setText("Compania: " + value.company);
        add(Nume);
        Start_Date.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        End_Date.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Level.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Grade.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Start_Date.setText("De pe: " + value.start_date);
        End_Date.setText("Pana pe: " + value.end_date);
        Level.setText("Departament: " + value.department);
        Grade.setText("Pozitia: " + value.position);
        mijloc.add(Start_Date);
        mijloc.add(Grade);
        mijloc.add(End_Date);
        mijloc.add(Level);
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
