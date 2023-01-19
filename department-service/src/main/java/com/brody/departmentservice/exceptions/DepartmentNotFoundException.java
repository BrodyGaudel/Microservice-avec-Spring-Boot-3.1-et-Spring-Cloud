package com.brody.departmentservice.exceptions;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
