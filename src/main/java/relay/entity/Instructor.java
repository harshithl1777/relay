package relay.entity;

import java.util.HashMap;
import java.util.Map;

public class Instructor {
	private String instructorID;
	private String firstName;
	private String lastName;
	private String emailAddress;

	public Instructor(String firstName, String lastName, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	/**
	 * Empty constructor required by certain data access interface
	 * implementations. Do not remove, it will cause breaking changes.
	 */
	public Instructor() {
	}

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> instructorMap = new HashMap<>();
		instructorMap.put("firstName", firstName);
		instructorMap.put("lastName", lastName);
		instructorMap.put("emailAddress", emailAddress);
		return instructorMap;
	}

}
