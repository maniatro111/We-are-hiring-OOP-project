package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class User extends Consumer implements Observer {
    ArrayList<String> companii;
    ArrayList<String>notificari;
    User(){

    }


    User(Resume rezumat,ArrayList<String> companii){
        super(rezumat);
        this.companii=companii;
        notificari=new ArrayList<>();
    }

    public Employee convert(){
        Employee angajat= new Employee(this);
        return angajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(companii, user.companii);
    }


    public Double getTotalScore(){
        Double score=0.0;
        for(Experience ex: rezumat.experienta){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate Start_Date=LocalDate.parse(ex.start_date,format);
            if(ex.end_date!=null){
                LocalDate End_Date=LocalDate.parse(ex.end_date,format);
            Period duration = Period.between(Start_Date,End_Date);
            Integer ani=Math.abs(duration.getYears());
            if(duration.getDays()!=0||duration.getMonths()!=0)
                ani=ani+1;
            score=score+ani;
            }
            else {
                LocalDate azi= LocalDate.now();
                Period duration=Period.between(Start_Date,azi);
                Integer ani=Math.abs(duration.getYears());
                if(duration.getDays()!=0||duration.getMonths()!=0)
                    ani=ani+1;
                score=score+ani;
            }
        }
        score=score * 1.5;
        score=score+this.meanGPA();
        return score;
    }

    @Override
    public void update(String name) {
        notificari.add(name);
    }
}
