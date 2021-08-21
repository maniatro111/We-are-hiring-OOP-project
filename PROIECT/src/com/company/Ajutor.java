package com.company;

import java.util.ArrayList;

public class Ajutor {
    public User retUser(String nume, String prenume, ArrayList<User> yup){
        for(User da : yup)
            if(da.rezumat.date_personale.getNume().compareTo(nume)==0 &&
                    da.rezumat.date_personale.getPrenume().compareTo(prenume)==0)
                return da;
        return null;
    }

    public Employee retEmp(String nume, String prenume, ArrayList<Employee> yup){
        for(Employee da : yup)
            if(da.rezumat.date_personale.getNume().compareTo(nume)==0 &&
                    da.rezumat.date_personale.getPrenume().compareTo(prenume)==0)
                return da;

        return null;
    }

    public Recruiter retRec(String nume, String prenume, ArrayList<Recruiter> yup){
        for(Recruiter da : yup)
            if(da.rezumat.date_personale.getNume().compareTo(nume)==0 &&
                    da.rezumat.date_personale.getPrenume().compareTo(prenume)==0)
                return da;
        return null;
    }
    public Manager retMan(String nume, String prenume, ArrayList<Manager> yup){
        for(Manager da : yup)
            if(da.rezumat.date_personale.getNume().compareTo(nume)==0 &&
                    da.rezumat.date_personale.getPrenume().compareTo(prenume)==0)
                return da;
        return null;
    }
}
