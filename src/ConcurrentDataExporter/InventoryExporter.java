package ConcurrentDataExporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InventoryExporter extends DataExporter {

    public InventoryExporter(String fileName) {
        super(fileName);
    }

    @Override
    protected List<DataRecord> fetchData() {
        List<DataRecord> records = new ArrayList<>();
        String query = "SELECT * FROM mt24037_inventory_table";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String itemId = resultSet.getString("item_id");
                String itemName = resultSet.getString("item_name");
                String quantity = resultSet.getString("quantity");
                String source = resultSet.getString("source");
                String lastUpdated = resultSet.getString("last_updated");
                String price = resultSet.getString("price");
                records.add(new DataRecord(itemId, itemName, quantity, source, lastUpdated, price));
            }
            System.out.println("Successfully mt24037_inventory_table table data into "+ fileName);

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
