# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the application
./mvnw spring-boot:run

# Build
./mvnw clean package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=BlogApiApplicationTests

# Skip tests during build
./mvnw clean package -DskipTests
```

The app runs on `http://localhost:8080`. A Postman collection (`BlogAPI.postman_collection.json`) is included with pre-built requests for all endpoints.

## Architecture

This is a minimal Spring Boot 4 REST API (Java 21) with a single source file:

- `BlogController.java` — `@RestController` at `/api/posts`. Posts are stored in a static in-memory `List<String>` (no database, no persistence between restarts). Each post is stored as a raw `"title:content"` string — there is no `Post` model class.

## Known bugs (intentional — this is a learning/exercise project)

The Postman collection labels these explicitly:

- `GET /api/posts/{id}` and `DELETE /api/posts/{id}` — no bounds checking; out-of-range index throws `IndexOutOfBoundsException`
- `POST /api/posts` — no validation on empty title
- `GET /api/posts/total` — uses string concatenation instead of numeric addition, so word counts are concatenated rather than summed
- `POST /api/posts/validate` — content limit is hardcoded to 5000 characters