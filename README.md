# Travel4Real - Backend

This repository houses the backend infrastructure for the **Travel4Real**, a web application dedicated to connecting travelers with authentic local experiences. Our platform enables locals to share and manage their unique cultural experiences while allowing travelers to discover genuine, off-the-beaten-path adventures.

---

## Backend Functionalities

This backend provides the following functionalities to support the Travel4Real platform:

### User Management
* **User registration** 📝 and **authentication** ✅.

### Experience Management
* **Creation of new experiencies** 
* **Retrieval of experience details** 🔍
* **Updating existing experience information** ✏️
* **Deletion of experiences** 🗑️

### Search
* **Search funcionality**, including filters of location and category.

### API Endpoints
* Provides a set of **API endpoints** 🌐 for communication with the frontend application.
* Supports standard **HTTP methods** (GET ➡️, POST 📤, PUT 💾, DELETE 🗑️) for data manipulation.

### Security
* Implements **security best practices** 🛡️ to protect user data and prevent unauthorized access 🚫.

---

## 🛠️ Technologies and Tools

* **Programming Language**: Java 21
* **Backend Framework**: Spring Boot 3.4.4
* **Data Persistence**: Spring Data JPA
* **Security**: Spring Security (JWT)
* **Database**: PostgreSQL / H2
* **IDE**: Visual Studio Code
* **Project Management**: Trello
* **Version Control**: Git - GitHub
* **API Client**: Postman / Swagger

---

## ⚙️ Environment Setup and Running the Application

1.  **PostgreSQL (Docker)**:
    * Ensure PostgreSQL is running as a Docker container (port: `54321`).
    * `pgAdmin` (Docker container) is available at `http://localhost:8080`.

2.  **Spring Boot Setup**:
    * Create your `.env` file from the example and fill in your desired values:
        ```bash
        cp .env.example .env
        ```
    * Run the Docker containers:
        ```bash
        docker compose up -d
        ```
    * Build the project with Maven (skips tests):
        ```bash
        mvn clean install -DskipTests
        ```
    * Run the Spring Boot application and visit `http://localhost:8080`:
        ```bash
        mvn spring-boot:run
        ```
---

## 📄 .env Example

Before running the application, make sure to configure your environment variables. Use the following example as a reference:

.env.example

DATABASE_URL=jdbc:postgresql://localhost:54321/travel
DATABASE_USERNAME=sameAsPostgresUser
DATABASE_PASSWORD=sameAsPostgresPassword

POSTGRES_HOST=localhost
POSTGRES_USER=sameAsPostgresUser
POSTGRES_PASSWORD=sameAsPostgresPassword
POSTGRES_DB=travel
POSTGRES_PORT=54321

PGADMIN_DEFAULT_EMAIL=yourname@email.com
PGADMIN_DEFAULT_PASSWORD=


💡 **Nota**: Cambia los valores `sameAsPostgresUser` y `sameAsPostgresPassword` por tus credenciales reales. No dejes el campo `PGADMIN_DEFAULT_PASSWORD` vacío si planeas usar pgAdmin de forma segura.

---

### Swagger
API documentation is provided through Swagger and can be found at `http://localhost:8080/swagger-ui/`.

---

## 👥 Authors


<table style="border-collapse: collapse; border: none;">
  <tr>
  <td align="center" style="border: none;">
      <br><b> Larissa Saud </b>
      <br>
      <a href="https://www.linkedin.com/in/larissasaud/">LinkedIn</a> |
      <a href="https://github.com/saudlari")">GitHub</a>
    </td>
    <td align="center" style="border: none;">
      <br><b> Mariuxi Olaya </b>
      <br>
      <a href="https://www.linkedin.com/in/molaya">LinkedIn</a> |
      <a href="https://github.com/catmaluci">GitHub</a>
    <td align="center" style="border: none;">
      <br><b>Milena Ocoro</b>
      <br>
      <a href="https://www.linkedin.com/in/mariabongoll">LinkedIn</a> |
      <a href="https://github.com/Femcom-Mari">GitHub</a>
    </td>
    <td align="center" style="border: none;">
      <br><b>Paola Morales</b>
      <br>
      <a href="https://www.linkedin.com/in/paola-morales-/"/>LinkedIn</a> |
      <a href="https://github.com/PaolaAMoralesP">GitHub</a>
    </td>    </td>
      <td align="center" style="border: none;">
      <br><b>Tetiana Vashchenko </b>
      <br>
      <a href="https://www.linkedin.com/in/priscilaguillen/">LinkedIn</a> |
      <a href="https://github.com/pgoliv-code">GitHub</a>
    </td>
  </tr>
</table>
