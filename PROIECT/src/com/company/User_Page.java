package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class User_Page extends JFrame implements ListSelectionListener {
    private JPanel panel1;
    private JTabbedPane Tab_Panel_1;
    private JPanel Panel_1;
    private JPanel Panel_2;
    private JButton profilulMeuButton;
    private JLabel Label_Prieteni;
    private JLabel Label_joburi;
    private JScrollPane Pane_notificari;
    private JScrollPane Scroll_prieteni;
    private JScrollPane Scroll_joburi;
    private JButton Refresh_button;
    private JButton inapoiButton;
    private DefaultListModel<Consumer>model_prieteni;
    private DefaultListModel<Job>model_joburi;
    private DefaultListModel<String>model_notificari;
    private JList<Consumer>lista_prieteni;
    private JList<String>lista_notificari;
    private JList<Job>lista_joburi;

    User_Page(User user){
        super("Pagina lui " + user.rezumat.date_personale.getPrenume()
                + " " + user.rezumat.date_personale.getNume());
        setContentPane(panel1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        model_prieteni=new DefaultListModel<>();
        model_prieteni.addAll(user.lista_prieteni);
        lista_prieteni=new JList<>(model_prieteni);
        Scroll_prieteni.setViewportView(lista_prieteni);
        lista_prieteni.setCellRenderer(new ConsumerRender());
        model_notificari= new DefaultListModel<>();
        model_notificari.addAll(user.notificari);
        lista_notificari=new JList<>(model_notificari);
        Pane_notificari.setViewportView(lista_notificari);
        ArrayList<Job> jobs=new ArrayList<>();
        Application app=Application.getInstance();
        for(Company cmp : app.companii)
            jobs.addAll(cmp.getJobs());
        model_joburi= new DefaultListModel<>();
        model_joburi.addAll(jobs);
        lista_joburi=new JList<>(model_joburi);
        Scroll_joburi.setViewportView(lista_joburi);
        lista_joburi.setCellRenderer(new JobRender());
        pack();
        profilulMeuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile_Page p = new Profile_Page(user);
                p.setVisible(true);
            }
        });
        lista_prieteni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =lista_prieteni.getCellBounds(0,
                        lista_prieteni.getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Consumer u = lista_prieteni.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
                if(r==null || !r.contains(e.getPoint()))
                    lista_prieteni.clearSelection();
            }
        });
        lista_prieteni.addListSelectionListener(this);
        lista_joburi.addListSelectionListener(this);
        lista_notificari.addListSelectionListener(this);
        lista_notificari.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =lista_notificari.getCellBounds(0,
                        lista_notificari.getLastVisibleIndex());
                if(r==null || !r.contains(e.getPoint()))
                    lista_notificari.clearSelection();
            }
        });
        Refresh_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Page log =new Login_Page();
                log.setVisible(true);
                setVisible(false);
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(!e.getSource().equals(lista_prieteni))
                lista_prieteni.clearSelection();
            if(!e.getSource().equals(lista_joburi))
                lista_joburi.clearSelection();
            if(!e.getSource().equals(lista_notificari))
                lista_notificari.clearSelection();
        }
    }
}
