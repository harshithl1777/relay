package com.blitz.backend.entity;

public class Student implements User {
    String firstName;
    String lastName;
    String emailAddress;

    String studentNumber;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void getFullName(String fullName){

    }
    public static void sendEmailConfirmation(){
    }


    public static void validateStudent(){

    }
    public static void updateEmailAddress(){
    }


}

