package relay.use_case;

import java.util.List;

public interface CsvFileGateway {
    void writeCsvFile(List<String[]> data, String filePath);
    // Add any other methods related to reading or interacting with CSV files
}

