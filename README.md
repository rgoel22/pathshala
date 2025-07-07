# PathShala Backend

This is the Spring Boot backend service for the PathShala platform, supporting digital course management. It provides RESTful APIs to handle users, courses, assignments, study materials, and submissions for both students and teachers.

---

## 🧩 Project Structure

```
pathshala-master/
│
├── src/main/java/com/pathshala/
│   ├── controller/        # API endpoints for all modules
│   ├── dao/               # Entity models for JPA/Hibernate
│   ├── config/            # Spring configurations
│   └── PathShalaApplication.java
│
├── pom.xml                # Maven build file
├── mvnw / mvnw.cmd        # Maven wrapper
└── system.properties      # System-level configs
```

---

## 🚀 Features

- 🧑‍🏫 Teacher dashboard to manage courses and grading
- 📝 Student assignment submission flow
- 📁 Upload & download study materials
- 📬 Email-based notifications
- 🔐 Role-based access control (e.g., student, teacher)
- 🧪 REST APIs with Spring MVC
- 🗃️ Data persistence using JPA & SQL

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot
- **Persistence:** Spring Data JPA (MySQL/PostgreSQL compatible)
- **Build Tool:** Maven
- **Email:** JavaMailSender (Spring Boot Starter Mail)

---

## ⚙️ Setup Instructions

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL / PostgreSQL / H2
- Git

### Run the Application

```bash
# Clone the repo
git clone https://github.com/rgoel22/pathshala.git
cd pathshala-master

# Build the project
./mvnw clean install

# Run the app
./mvnw spring-boot:run
```

App will be available at:  
`http://localhost:8080`

---

## 🧪 API Access

- All endpoints available under `/api/**`
- Swagger UI (if enabled): `http://localhost:8080/swagger-ui.html`

---

## 🌐 Frontend Integration

Pair this backend with the React frontend:  
[PathShala UI Repository](https://github.com/rgoel22/pathshala-ui)

---

