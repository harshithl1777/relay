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


    public static void sendEmailConfirmation() {
    }

    public static void updateEmailAddress() {
    }
}


