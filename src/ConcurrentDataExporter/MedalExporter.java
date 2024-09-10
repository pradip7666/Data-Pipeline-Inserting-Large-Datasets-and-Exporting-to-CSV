package ConcurrentDataExporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MedalExporter extends DataExporter {

    public MedalExporter(String fileName) {
        super(fileName);
    }

    @Override
    protected List<DataRecord> fetchData() {
        List<DataRecord> records = new ArrayList<>();
        String query = "SELECT * FROM mt24037_medal";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String game = resultSet.getString("game");
                String gold = resultSet.getString("gold");
                String silver = resultSet.getString("silver");
                String bronze = resultSet.getString("bronze");
                records.add(new DataRecord(game, gold, silver, bronze));
            }
            System.out.println("Successfully mt24037_medal table data into "+ fileName);

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
