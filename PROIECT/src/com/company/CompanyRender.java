package com.company;

import javax.swing.*;
import java.awt.*;

public class CompanyRender extends JPanel implements ListCellRenderer<Company> {
    JLabel Nume_comp =new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Company> list,
                                                  Company value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume_comp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nume_comp.setText(value.nume);
        add(Nume_comp);
        if(isSelected){
            setBackground(Color.red);
        }
        else{
            setBackground(new Color(0x00FFF8));
        }
        return this;
    }
}
