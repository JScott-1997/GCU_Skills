# GCU Skills

A Java-based web application for a learning management system.

## Features

*   User registration and login
*   View and manage courses
*   View lessons

## Technologies Used

*   Java
*   JSP (JavaServer Pages)
*   Maven
*   UCanAccess (for connecting to MS Access database)
*   jBCrypt (for password hashing)

## Setup and Installation

### Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Apache Maven
*   A servlet container like Apache Tomcat

### Database Configuration

The database connection string is currently hardcoded in the following files:

*   `src/main/java/bean/UserManager.java`
*   `src/main/java/bean/CourseManager.java`

You will need to change the `connectionString` variable in these files to point to the location of your `GCU_SkillsDB.accdb` file.

**Example:**

```java
private final String connectionString = "jdbc:ucanaccess://<path_to_your_database>/GCU_SkillsDB.accdb";
```

### Build and Run

1.  **Build the project:**
    ```bash
    mvn clean install
    ```
2.  **Deploy the WAR file:**
    *   The build process will generate a `GCU_Skills.war` file in the `target` directory.
    *   Deploy this WAR file to your servlet container (e.g., copy it to the `webapps` directory of Tomcat).
3.  **Access the application:**
    *   Once deployed, you can access the application at `http://localhost:<port>/GCU_Skills/` (replace `<port>` with the port number of your servlet container).
