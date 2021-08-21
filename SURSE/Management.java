package com.company;

import java.util.ArrayList;

public class Management extends Department{
    Management(String name){
        super(name);
    }

    @Override
    public double getTotalSalaryBudget() {
        double salariu=0.0;
        for(Employee var : angajati)
            salariu=salariu+var.Salariu;
        salariu=salariu*116/100;
        return salariu;
    }
}
