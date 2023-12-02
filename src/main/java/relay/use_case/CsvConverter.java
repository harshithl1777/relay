package relay.use_case;

import relay.entity.AttendanceRecord;
import relay.entity.Student;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class CsvConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private CsvConverter() {
        // Private constructor to prevent instantiation
    }

    public static List<String[]> convertToCsvFormat(List<AttendanceRecord> attendanceRecords) {
        List<String[]> csvData = new ArrayList<>();

        // Add CSV header
        String[] header = {"StudentID", "CreatedAt"};
        csvData.add(header);

        // Convert attendance records to CSV format
        for (AttendanceRecord record : attendanceRecords) {
            Student student = record.getStudent();
            LocalDateTime createdAt = record.getCreatedAt();

            String studentID = (student != null) ? student.getStudentID() : "";
            String createdAtStr = (createdAt != null) ? createdAt.format(DATE_TIME_FORMATTER) : "";

            String[] row = {studentID, createdAtStr};
            csvData.add(row);
        }

        return csvData;
    }

    public static void main(String[] args) {
        // Example usage
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        // Populate attendanceRecords with actual data...

        List<String[]> csvData = convertToCsvFormat(attendanceRecords);

        // Print the CSV data
        for (String[] row : csvData) {
            System.out.println(String.join(",", row));
        }
    }
}