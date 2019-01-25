/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.event;

/**
 *
 * @author Shreyas Patil
 */
public class DatabaseEvent {
    private String message ;
    private String operation ;
    private Exception exception; 


    public DatabaseEvent(String message, String operation) {
        this.message = message;
        this.operation = operation;
    }
    
     public DatabaseEvent(String message, String operation, Exception exception) {
        this.message = message;
        this.operation = operation;
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public String getOperation() {
        return operation;
    }
    
    public Exception getException(){
        return exception;
    }
    
    public void printStackTrace(){
        exception.printStackTrace();
    }

    @Override
    public String toString() {
        String string = "\n=================================";
        string = string + "\nOperation : "+ operation +"\nMessage : "+message;
        string = string + "\n=================================";
       
        return string;
    }
    
    
}
