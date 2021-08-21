package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login_Page extends JFrame{
    private JPanel panel1;
    private JTextField FieldUser;
    private JPasswordField fieldParola;
    private JLabel LoginLabel;
    private JLabel UtilizatoriLabel;
    private JButton loginButton;
    private JLabel PasswordLabel;

    public Login_Page() {
        super("Login Page");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        pack();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username;
                String password;
                username=FieldUser.getText();
                password=fieldParola.getText();
                if(username.compareTo("")==0||password.compareTo("")==0)
                    if(username.compareTo("")==0 && password.compareTo("")==0)
                        Atentie.infoBox("Campurile sunt goale","Atentie!");
                    else{
                        if(username.compareTo("")==0)
                            Atentie.infoBox("Nu ai introdus numele de utilizator!",
                                    "Atentie!");
                        else
                            Atentie.infoBox("Nu ai introdus parola!","Atentie!");
                    }
                    else
                {
                    if(username.compareToIgnoreCase("admin")==0){
                        Admin_Page adm = new Admin_Page();
                        adm.setVisible(true);
                        setVisible(false);
                    }
                    else {
                        String[] nume = username.split("_");
                        Application app = Application.getInstance();
                        ArrayList<Consumer> oameni = app.getAllConsumers();
                        boolean gasit = false;
                        for (Consumer cns : oameni) {
                            if (nume[1].compareToIgnoreCase(
                                    cns.rezumat.date_personale.getNume()) == 0 &&
                                    nume[0].compareToIgnoreCase(cns.rezumat
                                            .date_personale.getPrenume()) == 0) {
                                if (cns instanceof User) {
                                    User u = (User) cns;
                                    User_Page user_page = new User_Page(u);
                                    user_page.setVisible(true);
                                    setVisible(false);
                                }
                               else if(cns instanceof Manager) {
                                    Manager emp = (Manager) cns;
                                    Manager_Page manager_page =
                                            new Manager_Page(emp);
                                    manager_page.setVisible(true);
                                    setVisible(false);
                                }

                                else if (cns instanceof Recruiter) {
                                    Recruiter emp = (Recruiter) cns;
                                    Recruiter_Page recruiter_page =
                                            new Recruiter_Page(emp);
                                    recruiter_page.setVisible(true);
                                    setVisible(false);
                                }

                                else if (cns instanceof Employee) {
                                    Employee emp = (Employee) cns;
                                    Employee_Page employee_page =
                                            new Employee_Page(emp);
                                    employee_page.setVisible(true);
                                    setVisible(false);
                                }
                                gasit = true;
                            }
                        }
                        if (!gasit)
                            Atentie.infoBox("Nu am gasit utilizatorul", "Atentie!");
                    }
                }
            }
        });
    }
}
