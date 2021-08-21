package com.company;

public class Employee extends Consumer{
    String nume_comp;
    Double Salariu;
    Employee(){

    }
    Employee(User user){
        this.rezumat=user.rezumat;
        this.lista_prieteni= user.lista_prieteni;
    }
    Employee(Resume rezumat){
        super(rezumat);
    }

    //Metoda care intoarece numele companiei unui angajat, care lucreaza de la
    //inceput intr-o companie.
    public String getNume_comp(){
        for(Experience exp : rezumat.experienta){
            if(exp.end_date == null)
                return exp.company;
        }
        return this.rezumat.experienta.first().company;
    }

    //Metoda care intoarece numele departamentului unui angajat, care lucreaza de la
    //inceput intr-o companie.
    public String getDep(){
        for(Experience exp : rezumat.experienta){
            if(exp.end_date == null)
                return exp.department;
        }
        return null;
    }


}
