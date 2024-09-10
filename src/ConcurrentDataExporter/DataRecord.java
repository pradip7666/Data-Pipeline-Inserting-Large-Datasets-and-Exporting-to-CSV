package ConcurrentDataExporter;

public class DataRecord {
    private String[] columns;

    public DataRecord(String... columns) {
        this.columns = columns;
    }

    public String toCSV() {
        return String.join(",", columns);
    }
}
