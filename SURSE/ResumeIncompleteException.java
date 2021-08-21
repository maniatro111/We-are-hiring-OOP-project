package com.company;

public class ResumeIncompleteException extends Exception{
    ResumeIncompleteException(){
        super("Resume-ul nu este complet!");
    }
}
