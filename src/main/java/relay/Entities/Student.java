package relay.Entities;

public class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private String emailAddress;

    // Constructor for Student
    public Student(String firstName, String lastName, String studentID, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.emailAddress = emailAddress;
    }

    // Implementing methods from User interface

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return emailAddress;
    }

    public String getStudentNumber() {
        return studentID;
    }


}


