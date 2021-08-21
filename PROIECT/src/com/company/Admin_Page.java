package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_Page extends JFrame implements ListSelectionListener {
    private JPanel Main_Panel;
    private JLabel Admin_page;
    private JLabel Companii_Label;
    private JPanel Panel_Utilizatori;
    private JPanel Panel_Companii;
    private JScrollPane Scroll_Comp;
    private JScrollPane Scroll_user;
    private JButton inapoiButton;
    private JList<Company> lista_companii;
    private JList<User> lista_Useri;

    Admin_Page(){
        super("");
        setContentPane(Main_Panel);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        Application app =Application.getInstance();
        DefaultListModel<User> model_utilizatori = new DefaultListModel<>();
        model_utilizatori.addAll(app.utilizatori);
        lista_Useri = new JList<>(model_utilizatori);
        Scroll_user.setViewportView(lista_Useri);

        DefaultListModel<Company> model_companie = new DefaultListModel<>();
        model_companie.addAll(app.companii);
        lista_companii = new JList<>(model_companie);
        Scroll_Comp.setViewportView(lista_companii);
        lista_Useri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =lista_Useri.getCellBounds(0,lista_Useri
                        .getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 &&
                        (r!=null && r.contains(e.getPoint()))){
                    User u = lista_Useri.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                    lista_Useri.clearSelection();
                }
                if(r==null || !r.contains(e.getPoint()))
                    lista_Useri.clearSelection();
            }
        });
        lista_Useri.addListSelectionListener(this);
        lista_companii.addListSelectionListener(this);
        lista_Useri.setCellRenderer(new UserRender());
        lista_companii.setCellRenderer(new CompanyRender());
        lista_companii.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =lista_companii.getCellBounds(0,lista_companii
                        .getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Company u = lista_companii.getSelectedValue();
                    Company_Page p =new Company_Page(u);
                    p.setVisible(true);
                    lista_companii.clearSelection();
                }
                if(r==null || !r.contains(e.getPoint()))
                    lista_companii.clearSelection();
            }
        });
        pack();
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
        if(e.getValueIsAdjusting()) {
            if (!e.getSource().equals(lista_Useri))
                lista_Useri.clearSelection();
            if (!e.getSource().equals(lista_companii))
                lista_companii.clearSelection();
        }
    }
}
