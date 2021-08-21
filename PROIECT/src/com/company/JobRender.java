package com.company;

import javax.swing.*;
import java.awt.*;

public class JobRender extends JPanel implements ListCellRenderer<Job> {
    JLabel Nume = new JLabel();
    JLabel Salariu = new JLabel();
    JLabel Disponibilitate= new JLabel();
    JLabel Nr_loc_disp = new JLabel();
    JPanel mijloc = new JPanel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Job> list,
                                                  Job value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Salariu.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Disponibilitate.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nr_loc_disp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        mijloc.setLayout(new GridLayout(1,2));
        Nume.setText("Nume Job: " + value.job_name);
        add(Nume);
        if(!value.flag_este_plin) {
            Disponibilitate.setText("Disponibilitate: deschis");
            Nr_loc_disp.setText("Numar locuri ramase: " + value.nr_joburi_libere);
        }
        else {
            Disponibilitate.setText("Dispnibilitate: inchis");
            Nr_loc_disp.setText("Numar locuri ramase: 0");
        }

        Salariu.setText("Salariu: " + value.salariu);
        mijloc.add(Nr_loc_disp);
        mijloc.add(Salariu);
        add(Disponibilitate);
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
