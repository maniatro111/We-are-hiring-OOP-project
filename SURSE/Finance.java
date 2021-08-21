package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Finance extends Department{
    public Finance(String name) {
        super(name);
    }

    @Override
    public double getTotalSalaryBudget() {
        double salariu=0.0;

        for(Employee var : angajati){

            double aux= var.Salariu;
            LocalDate acum =LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter
                    .ofPattern("d.MM.yyyy");
            LocalDate Start_Date=LocalDate
                    .parse(var.rezumat.getLastExp(), format);
            Period vechime = Period.between(Start_Date,acum);
            int ani_vechime=vechime.getYears();
            if(ani_vechime<1)
                aux = aux * 110 / 100;
            else
                aux = aux * 116 / 100;

            salariu=salariu+aux;
        }
        return salariu;
    }
}
