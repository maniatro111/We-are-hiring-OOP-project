package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Job {
    String job_name;
    String company_name;
    boolean flag_este_plin;
    Constraint anul_abs;
    Constraint nr_ani_exp;
    Constraint media_academica;
    ArrayList<User>candidati;
    int nr_joburi_libere;
    Double salariu;
    Job(){

    }
    Job(String job_name,String company_name,Constraint anul_abs,
        Constraint nr_ani_exp, Constraint media_academica,
        int nr_joburi_libere,Double salariu){
        this.job_name=job_name;
        this.company_name=company_name;
        flag_este_plin=false;
        this.anul_abs=anul_abs;
        this.nr_ani_exp=nr_ani_exp;
        this.media_academica=media_academica;
        this.candidati=new ArrayList<>();
        this.nr_joburi_libere= nr_joburi_libere;
        this.salariu=salariu;
    }
    public void apply(User user){
        if(user.companii.contains(company_name)) {
            Application app = Application.getInstance();
            Company comp = app.getCompany(company_name);
            if (!flag_este_plin) {
                Recruiter rec = comp.getRecruiter(user);
                rec.evaluate(this, user);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return flag_este_plin == job.flag_este_plin &&
                nr_joburi_libere == job.nr_joburi_libere &&
                Objects.equals(job_name, job.job_name) &&
                Objects.equals(company_name, job.company_name) &&
                Objects.equals(anul_abs, job.anul_abs) &&
                Objects.equals(nr_ani_exp, job.nr_ani_exp) &&
                Objects.equals(media_academica, job.media_academica) &&
                Objects.equals(candidati, job.candidati) &&
                Objects.equals(salariu, job.salariu);
    }


    public boolean meetsRequirments(User user){
        if(user.getGraduationYear() < anul_abs.inf_limit||
                user.getGraduationYear() > anul_abs.sup_limit)
            return false;
        if(user.meanGPA() < media_academica.inf_limit ||
                user.meanGPA() > media_academica.sup_limit)
            return false;
        Double score=0.0;
        for(Experience ex: user.rezumat.experienta){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate End_Date=LocalDate.parse(ex.end_date,format);
            LocalDate Start_Date=LocalDate.parse(ex.start_date,format);
            Period duration = Period.between(End_Date,Start_Date);
            Integer ani=Math.abs(duration.getYears());
            if(duration.getDays()!=0||duration.getMonths()!=0)
                ani=ani+1;
            score=score+ani;
        }
        if(score < nr_ani_exp.inf_limit || score > nr_ani_exp.sup_limit)
            return false;
        return true;
    }

    public String toString(){
        return job_name + ": " + company_name;
    }
}
