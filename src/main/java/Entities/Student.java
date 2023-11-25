package Entities;

public class Student implements User {
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String email;

    // Constructor for Student
    public Student(String firstName, String lastName, String studentNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.email = email;
    }

    // Implementing methods from User interface
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

}


