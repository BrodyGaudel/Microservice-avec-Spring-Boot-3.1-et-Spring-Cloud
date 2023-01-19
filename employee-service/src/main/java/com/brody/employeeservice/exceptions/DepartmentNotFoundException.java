package com.brody.employeeservice.exceptions;

public class DepartmentNotFoundException extends Exception{

    public DepartmentNotFoundException(String message){
        super(message);
    }
}
