package relay.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public record AttendanceRecord (Student student, LocalDateTime createdAt) {

	public AttendanceRecord {
		Objects.requireNonNull(student);
		Objects.requireNonNull(createdAt);
	}

}
