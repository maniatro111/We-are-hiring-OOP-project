package com.company;

import java.util.ArrayList;

public abstract class Department {
    String name=null;
    ArrayList<Employee> angajati;
    ArrayList<Job>joburi_disp;

    Department(String name){
        angajati= new ArrayList<>();
        joburi_disp= new ArrayList<>();
        this.name=name;
    }

    public abstract double getTotalSalaryBudget();
    public ArrayList<Job> getJobs(){
        ArrayList<Job> rezultat=new ArrayList<Job>();
        for(Job a : joburi_disp)
            if(!a.flag_este_plin)
                rezultat.add(a);
        return rezultat;
    }
    public void add(Employee employee){
        angajati.add(employee);
    }
    public void remove(Employee employee){
        angajati.remove(employee);
    }
    public void add(Job job){
        joburi_disp.add(job);
    }

    public ArrayList<Employee> getEmployees() {
        return angajati;
    }

    @Override
    public String toString(){
        int i;
        String s="";
        s = s + name + ": ";
        if(angajati.size()!=0){
            for(i=0;i<angajati.size()-1;i++) {
                s = s + angajati.get(i).rezumat.date_personale.getNume();
                s = s + " " + angajati.get(i).rezumat.date_personale.getPrenume();
                s = s + ", ";
            }
            s = s + angajati.get(i).rezumat.date_personale.getNume();
            s = s + " " + angajati.get(i).rezumat.date_personale.getPrenume();
    }
        s = s + "\n";
        s = s + "Joburi disponibile: ";
        if(joburi_disp.size() !=0){
            for(Job jb : joburi_disp)
            {
                s = s + jb.job_name + ": " + jb.salariu + " ";
                if(jb.flag_este_plin)
                    s = s + "inchis";
                else
                    s = s + "deschis";
            }
        }
            else
                s = s + "niciunul";
        return s;
    }
}
