package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Information {
    private String Nume;
    private String Prenume;
    private String Email;
    private String Telefon;
    private String Data_Nastere;
    private String Sex;
    private ArrayList<String>Limbi;
    private ArrayList<String>Experienta;
    Information(){
        Nume=null;
        Prenume=null;
        Email=null;
        Telefon=null;
        Data_Nastere=null;
        Sex=null;
        Limbi=null;
        Experienta=null;
    }
    Information(String Nume,String Prenume,String Email,String Telefon,
                String Data_Nastere, String Sex,ArrayList<String>Limbi,
                ArrayList<String>Experienta){
        this.Nume=Nume;
        this.Prenume=Prenume;
        this.Email=Email;
        this.Telefon=Telefon;
        this.Data_Nastere=Data_Nastere;
        this.Sex=Sex;
        this.Limbi=new ArrayList<String>(Limbi);
        this.Experienta=new ArrayList<String>(Experienta);
    }
    public void setNume(String Nume){
        this.Nume=Nume;
    }
    public String getNume(){
        return Nume;
    }
    public void setPrenume(String Prenume){
        this.Prenume=Prenume;
    }
    public String getPrenume(){
        return Prenume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return Objects.equals(Nume, that.Nume) &&
                Objects.equals(Prenume, that.Prenume) &&
                Objects.equals(Email, that.Email) &&
                Objects.equals(Telefon, that.Telefon) &&
                Objects.equals(Data_Nastere, that.Data_Nastere) &&
                Objects.equals(Sex, that.Sex) &&
                Objects.equals(Limbi, that.Limbi) &&
                Objects.equals(Experienta, that.Experienta);
    }


    public void setEmail(String Email){
        this.Email=Email;
    }
    public String getEmail(){
        return Email;
    }
    public void setTelefon(String Telefon){
        this.Telefon=Telefon;
    }
    public String getTelefon(){
        return Telefon;
    }
    public void setData_Nastere(String Data_Nastere){
        this.Data_Nastere=Data_Nastere;
    }
    public String getData_Nastere(){
        return Data_Nastere;
    }
    public void setSex(String Sex){
        this.Sex=Sex;
    }
    public String getSex(){
        return Sex;
    }
    public void setLimbi(ArrayList<String> Limbi){
        this.Limbi=Limbi;
    }
    public String getLimbi(){
        if(Limbi != null){
            String s="";
            for(int i=0; i < Limbi.size()-1;i++)
                s = s + Limbi.get(i) + ", ";
            s= s + Limbi.get(Limbi.size()-1);
            return s;
        }
        return null;
    }

    public ArrayList<String> getLimbiarr(){
        return Limbi;
    }
    public ArrayList<String> getExperientaarr(){
        return Experienta;
    }
    public void setExperienta(ArrayList<String> Experienta){
        this.Experienta=Experienta;
    }
    public String getExperienta(){
        if(Experienta != null){
            String s="";
            for(int i=0; i < Experienta.size()-1;i++)
                s = s + Experienta.get(i) + ", ";
            s= s + Experienta.get(Experienta.size()-1);
            return s;
        }
        return null;
    }
    public void addLimba(String Limba, String Nivel){
        Limbi.add(Limba);
        Experienta.add(Nivel);
    }
    public void removeLimba(String Limba){
        if(Limbi.contains(Limba)){
            int i=Limbi.indexOf(Limba);
            Experienta.remove(i);
            Limbi.remove(i);
        }
    }
}
