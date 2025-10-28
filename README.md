# ⚡ Place Service: Fully Reactive CRUD API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=spring)
![WebFlux](https://img.shields.io/badge/Spring_WebFlux-Reactive-000000?style=for-the-badge&logo=spring)
![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk)
![R2DBC](https://img.shields.io/badge/Database-R2DBC-00C4FF?style=for-the-badge)
![H2](https://img.shields.io/badge/Database-H2-005662?style=for-the-badge)
![OpenAPI](https://img.shields.io/badge/API_Docs-OpenAPI-60A5FA?style=for-the-badge&logo=openapi-initiative)

> The **Place Service** is built on a modern, non-blocking stack designed for high concurrency. Utilizing **Spring WebFlux** for the web layer and **R2DBC** for reactive data persistence, this project demonstrates best practices for building scalable, end-to-end reactive applications.
> 
> ---

## 🏗️ Core Reactive Stack

This application relies on the following key components:

- Framework: Spring Boot 3.5.7
- Web: Spring WebFlux (`Mono`, `Flux`)
- Persistence: Spring Data R2DBC
- Database: H2 (in-memory, PostgreSQL compatibility mode)
- Utilities: Slugify (slug generation), DevTools (hot reload)

---

## 💻 Setup and Execution

### Prerequisites
- JDK 21
- Maven 3.9+

### Run

```bash
# Run in dev
mvn spring-boot:run

# Build and run
mvn clean package
java -jar target/place-service-0.0.1-SNAPSHOT.jar
```

## ⚙️ Configuration

Add or adjust the following in `src/main/resources/application.properties`:

```properties
spring.application.name=place-service
spring.sql.init.mode=always
springdoc.swagger-ui.path=/swagger-ui/index.html

# Optional: explicit R2DBC connection for H2 in-memory
# DB_CLOSE_DELAY=-1 keeps the database alive while the JVM is running
spring.r2dbc.url=r2dbc:h2:mem:///places;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.r2dbc.username=sa
spring.r2dbc.password=
```
## 🗄️ Database & Schema

- The database is H2 in-memory. Schema is initialized via `src/main/resources/schema.sql`.
- Entity: `Place` with fields `id`, `name`, `slug`, `state`, `created_at`, `updated_at`.

## 📚 API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🌐 API Endpoints (Base: `/places`)

| Method | Path | Purpose |
| :--- | :--- | :--- |
| POST | `/places` | Create a new place |
| GET | `/places/{id}` | Retrieve a place by ID |
| GET | `/places` | List all places |
| PATCH | `/places/{id}` | Partially update a place |

### Request/Response Models
`PlaceRequest` (input):

```json
{
  "name": "São Paulo",
  "state": "SP"
}
```

Notes:
- `slug` is generated from `name` (via Slugify) on the server side.
- `createdAt` and `updatedAt` are managed by R2DBC auditing.

`PlaceResponse` (output):

```json
{
  "name": "São Paulo",
  "slug": "sao_paulo",
  "state": "SP",
  "createdAt": "2025-01-01T12:00:00",
  "updatedAt": "2025-01-01T12:00:00"
}
```

- DevTools enabled for hot reload in development.
- Uses `ReactiveCrudRepository` (Spring Data R2DBC).
- R2DBC auditing enabled via `@EnableR2dbcAuditing` in `PlaceConfig`.

## 📜 License
Licensed under the **MIT License**.