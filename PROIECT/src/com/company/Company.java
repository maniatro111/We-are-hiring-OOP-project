package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Company implements Subject{
    String nume;
    Manager manager;
    ArrayList<Department>departments;
    ArrayList<Recruiter>recruiters;
    ArrayList<Observer>subs;
    Company(){

    }
    Company(String nume,Manager manager){
        this.nume=nume;
        this.manager=manager;
        departments=new ArrayList<Department>();
        recruiters=new ArrayList<Recruiter>();
        subs=new ArrayList<>();
        DepartmentFactory f = new DepartmentFactory();
        Department Dep = f.factory("IT");
        departments.add(Dep);
        Dep = f.factory("Management");
        departments.add(Dep);
        Dep = f.factory("Marketing");
        departments.add(Dep);
        Dep = f.factory("Finance");
        departments.add(Dep);
    }
    public void add(Department department){
        departments.add(department);
    }
    public void add(Recruiter recruiter){
        recruiters.add(recruiter);
        for(Department dep :departments)
            if(dep.name.compareTo("IT")==0)
                dep.angajati.add(recruiter);
    }
    public void add(Employee employee, Department department){
        for (Department value : departments)
            if (value.equals(department))
                value.add(employee);
    }
    public void remove(Employee employee){
        for(Department value : departments)
                if(value.angajati.contains(employee)){
                    int i=value.angajati.indexOf(employee);
                    value.angajati.remove(i);
                }
        recruiters.remove(employee);
    }

    public void remove(Department department){
        department.angajati.clear();
        department.joburi_disp.clear();
        departments.remove(department);
    }
    public void remove(Recruiter recruiter){
        if(recruiters.contains(recruiter)){
            int i=recruiters.indexOf(recruiter);
            recruiters.remove(i);
        }
    }
    public boolean contains(Department department){
        if(departments.contains(department))
            return true;
        return false;
    }
    public boolean contains(Employee employee){
        for(Department val :departments)
            if(val.angajati.contains(employee))
                return true;
        return false;
    }
    public boolean contains(Recruiter recruiter){
        if(recruiters.contains(recruiter))
            return true;
        return false;
    }
    public void move(Department source,Department destinaton){
        for(Job joburi : source.joburi_disp)
            destinaton.add(joburi);
        for(Employee angajat:source.angajati)
            destinaton.add(angajat);
        source.joburi_disp.clear();
        destinaton.angajati.clear();
        departments.remove(source);
    }
    public void move(Employee employee,Department newDepartment){
        newDepartment.add(employee);
        this.remove(employee);
    }
    public Recruiter getRecruiter(User user) {
        ArrayList<Integer> degree = new ArrayList<>();

        for (Recruiter rec : this.recruiters)
            degree.add(user.getDegreeInFriendship(rec));

        int grad_max = Collections.max(degree);

        ArrayList<Recruiter> rec_bun = new ArrayList<>();

        for (Recruiter da : this.recruiters)
            if (degree.get(this.recruiters.indexOf(da)) == grad_max)
                rec_bun.add(da);

        if (rec_bun.size() == 1) {
            Recruiter celbun = rec_bun.get(0);
            return celbun;
        } else {
            ArrayList<Double> ratings = new ArrayList<>();
            for (Recruiter rec : rec_bun)
                ratings.add(rec.rating);
            Double max_rating = Collections.max(ratings);
            for (Recruiter rec : rec_bun)
                if (rec.rating.equals(max_rating)) {
                    return rec;
                }
        }
        return null;
    }
    public ArrayList<Job> getJobs(){
        ArrayList<Job>rezultat=new ArrayList<Job>();
        for(Department dep: departments)
            rezultat.addAll(dep.getJobs());
        return rezultat;
    }

    public Department getDep(String name){
        for(Department d : departments)
            if(d.name.compareTo(name)==0)
                return d;
            return null;
    }

    @Override
    public String toString(){
    String s="";
    s = s + "Nume companie: " + nume + "\n";
    s = s + "Manager: " + manager.rezumat.date_personale.getPrenume() + " " +
            manager.rezumat.date_personale.getNume() + "\n\n";
    s = s + "Departamente: " + "\n";
    for(Department dep : departments)
        s = s + dep.toString() + "\n";
    s = s + "Recruiteri: " ;
    if(recruiters.size()!=0){
        int i;
        for(i=0;i<recruiters.size()-1;i++) {
            s = s + recruiters.get(i).rezumat.date_personale.getNume();
            s = s + " " + recruiters.get(i).rezumat.date_personale.getPrenume();
            s = s + ", ";
        }
        s = s + recruiters.get(i).rezumat.date_personale.getNume();
        s = s + " " + recruiters.get(i).rezumat.date_personale.getPrenume();
    }
    s = s + "\n";
    return s;
    }

    @Override
    public void addObserver(Observer observer) {
        subs.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subs.remove(observer);
    }

    @Override
    public void notifyObservers(String name) {
        for(Observer obs : subs)
            obs.update(name);
    }
}
