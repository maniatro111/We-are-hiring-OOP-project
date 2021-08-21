package com.company;

public class Recruiter extends Employee{
    Double rating=5.0;

    public Recruiter() {
    }

    public Recruiter(User user) {
        super(user);
    }

    public Recruiter(Resume rezumat) {
        super(rezumat);
    }


    public int evaluate(Job job, User user){
        Double Score = rating*user.getTotalScore();
        rating=rating+0.1;
        if(job.meetsRequirments(user)){
            Request cerere =new Request(job,user,this,Score);
            Application app=Application.getInstance();
            Company comp=app.getCompany(job.company_name);
            comp.manager.cereri.add(cerere);
        }
        else{
            user.update("Nu va incadrati in cerintele necesare pentru " +
                    "jobul de " + job.job_name + ", compania " + job.company_name);
        }
        return (int)Math.floor(Score);
    }
}
