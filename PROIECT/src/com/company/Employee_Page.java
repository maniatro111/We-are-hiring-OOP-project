package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Vector;

public class Employee_Page extends JFrame implements ListSelectionListener {
    private JTabbedPane Tab_Panel_1;
    private JPanel Panel_1;
    private JButton profilulMeuButton;
    private JScrollPane Scroll_prieteni;
    private JLabel Label_Prieteni;
    private JLabel Label_joburi;
    private JScrollPane Scroll_joburi;
    private JPanel Panel_2;
    private JScrollPane Pane_notificari;
    private JButton Refresh_button;
    private JButton demisioneazaButton;
    private JButton muzicaButton;
    private JButton inapoiButton;
    private DefaultListModel<Consumer>model_prieteni;
    private DefaultListModel<Job>model_joburi;
    private JList<Consumer>lista_prieteni;
    private JList<Job>lista_joburi;
    private Vector<String> Links=new Vector<>();
    private Integer k=0;


    Employee_Page(Employee empl){
        super("Pagina lui " + empl.rezumat.date_personale.getPrenume()
                + " " + empl.rezumat.date_personale.getNume());
        setContentPane(Tab_Panel_1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        model_prieteni=new DefaultListModel<>();
        model_prieteni.addAll(empl.lista_prieteni);
        lista_prieteni=new JList<>(model_prieteni);
        Scroll_prieteni.setViewportView(lista_prieteni);
        lista_prieteni.setCellRenderer(new ConsumerRender());

        Links.add("https://www.facebook.com/");
        Links.add("https://www.instagram.com/");
        Links.add("https://www.youtube.com/");

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
                Profile_Page p = new Profile_Page(empl);
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
        Refresh_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        muzicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop d =Desktop.getDesktop();
                try {
                    d.browse(new URI("https://www.youtube.com/watch?v=RkgWSyCCA8g"));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        Refresh_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop d =Desktop.getDesktop();
                try {
                    d.browse(new URI(Links.get(k)));
                    k++;
                    if(k>2)
                        k=0;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });
        demisioneazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop d =Desktop.getDesktop();
                try {
                    d.browse(new URI("https://www.youtube.com/watch?v=ULxrUlPjbEE"));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
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
        }
    }
}
