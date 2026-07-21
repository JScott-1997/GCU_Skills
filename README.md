# GCU Skills

A Java web application for managing courses and lessons, built with server-rendered JSP and a relational database backend.

## Features

- User registration and login, with passwords hashed using jBCrypt
- Course creation and management
- Lesson viewing per course

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Web layer | JSP (JavaServer Pages) |
| Build | Maven |
| Database | MS Access, via UCanAccess (JDBC) |
| Security | jBCrypt password hashing |
| Deployment | Apache Tomcat (WAR) |

## Getting Started

### Prerequisites

- JDK 8 or higher
- Apache Maven
- A servlet container such as Apache Tomcat

### Database configuration

The connection string is set in:

- `src/main/java/bean/UserManager.java`
- `src/main/java/bean/CourseManager.java`

Update the `connectionString` variable in both files to point to your local `GCU_SkillsDB.accdb`:

```java
private final String connectionString = "jdbc:ucanaccess://<path_to_your_database>/GCU_SkillsDB.accdb";
```

### Build and run

```bash
mvn clean install
```

This produces `GCU_Skills.war` in `target/`. Deploy it to your servlet container (e.g. copy it into Tomcat's `webapps/` directory), then visit:

```
http://localhost:<port>/GCU_Skills/
```

## About

Built as an integrated project during my BSc (Hons) Computing at Glasgow Caledonian University.
