package ConcurrentDataExporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsersExporter extends DataExporter {

    public UsersExporter(String fileName) {
        super(fileName);
    }

    @Override
    protected List<DataRecord> fetchData() {
        List<DataRecord> records = new ArrayList<>();
        String query = "SELECT * FROM mt24037_users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String index = resultSet.getString("index");
                String username = resultSet.getString("username");
                String identifier = resultSet.getString("identifier");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                records.add(new DataRecord(index, username, identifier, firstName, lastName));
            }
            System.out.println("Successfully mt24037_users table data into "+ fileName);
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
