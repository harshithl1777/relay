package relay.use_case;

import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.use_case.CsvFileGateway;

import java.util.List;

// Use case layer

public class ExportCSVInteractor implements ExportCSVInputBoundary {
    private ExportCSVOutputBoundary outputBoundary;
    private CsvFileGateway csvFileGateway;

    // Corrected constructor syntax
    public ExportCSVInteractor(ExportCSVOutputBoundary outputBoundary, CsvFileGateway csvFileGateway) {
        this.outputBoundary = outputBoundary;
        this.csvFileGateway = csvFileGateway;
    }

    @Override
    public void execute(ExportCSVInputData inputData) {
        try {
            // Replace this with your actual logic to get attendance records
            List<AttendanceRecord> attendanceRecords = retrieveAttendanceRecords(inputData);

            // Implement the logic to export attendance records to CSV
            List<String[]> csvData = convertToCsvFormat(attendanceRecords);
            csvFileGateway.writeCsvFile(csvData, "attendance_records.csv");

            // Notify the output boundary (presenter) with the success result
            ExportCSVOutputData outputData = new ExportCSVOutputData(attendanceRecords);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle the exception
            String errorMessage = "Error exporting attendance records: " + e.getMessage();
            outputBoundary.prepareFailView(errorMessage);
        }
    }


    private List<AttendanceRecord> retrieveAttendanceRecords(ExportCSVInputData inputData) {
        // Replace this with your actual logic to fetch attendance records
        // For example, you might query a database or call a service
        // to get the attendance records based on the provided input data.
        Course course = new Course();
         course.getHistory();
        return /* your logic to get attendance records */;
    }

    private List<String[]> convertToCsvFormat(List<AttendanceRecord> attendanceRecords) {
        // Implement logic to convert attendance records to CSV format
        // Return a list of string arrays representing CSV rows
        return null;
    }
}