# Learning Delivery Platform

A Spring Boot based REST API for delivering learning content such as courses and user enrollment. This backend system is designed to support scalable content delivery for education platforms, including support for users, roles, and structured curriculum data.

---

## Features
- **Spring Boot REST API** for learning content delivery
- **User Management** with roles (currently `ADMIN`, and `STUDENT`)
- **Course Creation** and retrieval
- **User - Course Relaitionship** user can enroll in course, duplicates not allowed
- **MySQL** database integration using **JPA/Hibernate**
- **Dockerized** with `docker-compose` for local deployment
- **Seeded test data** on startup for rapid testing

**Next Steps:**
- Testing suite
- Authentication (JWT or Spring Security)
- Module/Lesson breakdowns within courses
- Add pagination and search filters
- Swagger/OpenAPI documentation

---

## Technologies

| Layer | Tech Stack |
|------|-----------|
| Backend | Java 17, Spring Boot 3 |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL 8 (via Docker Compose) |
| Dev Tools | Lombok, Spring Boot DevTools |
| Build Tool | Maven |
| Container | Docker, Docker Compose |

---

## API Endpoints

### Users
- `GET /api/users` - list all users
- `GET /api/users/{id}` - get a single user
- `POST /api/users` - create a new user
- `DELETE /api/users/{id}` - delete a user

### Courses
- `GET /api/courses` - list all courses
- `GET /api/courses/{id}` - get a single course
- `POST /api/courses` - create a new course
- `DELETE /api/courses/{id}` - delete a course

---

## Getting Started

### Prerequisites
- Java 17
- Maven
- Docker (for MySQL)

### Run the Platform with Docker Compose

This project includes a `Dockerfile` and `docker-compose.yml` to run both the Spring Boot app and MySQL together.

### Steps

```bash
# Build and start everything
docker-compose up --build
```

The Spring Boot API will be available at:
http://localhost:8080

## Database Configuration (via Compose)

| Property | Value |
|----------|-------|
| JDBC URL | `jdbc:mysql://mysql:3306/learnhub_db` |
| MySQL username | `appuser` |
| MySQL password | `app_pass` |
| MySQL root password | `itsatrap` |
| DB container name | `learnhub-mysql` |
| App container name | (built from Dockerfile) |

These are injected automatically into Spring Boot via environment variables in the `docker-compose.yml`.

## Author
Kelsy Frank

M.S. in Computer Science | Learning Tech Enthusiast

[LinkedIn](https://www.linkedin.com/in/kelsy-frank-36a20732a/)


