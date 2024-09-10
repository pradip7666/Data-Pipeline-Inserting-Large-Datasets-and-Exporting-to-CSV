package ConcurrentDataExporter;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new UsersExporter("/home/mt24037/IdeaProjects/UserStory1/Output_csv_files/users.csv"));
        Thread t2 = new Thread(new InventoryExporter("/home/mt24037/IdeaProjects/UserStory1/Output_csv_files/inventory.csv"));
        Thread t3 = new Thread(new Table3Exporter("/home/mt24037/IdeaProjects/UserStory1/Output_csv_files/table3.csv"));
        Thread t4 = new Thread(new MedalExporter("/home/mt24037/IdeaProjects/UserStory1/Output_csv_files/medals.csv"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All exports complete.");
    }
}
