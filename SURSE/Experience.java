package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience implements Comparable{

    String start_date;
    String end_date;
    String position;
    String department;
    String company;
    Experience(String start_date, String end_date, String position,
               String department, String company) throws InvalidDatesException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate Start_Date=LocalDate.parse(start_date, format);
        LocalDate End_Date = null;
        this.position = position;
        this.department = department;
        this.start_date = start_date;
        this.company=company;

        if(end_date!=null){
            this.end_date=end_date;
            End_Date=LocalDate.parse(end_date,format);
        }
        else
            this.end_date=null;
            if(this.end_date!=null && Start_Date.compareTo(End_Date)>0)
                throw new InvalidDatesException();
    }
    @Override
    public int compareTo(Object o) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
        Experience aux=(Experience) o;

        if(this.end_date == null || aux.end_date == null)
            if(this.company.compareTo(aux.company)!=0)
                return this.company.compareTo(aux.company);
            else
                return 1;
        else{

            LocalDate End_Date_1 = LocalDate.parse(this.end_date,format);
            LocalDate End_Date_2 = LocalDate.parse(aux.end_date,format);

            if(End_Date_1.compareTo(End_Date_2)>0)
                return -1;
            if(End_Date_1.compareTo(End_Date_2)<0)
                return 1;
            if(End_Date_1.equals(End_Date_2))
                return this.company.compareTo(aux.company);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Experience : " +
                "Start_Date=" + start_date +
                ", End_Date=" + end_date +
                ", Company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + "'" + '\n';
    }
}
