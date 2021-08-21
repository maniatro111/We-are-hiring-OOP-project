package com.company;

import javax.swing.*;
import java.awt.*;

public class ConsumerRender extends JPanel implements ListCellRenderer<Consumer> {
    JLabel Nume=new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Consumer> list,
                                                  Consumer value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        Nume.setText(value.rezumat.date_personale.getPrenume() + " " +
                value.rezumat.date_personale.getNume());
        add(Nume);

        if(isSelected){
            setBackground(Color.red);
        }
        else{
            setBackground(new Color(0x00FFF8));
        }
        return this;
    }
}
