package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class Profile_Page extends JFrame implements ListSelectionListener {
    private JLabel Experienta_Label;
    private JScrollPane Experienta_Pane;
    private JLabel Prenume_Label;
    private JTextField Prenume_Field;
    private JLabel Email_Label;
    private JTextField Email_text;
    private JLabel Data_Nastere_Label;
    private JTextField Data_Nastere_Text;
    private JLabel Nume_Label;
    private JTextField Nume_Text;
    private JLabel Telefon_lab;
    private JTextField Telefon_text;
    private JLabel Gen_Label;
    private JTextField Gen_Fields;
    private JLabel Educatie_Label;
    private JScrollPane Educatie_Pane;
    private JScrollPane Limbi_cunoscute_pane;
    private JPanel MainPanel;
    private JLabel Limibi_cunoscute_lb;
    private final JList<Education> Educatie_list;
    private final JList<Experience> Experienta_list;
    private final JList<String> Limbi_list;

    Profile_Page(Consumer cons){
        super("Informatii despre");
        this.setContentPane(this.MainPanel);
        Prenume_Field.setText(cons.rezumat.date_personale.getPrenume());
        Nume_Text.setText(cons.rezumat.date_personale.getNume());
        Email_text.setText(cons.rezumat.date_personale.getEmail());
        Telefon_text.setText(cons.rezumat.date_personale.getTelefon());
        Data_Nastere_Text.setText(cons.rezumat.date_personale.getData_Nastere());
        Gen_Fields.setText(cons.rezumat.date_personale.getSex());
        Vector<Education> edu = new Vector<>(cons.rezumat.educatie);
        Educatie_list=new JList(edu);
        Educatie_list.setCellRenderer(new EducatieRender());
        Educatie_list.setBackground(new Color(0x00FFF8));
        Educatie_Pane.setViewportView(Educatie_list);
        Vector<Experience> exp = new Vector<>(cons.rezumat.experienta);
        Experienta_list=new JList(exp);
        Experienta_Pane.setViewportView(Experienta_list);
        Experienta_list.setCellRenderer(new ExperienceRender());
        Vector<String>Limbi=new Vector<>(cons.rezumat.date_personale.getLimbiarr());
        Vector<String>Exper=new Vector<>(cons.rezumat.date_personale
                .getExperientaarr());
        Vector<String>Limbi_Exp=new Vector<>();
        for(int i=0;i<Limbi.size();i++){
            String s = Limbi.get(i) + " -> " + Exper.get(i);
            Limbi_Exp.add(s);
        }
        Limbi_list=new JList(Limbi_Exp);
        Limbi_cunoscute_pane.setViewportView(Limbi_list);
        Experienta_list.addListSelectionListener(this);
        Educatie_list.addListSelectionListener(this);
        Limbi_list.addListSelectionListener(this);

        this.pack();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(!e.getSource().equals(Experienta_list))
                Experienta_list.clearSelection();
            if(!e.getSource().equals(Educatie_list))
                Educatie_list.clearSelection();
            if(!e.getSource().equals(Limbi_list))
                Limbi_list.clearSelection();
        }
    }
}
