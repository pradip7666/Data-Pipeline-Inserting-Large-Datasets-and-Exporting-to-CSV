## Data-Processing-And-Exporting

## Description

**Data-Processing-And-Exporting** is a Java project for handling data tasks using multi-threading. It includes:

1. **Data Processing:** Inserts large amounts of data into a PostgreSQL database using multiple threads.
2. **Data Exporting:** Exports data from PostgreSQL tables to CSV files using multi-threading.

## Features

- **Multi-threaded data insertion** into PostgreSQL.
- **Parallel data exporting** to CSV files.
- **Efficient resource management** and thread safety.

## Getting Started

### Prerequisites

- Java 11 or later
- PostgreSQL database
- PostgreSQL JDBC Driver

### Setup

1. **Clone the Repository:**

  Command:
   git clone https://github.com/pradip7666/Data-Processing-And-Exporting.git
   cd Data-Processing-And-Exporting



2. **Configure Database Connection:**

   Update 'src/DataProcessor.java' and 'src/DataExporter.java' with your database details.

3. **Add JDBC Driver:**

   Place the PostgreSQL JDBC driver in the `lib` directory.

4. **Build the Project:**

   Command:
   javac -cp .:lib/postgresql.jar src/*.java
   

5. **Run the Application:**

   Command
   java -cp .:lib/postgresql.jar src/Main
 

### Database Setup

1. **Create the Inventory Table:**

   sql command:
   CREATE TABLE inventory_table (
       id SERIAL PRIMARY KEY,
       product_name VARCHAR(255) NOT NULL,
       quantity INT NOT NULL,
       price DECIMAL(10, 2) NOT NULL
   );
   

2. **Set Up Export Tables:**

   Create and populate tables in PostgreSQL for data exporting.


## Contact

For questions, email [alnepradip31@gmail.com](mailto:alnepradip31@gmail.com).
