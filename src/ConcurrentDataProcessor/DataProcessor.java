package ConcurrentDataProcessor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class DataProcessor extends Thread {
    String filePath;
    String tableName;

    public DataProcessor(String csvFile, String tablename) {
        this.filePath = csvFile;
        this.tableName = tablename;
    }

    @Override
    public void run() {
        try (Connection connection = getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath));
             Statement statement = connection.createStatement()) {
            // seperate the each column in csv record and store its value
            String line;
            line = br.readLine();
            int j=0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String sql = String.format("INSERT INTO %s (item_id, item_name, quantity, source, last_updated, price) " +
                                "VALUES (%d, '%s', %d, '%s', '%s', %.2f)",
                        tableName,Integer.parseInt(fields[0]), fields[1],Integer.parseInt(fields[2]),fields[3],fields[4],Double.parseDouble(fields[5]));
                statement.executeUpdate(sql);
                j++;
                System.out.println("thread no :" + Thread.currentThread().getId()+"   inserted the "+ j + " row.");
            }
            System.out.println("Completed thread no :" + + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
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


