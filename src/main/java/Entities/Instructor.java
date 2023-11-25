package Entities;

public class Instructor implements User {
    private String firstName;
    private String lastName;
    private String email;
    private String[] courses;

    // Constructor for Instructor
    public Instructor(String firstName, String lastName, String email, String[] courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = courses;
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

    public String[] getCourses() {
        return courses;
    }

    // ...
}