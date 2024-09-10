package ConcurrentDataProcessor;
public class Main {
    public static void main(String[] args) {
        String[] csvFiles = {
                "/home/mt24037/IdeaProjects/UserStory1/data/inventory_batch1.csv",
                "/home/mt24037/IdeaProjects/UserStory1/data/inventory_batch2.csv",
                "/home/mt24037/IdeaProjects/UserStory1/data/inventory_batch3.csv",
                "/home/mt24037/IdeaProjects/UserStory1/data/inventory_batch4.csv"
        };
        String tableName= "mt24037_inventory_table";

        // start a thread for each CSV file
        DataProcessor[] threads = new DataProcessor[csvFiles.length];
        for (int i = 0; i < csvFiles.length; i++) {
            threads[i] = new DataProcessor(csvFiles[i],tableName);
            threads[i].start();
        }

        // Wait for all threads to complete
        for (DataProcessor thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All files have been processed.");
    }
}
