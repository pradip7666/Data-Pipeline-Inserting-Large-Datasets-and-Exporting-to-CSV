package ConcurrentDataExporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Table3Exporter extends DataExporter {

    public Table3Exporter(String fileName) {
        super(fileName);
    }

    @Override
    protected List<DataRecord> fetchData() {
        List<DataRecord> records = new ArrayList<>();
        String query = "SELECT * FROM mt24037tble3";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");
                records.add(new DataRecord(studentId, name, gender, age));
            }
            System.out.println("Successfully mt24037tble3 table data into "+ fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    private Connection getConnection() throws Exception {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}
