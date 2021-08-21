package com.company;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class Resume{
    Information date_personale;
    TreeSet<Education> educatie;
    TreeSet<Experience> experienta;
    private Resume(ResumeBuilder builder) throws ResumeIncompleteException {
        date_personale=builder.date_personale;
        educatie= builder.educatie;
        experienta= builder.experienta;
        if(date_personale==null || date_personale.getNume()==null||
                date_personale.getPrenume()==null||
                date_personale.getTelefon()==null||date_personale
                .getData_Nastere()==null||(educatie.size()<1))
            throw new ResumeIncompleteException();
    }
    Resume(){
        date_personale=new Information();
        educatie=new TreeSet<>();
        experienta=new TreeSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(date_personale, resume.date_personale) &&
                Objects.equals(educatie, resume.educatie) && Objects
                .equals(experienta, resume.experienta);
    }

    //Metoda care intoarce ultima experienta ( experienta la care End_date-ul
    //este null)
    public String getLastExp(){
        for(Experience var : experienta)
            if(var.end_date == null)
                return var.start_date;
        return null;
    }

    @Override
    public String toString(){
        String s= "";
        s = s + "Nume: " + date_personale.getNume() + "\n" +
                "Prenume: " + date_personale.getPrenume() + "\n" +
                "Email: " + date_personale.getEmail() + "\n" +
                "Telefon: " + date_personale.getTelefon() + "\n" +
                "Data_Nastere: " + date_personale.getData_Nastere() + "\n" +
                "Sex: " + date_personale.getSex() + "\n" +
                "Limbi cunscute: " + date_personale.getLimbi() + "\n" +
                "Experienta in limbi: " + date_personale.getExperienta()+ "\n";
        for(Education ed: educatie)
            s= s + ed.toString();
        for(Experience exp: experienta)
            s=s + exp.toString();
        return s;
    }
    public static class ResumeBuilder{
        Information date_personale;
        TreeSet<Education> educatie;
        TreeSet<Experience> experienta;
        public ResumeBuilder(){
            date_personale=new Information();
            educatie=new TreeSet<>();
            experienta =new TreeSet<>();
        }
        public ResumeBuilder last_name(String name){
            date_personale.setNume(name);
            return this;
        }
        public ResumeBuilder first_name(String name){
            date_personale.setPrenume(name);
            return this;
        }
        public ResumeBuilder Email(String Email){
            date_personale.setEmail(Email);
            return this;
        }
        public ResumeBuilder Telefon(String Telefon){
            date_personale.setTelefon(Telefon);
            return this;
        }
        public ResumeBuilder Data_nastere(String Data_nastere){
            date_personale.setData_Nastere(Data_nastere);
            return this;
        }
        public ResumeBuilder Sex(String sex){
            date_personale.setSex(sex);
            return this;
        }
        public ResumeBuilder Limbi(ArrayList<String>Limbi,ArrayList<String>Exp){
            date_personale.setLimbi(Limbi);
            date_personale.setExperienta(Exp);
            return this;
        }
        public ResumeBuilder Education(TreeSet<Education> edu){
            educatie=edu;
            return this;
        }
        public ResumeBuilder Experience(TreeSet<Experience>exp){
            experienta=exp;
            return this;
        }
        public Resume build() throws ResumeIncompleteException {
            return new Resume(this);
        }
    }
}