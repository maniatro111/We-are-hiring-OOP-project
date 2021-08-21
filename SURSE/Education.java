package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Education implements Comparable{
    String level;
    String name;
    String start_date;
    String end_date;
    Double grade;
    Education(String start_date, String end_date, String name, String level,
              Double grade)throws InvalidDatesException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate Start_Date=LocalDate.parse(start_date, format);
            LocalDate End_Date = null;
            this.start_date=start_date;
        if(end_date!=null){
            this.end_date=end_date;
            End_Date=LocalDate.parse(end_date,format);
        }
        else
            this.end_date=null;
            this.name = name;
            this.level = level;

            if(this.end_date!=null && Start_Date.compareTo(End_Date)>0)
                throw new InvalidDatesException();
            this.grade=grade;

    }
    @Override
    public int compareTo(Object o) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
        Education aux=(Education) o;
        LocalDate Start_Date_1 = LocalDate.parse(this.start_date, format);

        LocalDate Start_Date_2 = LocalDate.parse(aux.start_date, format);

        if(this.end_date == null || aux.end_date == null){
            if(Start_Date_1.compareTo(Start_Date_2)>0){
                return 1;
            }
            if(Start_Date_1.compareTo(Start_Date_2)<0)
                return -1;
            if(Start_Date_1.equals(Start_Date_2)){
                return aux.grade.compareTo(this.grade);
            }
        }
        else{
            LocalDate End_Date_1 = LocalDate.parse(this.end_date,format);
            LocalDate End_Date_2 = LocalDate.parse(aux.end_date,format);
            if(End_Date_1.compareTo(End_Date_2)>0)
                return -1;
            if(End_Date_1.compareTo(End_Date_2)<0)
                return 1;
            if(End_Date_1.equals(End_Date_2))
                return aux.grade.compareTo(this.grade);
        }
        return 0;
    }

    @Override
    public String toString() {
        return  "Start_Date=" + start_date +
                ", End_Date=" + end_date +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", grade='" + grade + "'" + '\n';
    }
}
