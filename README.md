<h1 align="center">📊 SortMetrics</h1>

**SortMetrics** is a Java-based application designed to analyze, benchmark, and visualize the performance of various sorting algorithms. It features a **JavaFX** graphical user interface (GUI) for visualization and a console mode for batch analysis.

Results are persisted locally for historical comparison, and the project utilizes **Testcontainers** (via Docker) for reliable integration testing.

---

## 🚀 Core Features

### 🧮 Supported Algorithms
* **Bubble Sort**
* **Heap Sort**
* **Insertion Sort**
* **Merge Sort**
* **Quick Sort**
* **Selection Sort**
  <br>

### 📈 Functionality
* **Performance Analysis**: Measures execution time, swap counts, and other relevant metrics.
* **Data Visualization**:
    * **Bar Chart**: Side-by-side performance comparison of selected algorithms.
    * **Scatter Chart**: Visualizes performance trends across increasing input sizes.
* **Customizable Benchmarks**: Configure array sizes, random seeds, and iteration counts via `analysis.json`.
* **Data Persistence**: Automatically manages a local database (`analysis.db`) to store and retrieve benchmark history.

---

## 🛠️ Tech Stack & Tools

* **Language**: Java
* **Build Tool**: Maven
* **GUI Framework**: JavaFX (Controls, FXML)
* **Database**: 
    * **SQLite**: For local data persistence (`analysis.db`).
    * **PostgreSQL** (via Testcontainers): Used for integration testing.
* **Testing**: JUnit 5, Testcontainers.
* **Connection Pooling**: HikariCP.
* **JSON Processing**: Jackson.

---

## 📂 Project Structure

* `src/main/java/model/algoritmos`: Sorting algorithm implementations.
* `src/main/java/model/analise`: Benchmarking logic and metric collection.
* `src/main/java/DAO`: Data Access Objects for database interactions.
* `src/main/java/view`: JavaFX UI controllers (`AppLauncher`, `BarChart`, `ScatterChart`).
* `src/main/java/application`: Console entry point (`ConsoleRun`).

---

## ⚙️ Prerequisites

Ensure you have the following installed before running the project:

1.  **Java JDK 17+**
2.  **Maven**
3.  **Docker Desktop** (Required for running tests via **Testcontainers**)

---

## 💾 Database

The application utilizes a local database file named **`analysis.db`** created in the root directory.
* **Production**: The `DbManager` handles connections via a HikariCP pool.
* **Testing**: Docker containers are spun up dynamically to test database logic without polluting your local file.

---

## 🏃‍♂️ How to Run

### Check Docker Status and build with maven
Since the build process runs tests using Testcontainers, verify Docker is running:
```bash
docker info
mvn clean javafx:run
```
