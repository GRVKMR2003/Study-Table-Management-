

### ✅ `README.md`

```markdown
# 📚 Library Study Table Management System

A web-based application for managing study tables in a library. Admins can assign students to tables, monitor occupied and available tables, and free up tables when students leave — all through an intuitive interface.

---

## 🧩 Features

- View all study tables with real-time availability
- Assign students to tables
- Free up tables when students leave
- Auto-update table status (Occupied ↔ Available)
- Clean and interactive frontend
- RESTful API integration

---

## 🛠️ Tech Stack

| Layer         | Technology                |
|---------------|---------------------------|
| Frontend      | HTML, CSS, Vanilla JS     |
| Backend       | Spring Boot (Java)        |
| Database      | MySQL                     |
| ORM Framework | Spring Data JPA + Hibernate |

---

## 📁 Project Structure

```

study-management/
│
├── src/main/java/com/library/study/
│   ├── controller/StudyTableController.java
│   ├── service/StudyTableService.java
│   ├── model/StudyTable.java
│   └── repository/StudyTableRepository.java
│
├── src/main/resources/
│   ├── application.properties
│
├── static/
│   └── index.html
│
└── README.md

````

---

## 🚀 Getting Started

### Prerequisites

- Java 11+
- Maven
- MySQL

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/study-management.git
cd study-management
````

### 2️⃣ Configure MySQL

Create a database named:

```sql
CREATE DATABASE studymanagement;
```

### 3️⃣ Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost/studymanagement
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣ Run the Application

```bash
./mvnw spring-boot:run
```

The app will start at `http://localhost:8080`

---

## 🌐 API Endpoints

| Method | Endpoint                  | Description                     |
| ------ | ------------------------- | ------------------------------- |
| GET    | `/api/tables`             | Get all tables                  |
| GET    | `/api/tables/available`   | Get available tables            |
| GET    | `/api/tables/room/{room}` | Get tables by room              |
| GET    | `/api/tables/{id}`        | Get specific table by ID        |
| POST   | `/api/tables`             | Add a new study table           |
| PUT    | `/api/tables/{id}/assign` | Assign a student to a table     |
| PUT    | `/api/tables/{id}/free`   | Free up a table                 |
| DELETE | `/api/tables/{id}`        | Delete a table                  |
| GET    | `/api/tables/summary`     | Get table summary (total, etc.) |

---

## 📦 Sample SQL Data (Insert into MySQL)

```sql
INSERT INTO study_table (room_number, table_number, is_available, occupied, assigned_student)
VALUES
('A101', 1, true, false, NULL),
('A101', 2, true, false, NULL),
('A102', 3, true, false, NULL),
('A102', 4, true, false, NULL),
('A103', 5, true, false, NULL),
('A103', 6, true, false, NULL),
('A104', 7, true, false, NULL),
('A104', 8, true, false, NULL),
('B201', 9, true, false, NULL),
('B201', 10, true, false, NULL),
('B202', 11, true, false, NULL),
('B202', 12, true, false, NULL),
('B203', 13, true, false, NULL),
('B203', 14, true, false, NULL),
('B204', 15, true, false, NULL),
('B204', 16, true, false, NULL),
('C301', 17, true, false, NULL),
('C301', 18, true, false, NULL),
('C302', 19, true, false, NULL),
('C302', 20, true, false, NULL);
```

---

## 💡 Future Improvements

* Role-based admin login
* Student registration & login
* Table usage analytics (daily/weekly)
* Room occupancy heatmap view
* Dark mode toggle

---

## 🧑‍💻 Author

**Gaurav Kumar**
Java Developer | DSA Enthusiast | Member @GDSC
[GitHub](https://github.com/GrvKmr2003) • [LinkedIn](https://www.linkedin.com/in/gaurav_kumar)

---

## 📄 License

This project is licensed under the MIT License.

```
