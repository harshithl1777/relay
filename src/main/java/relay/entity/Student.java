package relay.entity;

import java.util.Objects;

public record Student(String firstName, String lastName, String studentID, String emailAddress) {
	public Student {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		Objects.requireNonNull(studentID);
		Objects.requireNonNull(emailAddress);
	}
}
