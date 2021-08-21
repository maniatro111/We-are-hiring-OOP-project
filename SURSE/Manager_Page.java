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

public class Manager_Page extends JFrame{
    private JPanel Main_Panel;
    private JButton Manager_profile;
    private JLabel Manager_label;
    private JButton buton_recruiter;
    private JButton buton_angajat;
    private JButton Angajare_automata;
    private JButton Respingere_cerere;
    private JButton Acceptare_cerere;
    private JScrollPane Lista_cereri_scroll;
    private JPanel Panel_cereri;
    private JLabel Cerere_aangajare_lb;
    private JButton Clear_selection;
    private JButton Buton_companie;
    private JButton inapoiButton;
    private JList<Request> Lista_cereri;

    Manager_Page(Manager man){
        super("Compania: " + man.nume_comp);
        setContentPane(Main_Panel);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        Manager_profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile_Page y = new Profile_Page(man);
                y.setVisible(true);
            }
        });
        DefaultListModel<Request> cerere = new DefaultListModel<>();
        cerere.addAll(man.cereri);
        Manager_label.setText(man.rezumat.date_personale.getPrenume() + " "
                + man.rezumat.date_personale.getNume());
        Lista_cereri=new JList(cerere);
        Lista_cereri_scroll.setViewportView(Lista_cereri);
        Lista_cereri.setCellRenderer(new CereriRender());
        Lista_cereri.setBackground(new Color(0x00FFF8));
        Lista_cereri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =Lista_cereri.getCellBounds(0,
                        Lista_cereri.getLastVisibleIndex());
                if(r==null || !r.contains(e.getPoint()))
                    Lista_cereri.clearSelection();
            }
        });

        buton_recruiter.setEnabled(false);
        buton_angajat.setEnabled(false);
        Respingere_cerere.setEnabled(false);
        Acceptare_cerere.setEnabled(false);

        Clear_selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lista_cereri.clearSelection();
            }
        });
        Lista_cereri.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(Lista_cereri.isSelectionEmpty()){
                    buton_recruiter.setEnabled(false);
                    buton_angajat.setEnabled(false);
                    Respingere_cerere.setEnabled(false);
                    Acceptare_cerere.setEnabled(false);
                }
                else {
                    buton_recruiter.setEnabled(true);
                    buton_angajat.setEnabled(true);
                    Respingere_cerere.setEnabled(true);
                    Acceptare_cerere.setEnabled(true);
                }
            }
        });


        this.pack();
        buton_recruiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Lista_cereri.getSelectedIndex();
                Request r = man.cereri.get(i);
                Profile_Page p =new Profile_Page((Recruiter)r.getValue2());
                p.setVisible(true);
            }
        });
        buton_angajat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Lista_cereri.getSelectedIndex();
                Request r = man.cereri.get(i);
                Profile_Page p =new Profile_Page((User)r.getValue1());
                p.setVisible(true);
            }
        });
        Respingere_cerere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Lista_cereri.getSelectedIndex();
                User u =(User) (man.cereri.get(i).getValue1());
                u.update("Cererea ta pentru jobul de " + ((Job)man.cereri
                        .get(i).getKey()).job_name + " a fost refuzata.");
                cerere.removeElementAt(i);
                man.cereri.remove(i);
                if(man.cereri.size()==0)
                    Angajare_automata.setEnabled(false);
            }
        });
        Angajare_automata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerere.removeAllElements();
                Application app = Application.getInstance();
                Company cmp =app.getCompany(man.nume_comp);
                ArrayList<Job> da=cmp.getJobs();
                for(Job jb : da)
                    man.process(jb);
                if(man.cereri.size()==0)
                    Angajare_automata.setEnabled(false);
            }
        });
        Acceptare_cerere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application app =Application.getInstance();
                int i = Lista_cereri.getSelectedIndex();
                cerere.removeElementAt(i);
                Request r = man.cereri.get(i);
                man.cereri.remove(i);
                Job j = (Job)r.getKey();
                User u =(User)r.getValue1();
                Employee angajat=new Employee(u);
                angajat.nume_comp= j.company_name;
                angajat.Salariu= j.salariu;
                for(Company cmp : app.companii)
                    if(cmp.subs.contains((User)r.getValue1()))
                        cmp.removeObserver((User)r.getValue1());
                Company companie=app.getCompany(j.company_name);
                for(Department dep:companie.departments)
                    if(dep.joburi_disp.contains(j))
                        companie.add(angajat,dep);
                app.utilizatori.remove(u);
                j.nr_joburi_libere--;
                if(j.nr_joburi_libere==0){
                    companie.notifyObservers("Jobul de " + j.job_name
                            + " a fost inchis.");
                    j.flag_este_plin=true;
                    ArrayList<Request> cereri_job=new ArrayList<>();
                    for(Request cerere :man.cereri)
                        if(cerere.getKey().equals(j))
                            cereri_job.add(cerere);
                    for(Request cerere1: cereri_job) {
                        Integer k =cereri_job.indexOf(cerere1);
                        cerere.removeElementAt(k);
                        User uds =(User) cerere1.getValue1();
                        uds.update("Cererea ta pentru jobul de " + ((Job)
                                cerere1.getKey()).job_name + " a fost refuzata.");
                        man.cereri.remove(cerere1);
                        }
                    }
            }
        });
        Buton_companie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application app =Application.getInstance();
                Company cmp = app.getCompany(man.nume_comp);
                Company_Page pg =new Company_Page(cmp);
                pg.setVisible(true);
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
}
