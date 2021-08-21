package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ResumeIncompleteException {
        Gson gson = new Gson();
        JsonReader cititor = new JsonReader(new FileReader("consumers.json"));
        Entities entitati = gson.fromJson(cititor,Entities.class);
        ArrayList<Employee> Angajati = new ArrayList<>();
        ArrayList<Recruiter> Recruiter = new ArrayList<>();
        ArrayList<User> Useri = new ArrayList<>();
        ArrayList<Manager> Manageri = new ArrayList<>();

        for(Entity_info info :entitati.employees){
            String[] NUME=info.name.split(" ");
            TreeSet<Education> ed=new TreeSet<>(info.education);
            TreeSet<Experience> ex=new TreeSet<>(info.experience);
            Resume rez = new Resume.ResumeBuilder().last_name(NUME[1])
                    .first_name(NUME[0]).Email(info.email).Telefon(info.phone)
                    .Data_nastere(info.date_of_birth).Sex(info.genre)
                    .Limbi(info.languages, info.languages_level).Education(ed)
                    .Experience(ex).build();
            Employee emp = new Employee(rez);
            emp.Salariu=info.salary;
            emp.nume_comp= emp.getNume_comp();
            Angajati.add(emp);
        }

        for(Entity_info info : entitati.recruiters){
            String[] NUME=info.name.split(" ");
            TreeSet<Education> ed=new TreeSet<>(info.education);
            TreeSet<Experience> ex=new TreeSet<>(info.experience);
            Resume rez = new Resume.ResumeBuilder().last_name(NUME[1])
                    .first_name(NUME[0]).Email(info.email).Telefon(info.phone)
                    .Data_nastere(info.date_of_birth).Sex(info.genre)
                    .Limbi(info.languages, info.languages_level).Education(ed)
                    .Experience(ex).build();
            Recruiter recr = new Recruiter(rez);
            recr.Salariu= info.salary;
            recr.nume_comp= recr.getNume_comp();
            Recruiter.add(recr);
        }

        for(Entity_info info : entitati.users){
            String[] NUME=info.name.split(" ");
            TreeSet<Education> ed=new TreeSet<>(info.education);
            TreeSet<Experience> ex=new TreeSet<>(info.experience);
            Resume rez = new Resume.ResumeBuilder().last_name(NUME[1])
                    .first_name(NUME[0]).Email(info.email).Telefon(info.phone)
                    .Data_nastere(info.date_of_birth).Sex(info.genre)
                    .Limbi(info.languages, info.languages_level).Education(ed)
                    .Experience(ex).build();
            ArrayList<String> comp=new ArrayList<>(info.interested_companies);
            User us =new User(rez,comp);
            Useri.add(us);
        }

        for(Entity_info info : entitati.managers){
            String[] NUME=info.name.split(" ");
            TreeSet<Education> ed=new TreeSet<>(info.education);
            TreeSet<Experience> ex=new TreeSet<>(info.experience);
            Resume rez = new Resume.ResumeBuilder().last_name(NUME[1])
                    .first_name(NUME[0]).Email(info.email).Telefon(info.phone)
                    .Data_nastere(info.date_of_birth).Sex(info.genre)
                    .Limbi(info.languages, info.languages_level).Education(ed)
                    .Experience(ex).build();
            Manager man = new Manager(rez);
            man.Salariu= info.salary;
            man.nume_comp= man.getNume_comp();
            Manageri.add(man);
        }

        cititor = new JsonReader(new FileReader("Rest.json"));
        The_rest info_rest = gson.fromJson(cititor,The_rest.class);

        Application app=Application.getInstance();
        for(User utilizator : Useri)
            app.add(utilizator);

        Ajutor help =new Ajutor();

        for(Person pp : info_rest.friends){
            String[] nume=pp.name.split(" ");

            if(pp.where.compareTo("user")==0)
            {
                User U= help.retUser(nume[1],nume[0],Useri);
                for(Known pers : pp.known){
                    String[] pers_name=pers.name.split(" ");
                    if(pers.where.compareTo("user")==0){
                        User U_friend=help.retUser(pers_name[1],pers_name[0],Useri);
                        U.lista_prieteni.add(U_friend);
                    }
                    if(pers.where.compareTo("recruiter")==0){
                        Recruiter R_friend=help.retRec(pers_name[1],pers_name[0],
                                Recruiter);
                        U.lista_prieteni.add(R_friend);
                    }
                    if(pers.where.compareTo("employee")==0){
                        Employee E_friend=help.retEmp(pers_name[1],pers_name[0],
                                Angajati);
                        U.lista_prieteni.add(E_friend);
                    }
                }
            }

            if(pp.where.compareTo("recruiter")==0){
                Recruiter R= help.retRec(nume[1],nume[0],Recruiter);
                for(Known pers : pp.known){
                    String[] pers_name=pers.name.split(" ");
                    if(pers.where.compareTo("user")==0){
                        User U_friend=help.retUser(pers_name[1],pers_name[0],Useri);
                        R.lista_prieteni.add(U_friend);
                    }
                    if(pers.where.compareTo("recruiter")==0){
                        Recruiter R_friend=help.retRec(pers_name[1],pers_name[0],
                                Recruiter);
                        R.lista_prieteni.add(R_friend);
                    }
                    if(pers.where.compareTo("employee")==0){
                        Employee E_friend=help.retEmp(pers_name[1],pers_name[0],
                                Angajati);
                        R.lista_prieteni.add(E_friend);
                    }
                }
            }

            if(pp.where.compareTo("employee")==0){
                Employee E= help.retEmp(nume[1],nume[0],Angajati);
                for(Known pers : pp.known){
                    String[] pers_name=pers.name.split(" ");
                    if(pers.where.compareTo("user")==0){
                        User U_friend=help.retUser(pers_name[1],pers_name[0],Useri);
                        E.lista_prieteni.add(U_friend);
                    }
                    if(pers.where.compareTo("recruiter")==0){
                        Recruiter R_friend=help.retRec(pers_name[1],pers_name[0],
                                Recruiter);
                        E.lista_prieteni.add(R_friend);
                    }
                    if(pers.where.compareTo("employee")==0){
                        Employee E_friend=help.retEmp(pers_name[1],pers_name[0],
                                Angajati);
                        E.lista_prieteni.add(E_friend);
                    }
                }
            }
        }

        for(Cmp_Info com : info_rest.companies){
            String[] man_name=com.mng_name.split(" ");
            Manager man = help.retMan(man_name[1],man_name[0],Manageri);
            Company cmpnie = new Company(com.name,man);
            app.add(cmpnie);
        }

        for(Employee ang : Angajati){
            String nume_comp=ang.getNume_comp();
            String nume_dep= ang.getDep();
            Company com = app.getCompany(nume_comp);
            Department dep= com.getDep(nume_dep);
            dep.add(ang);
        }

        for(Recruiter rec : Recruiter){
            String num_comp=rec.nume_comp;
            Company com = app.getCompany(num_comp);
            com.add(rec);
        }
      for(Job_Info jb : info_rest.jobs){
          Job J = new Job(jb.name, jb.comp_name, new Constraint(jb.grad_min,
                  jb.grad_max),new Constraint(jb.exp_min,jb.exp_max),
                  new Constraint(jb.grade_min,jb.grade_max),jb.free_pos,
                  jb.salary);
          Company comp = app.getCompany(jb.comp_name);
          Department dep = comp.getDep("IT");
          dep.add(J);
        }

        for(User u : app.utilizatori){
            for(String cmp : u.companii){
                Company comp=app.getCompany(cmp);
                comp.addObserver(u);
                ArrayList<Job>m= comp.getJobs();
                    for(Job jb : m){
                        u.notificari.add("Ai aplicat pentru jobul de " +
                                jb.job_name + " la compania " + cmp + ".");
                        jb.apply(u);
                    }
            }
        }

       Company cmp= app.getCompany("Google");


       /* Manager cns = app.companii.get(0).manager;
        Manager_Page profil =new Manager_Page(cns);
        profil.setVisible(true);

        Admin_Page p = new Admin_Page();
        p.setVisible(true);

        User_Page u =new User_Page(app.utilizatori.get(0));
        u.setVisible(true);

        Employee_Page e =new Employee_Page(app.companii.get(0).departments.get(0).angajati.get(0));
        e.setVisible(true);

        Recruiter_Page r =new Recruiter_Page(app.companii.get(0).recruiters.get(0));
        r.setVisible(true);*/

        Login_Page l =new Login_Page();
        l.setVisible(true);

    }
}
