package ConcurrentDataExporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class DataExporter implements Runnable {
    protected String fileName;

    public DataExporter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread no : "+ Thread.currentThread().getId() + " Running");
            List<DataRecord> data = fetchData();
            writeToFile(data);
            System.out.println("Thread no : "+ Thread.currentThread().getId() + "done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract List<DataRecord> fetchData();

    private void writeToFile(List<DataRecord> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (DataRecord record : data) {
                writer.write(record.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
