package com.company;

import javax.swing.*;
import java.awt.*;

public class EmployeeRender extends JPanel implements ListCellRenderer<Employee> {
    JLabel Nume = new JLabel();
    JLabel Salariu = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Employee> list,
                                                  Employee value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Salariu.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nume.setText("Nume: " + value.rezumat.date_personale.getPrenume() + " "
                + value.rezumat.date_personale.getNume());
        add(Nume);
        Salariu.setText("Salariu: " + value.Salariu.toString());
        add(Salariu);
        if(isSelected){
            setBackground(Color.red);
        }
        else{
            setBackground(new Color(0x00FFF8));
        }
        return this;
    }
}
