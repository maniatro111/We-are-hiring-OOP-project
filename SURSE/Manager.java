package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Manager extends Employee{
    ArrayList<Request> cereri;

    public Manager() {
    }

    public Manager(User user) {
        super(user);
        cereri=new ArrayList<Request>();
    }

    public Manager(Resume rezumat) {
        super(rezumat);
        cereri = new ArrayList<Request>();
    }


    public void process(Job job){
        //Verific daca jobul este inca deschis
        if(!job.flag_este_plin){
        int noPosition=job.nr_joburi_libere;
        Application app=Application.getInstance();
        //Creez un TreeSet cu Request-urile catrea acelasi job, ordonate descres
            // cator dupa scor.
        TreeSet<Request>cereri_job=new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(Request o1, Request o2) {
                if (o1.getScore().compareTo(o2.getScore()) == 0)
                    return 1;
                return o2.getScore().compareTo(o1.getScore());
            }
        });

        for(Request cerere :cereri)
            if(cerere.getKey().equals(job))
                cereri_job.add(cerere);
            //Sterg cererile tocmai introduse in setul de mai sus din lista de
            //cereri a managerului.
        for(Request cerere: cereri_job)
            cereri.remove(cerere);
        //Parcurg treeset-ul
        Iterator<Request> itr=cereri_job.iterator();
        while(noPosition>0 && itr.hasNext()){

                Request aux=itr.next();
                //Verific daca utilizatorul nu a mai fost deja angajat in alta
            //parte
                if(app.utilizatori.contains((User)aux.getValue1())){
                    //Il convertesc la Employee
                    Employee angajat=new Employee((User) aux.getValue1());
                    angajat.nume_comp= job.company_name;
                    angajat.Salariu= job.salariu;
                    //Il sterg din listele de subscriberi din toate companiile
                    //la care a aplicat.
                    for(Company cmp : app.companii)
                        if(cmp.subs.contains((User)aux.getValue1()))
                            cmp.removeObserver((User)aux.getValue1());
                    Company companie=app.getCompany(job.company_name);
                    //Il bag in departamentul corespunzator jobului.
                    for(Department dep:companie.departments)
                        if(dep.joburi_disp.contains(job))
                            companie.add(angajat,dep);
                        //Il scot din lista de utilizatori ai aplicatiei.
                    app.utilizatori.remove((User) aux.getValue1());
                    //Scad numarul de pozitii disponibile
                    noPosition--;
                }

        }
        //Pentru restul de cereri, trimit cate un mesaj respectivilor utilizatori
            //anuntandu-i ca nu au fost acceptati.
        for(Request req : cereri_job){
            User us = (User) req.getValue1();
            us.update("Cererea ta pentru jobul de " +
                    ((Job)req.getKey()).job_name + " a fost refuzata.");
        }
        cereri_job.clear();
        //Inchid job-ul
        job.flag_este_plin=true;
        Company companie = app.getCompany(nume_comp);
        //Trimit o notificare catre toti abonatii companiei, anuntandu-i ca job-ul
            //s-a inchis.
        companie.notifyObservers("Jobul de " + job.job_name +
                " a fost inchis.");
    }
    }
}
