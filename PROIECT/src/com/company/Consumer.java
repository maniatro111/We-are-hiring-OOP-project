package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Consumer {
    Resume rezumat;
    ArrayList<Consumer>lista_prieteni;

    Consumer(){
    }
    Consumer(Resume rezumat){
        this.rezumat = rezumat;
        lista_prieteni=new ArrayList<>();
    }

    public void add(Education education){
        rezumat.educatie.add(education);
    }

    public void add(Experience experience){
        rezumat.experienta.add(experience);
    }

    public void add(Consumer consumer){
        lista_prieteni.add(consumer);
    }

    public void remove(Consumer consumer){
        lista_prieteni.remove(consumer);
    }


    public Double meanGPA() {
        Double s=0.0;
        if(rezumat.educatie.size() != 0){
        for(Education ed : rezumat.educatie)
            s = s + ed.grade;
        s=s/rezumat.educatie.size();
        }
        return s;
    }

    public Integer getGraduationYear(){
        for(Education edu: rezumat.educatie){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
            if(edu.end_date!=null && edu.level.equals("college")){
                LocalDate End_Date=LocalDate.parse(edu.end_date,format);
                return Math.abs(End_Date.getYear());
            }
        }
        return 0;
    }

    public int getDegreeInFriendship(Consumer consumer){
        int degree=1;
        if(this.lista_prieteni.contains(consumer))
            return 1;
        else{
            ArrayList<Consumer> prieteni_neparcursi =
                    new ArrayList<>(lista_prieteni);
            ArrayList<Consumer> prieteni_parcursi=new ArrayList<>();
            ArrayList<Consumer> lista_aux=new ArrayList<>();
            prieteni_parcursi.add(this);

            while( prieteni_neparcursi.size()!=0 ){
                for(Consumer prieten : prieteni_neparcursi){
                    if(!prieteni_parcursi.contains(prieten)){
                    lista_aux.addAll(prieten.lista_prieteni);
                    prieteni_parcursi.add(prieten);
                    }
                }
                prieteni_neparcursi.clear();
                prieteni_neparcursi.addAll(lista_aux);
                lista_aux.clear();
                degree++;
                if( prieteni_neparcursi.contains(consumer))
                    return degree;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(rezumat, consumer.rezumat) &&
                Objects.equals(lista_prieteni, consumer.lista_prieteni);
    }

    @Override
    public String toString() {
        int i;
        String s="";
        s = s + rezumat.toString();
        if(lista_prieteni.size()!=0){
            s = s + "Prieteni: ";
            for(i=0;i<lista_prieteni.size()-1;i++) {
                s = s + lista_prieteni.get(i).rezumat.date_personale.getNume();
                s = s + " " + lista_prieteni.get(i).rezumat.date_personale
                        .getPrenume();
                s = s + ", ";
            }
            s = s + lista_prieteni.get(i).rezumat.date_personale.getNume();
            s = s + " " + lista_prieteni.get(i).rezumat.date_personale
                    .getPrenume();
        }
        s = s + "\n";
        return s;
    }
}
