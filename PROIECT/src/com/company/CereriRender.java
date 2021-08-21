package com.company;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CereriRender extends JPanel implements ListCellRenderer<Request> {
    JLabel Nume_Job = new JLabel();
    JLabel Nume_User = new JLabel();
    JLabel Nume_Recruiter= new JLabel();
    JLabel Nota_User = new JLabel();
    JPanel mijloc = new JPanel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list,
                                                  Request value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Nume_Job.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        mijloc.setLayout(new GridLayout(1,2));
        Nume_Job.setText("Job: " + ((Job)value.getKey()).job_name);
        add(Nume_Job);
        Nume_User.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nume_Recruiter.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nota_User.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Nume_User.setText("Nume user: " + ((User)value.getValue1())
                .rezumat.date_personale.getPrenume() + " " +
                ((User)value.getValue1()).rezumat.date_personale.getNume());
        Nume_Recruiter.setText("Nume Recruiter: " +
                ((Recruiter)value.getValue2()).rezumat.date_personale
                        .getPrenume() + " " + ((Recruiter)value.getValue2())
                .rezumat.date_personale.getNume());
        NumberFormat format = new DecimalFormat("#0.00");
        Nota_User.setText("Scor: " + format.format(value.getScore()));
        mijloc.add(Nume_User);
        mijloc.add(Nume_Recruiter);
        add(mijloc);
        add(Nota_User);
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
