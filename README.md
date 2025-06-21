# Learning Delivery Platform

A Spring Boot based REST API for delivering learning content such as courses and user enrollment. This backend system is designed to support scalable content delivery for education platforms, including support for users, roles, and structured curriculum data.

---

## Features
- **Spring Boot REST API** for learning content delivery
- **User Management** with roles (currently `ADMIN`, and `STUDENT`)
- **Course Creation** and retrieval
- **User - Course Relaitionship**: user can enroll in course (no duplicates allowed)
- **MySQL** database integration using **JPA/Hibernate**
- **Dockerized** with `docker-compose` for local deployment
- **Seeded test data** on startup for rapid testing
- **Initial Integration Testing** using Testcontainers with MySQL - see tests below

**Next Steps:**
- Full testing suite
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

### Run the Platform locally with Docker Compose

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

## Testing with Testcontainers
This project use [Testcontainers](https://www.testcontainers.org/) for integration testing. Tests run against a real MySQL cotnainer, ensuring production-like test coverage without requiring a local DB setup.

```bash

# Run tests
mvn clean test

```
Testcontainers automatically handles lifecycle and resource cleanup for each test.

### Current Integration Tests

| Test Class | Purpose | Status |
|------------|---------|--------|
| `UserControllerTest` | Verifies user-related API endpoints | WORKING |
|| - `GET /api/users` | 🗹 |
|| - `GET /api/users/{id}` | 🗹 |
|| - `GET /api/users/9999` (not found case) | 🗹 |
|*More coming soon*| *e.g. CourseControllerTest, EnrollmentsTest* | TBD |

You can find this under `src/test/java/com/kelsyfrank/learning/controller`

## Author
Kelsy Frank

M.S. in Computer Science | Learning Tech Enthusiast

[LinkedIn](https://www.linkedin.com/in/kelsy-frank-36a20732a/)


