package com.company;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Company_Page extends JFrame implements ListSelectionListener {
    private JPanel Main_Panel;
    private JPanel IT_department;
    private JPanel Finance_Department;
    private JPanel Marketing_department;
    private JPanel Management_department;
    private JPanel Detalii_companii;
    private JTextField Nume_companie_field;
    private JTextField Manager_field;
    private JButton Calculate_Budget;
    private JLabel Nume_companie;
    private JLabel Buget_total;
    private JLabel Manager_label;
    private JLabel IT;
    private JPanel Panel_buget_IT;
    private JButton calculeazaBugetIT;
    private JLabel Buget_It;
    private JPanel Panel_IT;
    private JLabel Finance;
    private JPanel Panel_Finance;
    private JPanel Panel_buget_Finance;
    private JPanel Panel_Angajati_IT;
    private JPanel Panel_Joburi_IT;
    private JPanel Panel_Angajati_Finance;
    private JPanel Panel_Joburi_Finance;
    private JScrollPane Scroll_Ang_IT;
    private JScrollPane Scroll_Job_IT;
    private JScrollPane Scroll_Ang_Fin;
    private JScrollPane Scroll_Job_Finance;
    private JButton calculeazaBugetFinance;
    private JLabel Buget_Finance;
    private JPanel Panel_nume_Marketing;
    private JLabel Marketing;
    private JPanel Panel_Marketing;
    private JPanel Panel_buget_Marketing;
    private JPanel Panel_Angajati_Marketing;
    private JPanel Panel_Joburi_Marketing;
    private JScrollPane Scroll_Ang_Mark;
    private JScrollPane Scroll_Job_Mark;
    private JButton calculeazaBugetMarketing;
    private JLabel Buget_Marketing;
    private JPanel Panel_buget_Management;
    private JButton calculeazaBugetManagement;
    private JLabel BugetManagement;
    private JLabel Management;
    private JPanel Panel_nume_Management;
    private JPanel Panel_Management;
    private JPanel Panel_Angajati_Management;
    private JPanel Panel_Joburi_Management;
    private JScrollPane Scroll_Ang_Man;
    private JScrollPane Scroll_Job_Man;
    private JLabel Buget_Total_field;

    private JList<Employee> Angajati_IT_List;
    private JList<Job>Joburii_IT_List;

    private JList<Employee> Angajati_Finance_List;
    private JList<Job>Joburii_Finance_List;

    private JList<Employee> Angajati_Marketing_List;
    private JList<Job>Joburii_Marketing_List;

    private JList<Employee> Angajati_Management_List;
    private JList<Job>Joburii_Management_List;

    DefaultListModel<Employee> Model_Angajati_IT = new DefaultListModel<>();
    DefaultListModel<Job> Model_Job_IT =new DefaultListModel<>();

    DefaultListModel<Employee> Model_Angajati_Finance = new DefaultListModel<>();
    DefaultListModel<Job> Model_Job_Finance =new DefaultListModel<>();

    DefaultListModel<Employee> Model_Angajati_Marketing = new DefaultListModel<>();
    DefaultListModel<Job> Model_Job_Marketing =new DefaultListModel<>();

    DefaultListModel<Employee> Model_Angajati_Management = new DefaultListModel<>();
    DefaultListModel<Job> Model_Job_Management =new DefaultListModel<>();



    Company_Page(Company cmp){
        super("Profil companie");
        setContentPane(Main_Panel);
        Nume_companie_field.setText(cmp.nume);
        Manager_field.setText(cmp.manager.rezumat.date_personale.getPrenume()
                + " " + cmp.manager.rezumat.date_personale.getNume());
        for(Department dep : cmp.departments){

            if(dep.name.compareTo("IT")==0){
                Model_Angajati_IT.addAll(dep.angajati);
                Model_Job_IT.addAll(dep.getJobs());
            }

            if(dep.name.compareTo("Finance")==0){
                Model_Angajati_Finance.addAll(dep.angajati);
                Model_Job_Finance.addAll(dep.getJobs());
            }

            if(dep.name.compareTo("Marketing")==0){
                Model_Angajati_Marketing.addAll(dep.angajati);
                Model_Job_Marketing.addAll(dep.getJobs());
            }

            if(dep.name.compareTo("Management")==0){
                Model_Angajati_Management.addAll(dep.angajati);
                Model_Job_Management.addAll(dep.getJobs());
            }
        }

        Angajati_IT_List=new JList<>(Model_Angajati_IT);
        Joburii_IT_List = new JList<>(Model_Job_IT);
        Scroll_Ang_IT.setViewportView(Angajati_IT_List);
        Scroll_Job_IT.setViewportView(Joburii_IT_List);


        Angajati_Finance_List=new JList<>(Model_Angajati_Finance);
        Joburii_Finance_List = new JList<>(Model_Job_Finance);
        Scroll_Ang_Fin.setViewportView(Angajati_Finance_List);
        Scroll_Job_Finance.setViewportView(Joburii_Finance_List);

        Angajati_Management_List=new JList<>(Model_Angajati_Management);
        Joburii_Management_List = new JList<>(Model_Job_Management);
        Scroll_Ang_Man.setViewportView(Angajati_Management_List);
        Scroll_Job_Man.setViewportView(Joburii_Management_List);

        Angajati_Marketing_List=new JList<>(Model_Angajati_Marketing);
        Joburii_Marketing_List = new JList<>(Model_Job_Marketing);
        Scroll_Ang_Mark.setViewportView(Angajati_Marketing_List);
        Scroll_Job_Mark.setViewportView(Joburii_Marketing_List);

        Angajati_IT_List.setBackground(new Color(0x00FFF8));
        Joburii_IT_List.setBackground(new Color(0x00FFF8));
        Angajati_Finance_List.setBackground(new Color(0x00FFF8));
        Joburii_Finance_List.setBackground(new Color(0x00FFF8));
        Angajati_Management_List.setBackground(new Color(0x00FFF8));
        Joburii_Management_List.setBackground(new Color(0x00FFF8));
        Angajati_Marketing_List.setBackground(new Color(0x00FFF8));
        Joburii_Marketing_List.setBackground(new Color(0x00FFF8));

        Angajati_IT_List.setCellRenderer(new EmployeeRender());
        Angajati_Finance_List.setCellRenderer(new EmployeeRender());
        Angajati_Management_List.setCellRenderer(new EmployeeRender());
        Angajati_Marketing_List.setCellRenderer(new EmployeeRender());


        Joburii_IT_List.setCellRenderer(new JobRender());
        Joburii_Finance_List.setCellRenderer(new JobRender());
        Joburii_Marketing_List.setCellRenderer(new JobRender());
        Joburii_Management_List.setCellRenderer(new JobRender());


        Angajati_IT_List.addListSelectionListener(this);
        Angajati_Finance_List.addListSelectionListener(this);
        Angajati_Management_List.addListSelectionListener(this);
        Angajati_Marketing_List.addListSelectionListener(this);

        Manager_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 ){
                    Manager u = cmp.manager;
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
            }
        });


        Joburii_IT_List.addListSelectionListener(this);
        Joburii_Finance_List.addListSelectionListener(this);
        Joburii_Marketing_List.addListSelectionListener(this);
        Joburii_Management_List.addListSelectionListener(this);

        Angajati_IT_List.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =Angajati_IT_List.getCellBounds(0,Angajati_IT_List
                        .getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Consumer u = Angajati_IT_List.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
                if(r==null || !r.contains(e.getPoint()))
                    Angajati_IT_List.clearSelection();
            }
        });

        Angajati_Finance_List.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =Angajati_Finance_List.getCellBounds(0,
                        Angajati_Finance_List.getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Consumer u = Angajati_Finance_List.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
                if(r==null || !r.contains(e.getPoint()))
                    Angajati_Finance_List.clearSelection();
            }
        });

        Angajati_Marketing_List.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =Angajati_Marketing_List.getCellBounds(0,
                        Angajati_Marketing_List.getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Consumer u = Angajati_Marketing_List.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
                if(r==null || !r.contains(e.getPoint()))
                    Angajati_Marketing_List.clearSelection();
            }
        });

        Angajati_Management_List.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle r =Angajati_Management_List.getCellBounds(0,
                        Angajati_Management_List.getLastVisibleIndex());
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
                        && (r!=null && r.contains(e.getPoint()))){
                    Consumer u = Angajati_Management_List.getSelectedValue();
                    Profile_Page p =new Profile_Page(u);
                    p.setVisible(true);
                }
                if(r==null || !r.contains(e.getPoint()))
                    Angajati_Management_List.clearSelection();
            }
        });

        pack();


        Calculate_Budget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double s = 0.0;
                for(Department dep : cmp.departments)
                    s = s + dep.getTotalSalaryBudget();
                Buget_Total_field.setText(s.toString());
            }
        });
        calculeazaBugetIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Department dep : cmp.departments)
                    if(dep.name.compareTo("IT")==0){
                        Double b =dep.getTotalSalaryBudget();
                        String d =b.toString();
                        Buget_It.setText(d);
                    }
            }
        });
        calculeazaBugetFinance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Department dep : cmp.departments)
                    if(dep.name.compareTo("Finance")==0){
                        Double b =dep.getTotalSalaryBudget();
                        String d =b.toString();
                        Buget_Finance.setText(d);
                    }
            }
        });
        calculeazaBugetMarketing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Department dep : cmp.departments)
                    if(dep.name.compareTo("Marketing")==0){
                        Double b =dep.getTotalSalaryBudget();
                        String d =b.toString();
                        Buget_Marketing.setText(d);
                    }
            }
        });
        calculeazaBugetManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Department dep : cmp.departments)
                    if(dep.name.compareTo("Management")==0){
                        Double b =dep.getTotalSalaryBudget();
                        String d =b.toString();
                        BugetManagement.setText(d);
                    }
            }
        });
    }

    @Override
   public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(!e.getSource().equals(Angajati_IT_List))
                Angajati_IT_List.clearSelection();
            if(!e.getSource().equals(Angajati_Finance_List))
                Angajati_Finance_List.clearSelection();
            if(!e.getSource().equals(Angajati_Marketing_List))
                Angajati_Marketing_List.clearSelection();
            if(!e.getSource().equals(Angajati_Management_List))
                Angajati_Management_List.clearSelection();
            if(!e.getSource().equals(Joburii_IT_List))
                Joburii_IT_List.clearSelection();
            if(!e.getSource().equals(Joburii_Finance_List))
                Joburii_Finance_List.clearSelection();
            if(!e.getSource().equals(Joburii_Management_List))
                Joburii_Management_List.clearSelection();
            if(!e.getSource().equals(Joburii_Marketing_List))
                Joburii_Marketing_List.clearSelection();
        }

    }
}
