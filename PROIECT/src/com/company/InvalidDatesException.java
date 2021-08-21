package com.company;

public class InvalidDatesException extends Exception{
    InvalidDatesException(){
        super("Datele introduse nu sunt corecte");
    }
}
