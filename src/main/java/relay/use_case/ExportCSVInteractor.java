package relay.use_case;

import relay.entity.AttendanceRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Use case layer

public class ExportCSVInteractor implements ExportCSVInputBoundary {
    final ExportCSVOutputBoundary outputBoundary;
    final ExportCSVDataAccessInterface exportCSVDataAccessInterface;


    // Corrected constructor syntax
    public ExportCSVInteractor(ExportCSVOutputBoundary outputBoundary, ExportCSVDataAccessInterface exportCSVDataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.exportCSVDataAccessInterface = exportCSVDataAccessInterface;
    }

    @Override
    public void execute(ExportCSVInputData inputData) {
        try {
            // Replace this with your actual logic to get attendance records
            List<AttendanceRecord> attendanceRecords = exportCSVDataAccessInterface.retreiveAttendanceRecords(inputData);

            // Implement the logic to export attendance records to CSV
            List<String[]> csvData = convertToCsvFormat(attendanceRecords);
            writeCsvFile(csvData, "attendance_records.csv");

            // Notify the output boundary (presenter) with the success result
            ExportCSVOutputData outputData = new ExportCSVOutputData(attendanceRecords);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle the exception
            String errorMessage = "Error exporting attendance records: " + e.getMessage();
            outputBoundary.prepareFailView(errorMessage);
        }
    }

    public static List<String[]> convertToCsvFormat(List<AttendanceRecord> attendanceRecords) {
        List<String[]> csvData = new ArrayList<>();

        // Add CSV header
        String[] header = {"StudentID", "CreatedAt"};
        csvData.add(header);

        return csvData;
    }

    public void writeCsvFile(List<String[]> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] rowData : data) {
                for (int i = 0; i < rowData.length; i++) {
                    writer.append(rowData[i]);
                    if (i < rowData.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            // Handle the exception appropriately (e.g., log or throw a custom exception)
            e.printStackTrace();
        }
    }

}




