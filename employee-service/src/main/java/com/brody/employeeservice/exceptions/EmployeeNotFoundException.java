package com.brody.employeeservice.exceptions;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
