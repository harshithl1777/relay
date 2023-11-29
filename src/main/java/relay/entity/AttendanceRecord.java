package relay.entity;

import java.time.LocalDateTime;

public class AttendanceRecord {
	private Student student;
	private LocalDateTime createdAt;

	public AttendanceRecord(String studentID, LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Student getStudent() {
		return student;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
