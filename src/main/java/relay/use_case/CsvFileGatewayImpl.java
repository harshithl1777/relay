package relay.use_case;

// Infrastructure layer

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileGatewayImpl implements CsvFileGateway {

    @Override
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

    // Add other methods for reading or interacting with CSV files as needed
}

