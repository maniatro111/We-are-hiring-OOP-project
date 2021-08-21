package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static Application instance = null;

    ArrayList<Company>companii;
    ArrayList<User>utilizatori;

    public Application(){
        companii=new ArrayList<Company>();
        utilizatori=new ArrayList<User>();
    }

    public static Application getInstance(){
        if(instance == null)
            instance = new Application();
        return instance;
    }

    public ArrayList<Company>getCompanies(){
        return companii;
    }

    //Metoda ce imi returneaza toti consumatorii din aplicatie, indiferent de
    //tipul acestora (Manager, Employee, User, Recruiter).
    public ArrayList<Consumer>getAllConsumers(){
        ArrayList<Consumer>rez=new ArrayList<>();
        rez.addAll(utilizatori);
        for(Company cmp : companii){
            rez.add(cmp.manager);
            for(Department dep : cmp.departments){
                rez.addAll(dep.angajati);
            }
        }
        return rez;
    }
    public Company getCompany(String name){

        for(Company companie : companii)
            if(name.compareTo(companie.nume)==0)
                return companie;
        return null;
    }
    public void add(Company company){
        companii.add(company);
    }
    public void add(User user){
        utilizatori.add(user);
    }

    public boolean remove(Company company){
        if(companii.contains(company)){
            companii.remove(company);
            return true;
        }
        return false;
    }
    public boolean remove(User user){
        if(utilizatori.contains(user)){
            utilizatori.remove(user);
            return true;
        }
        return false;
    }
    public ArrayList<Job> getJobs(List<String>companies){
        ArrayList<Job> rezultat= new ArrayList<>();
        for(String nume_comp:companies){
            Company comp=this.getCompany(nume_comp);
            rezultat.addAll(comp.getJobs());
        }
        return rezultat;
    }

    @Override
    public  String toString(){
       String s="";
       s = s + "APLICATIE" + "\n";
       s = s + "Utilizatori: ";
       if(utilizatori.size()!=0){
           int i;
           for(i=0;i<utilizatori.size()-1;i++) {
               s = s + utilizatori.get(i).rezumat.date_personale.getNume();
               s = s + " " + utilizatori.get(i).rezumat.date_personale
                       .getPrenume();
               s = s + ", ";
           }
           s = s + utilizatori.get(i).rezumat.date_personale.getNume();
           s = s + " " + utilizatori.get(i).rezumat.date_personale.getPrenume();
       }
       s = s + "\n" + "Companii: " + "\n\n";
       for(Company company : companii)
           s = s + company.toString() + "\n";
       return s;
    }
}
class Entity_info{
    public String name;
    public String email;
    public String phone;
    public String date_of_birth;
    public String genre;
    public ArrayList<String>languages;
    public ArrayList<String>languages_level;
    public Double salary;
    public ArrayList<String>interested_companies;
    public ArrayList<Education>education;
    public ArrayList<Experience>experience;
}
class Entities{
public ArrayList<Entity_info>employees;
public ArrayList<Entity_info>recruiters;
public ArrayList<Entity_info>users;
public ArrayList<Entity_info>managers;
}

class Cmp_Info{
    String name;
    String mng_name;
}

class Job_Info{
    String name;
    int free_pos;
    String comp_name;
    Double salary;
    Double grad_min;
    Double grad_max;
    Double exp_min;
    Double exp_max;
    Double grade_min;
    Double grade_max;
}

class  Known{
    String name;
    String where;
}

class Person{
    String name;
    String where;
    ArrayList<Known> known;
}

class The_rest{
    ArrayList<Cmp_Info>companies;
    ArrayList<Job_Info>jobs;
    ArrayList<Person>friends;
}