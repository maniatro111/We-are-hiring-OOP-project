package com.company;

import java.util.ArrayList;

public class Marketing extends Department{
    public Marketing(String name) {
        super(name);
    }

    @Override
    public double getTotalSalaryBudget() {
        double salariu=0.0;
        for(Employee var : angajati){
            double aux= var.Salariu;
            if( aux > 5000.0)
                aux = aux * 11 / 10;
            if( aux <=5000.0 && aux > 3000.0)
                aux = aux * 116 / 100;
            salariu=salariu+aux;
        }
        return salariu;
    }
}
