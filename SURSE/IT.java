package com.company;

public class IT extends Department{
    IT(String name){
        super(name);
    }

    @Override
    public double getTotalSalaryBudget() {
        double salariu=0.0;
        for(Employee var :angajati){
            salariu=salariu+var.Salariu;
        }
        return salariu;
    }
}
