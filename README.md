
# Automated Academic Query Management System

## Description
The **Automated Academic Query Management System** is a comprehensive solution designed to streamline the management of academic queries using an automated rule-based reasoning engine. Developed using **Java Swing** for the user interface and **JDBC** for database interactions, the system helps automate query processing with **Drools** for efficient rule-based reasoning, providing fast and accurate responses to academic-related inquiries.

## Key Features
- **Automated Query Handling**: Automatically processes academic queries with predefined rules using Drools.
- **User-Friendly Interface**: Built with Java Swing to provide an intuitive interface for both students and administrators.
- **Rule-Based Reasoning Engine**: Leverages Drools for automated reasoning and decision-making based on academic query patterns.
- **Database Connectivity**: Uses JDBC for secure and efficient interaction with the backend database.
- **Scalable & Customizable**: Supports additional rules and easy modifications for future enhancements.

## Technology Stack
- **Frontend**: Java Swing
- **Backend**: Java, JDBC
- **Database**: MySQL (or any relational database)
- **Rule Engine**: Drools (for rule-based reasoning)
- **Version Control**: Git

## Prerequisites
To run this project, you will need:
- **Java Development Kit (JDK 8 or higher)**
- **MySQL Database** (or any other RDBMS)
- **Drools Rule Engine**
- **Maven** (for managing dependencies)

## Installation and Setup

## Clone the Repository:

git clone 
cd automated-academic-query-system


## Configure the Database:
- Set up a MySQL database and update the database configuration in the project files.
- You may need to configure the connection details (username, password) in the JDBC connection file.

## Install Drools:
- Download and install the Drools engine by adding the Drools dependencies to the `pom.xml` file (Maven):
```xml
<dependency>
    <groupId>org.kie</groupId>
    <artifactId>drools-core</artifactId>
    <version>7.48.0.Final</version>
</dependency>
<dependency>
    <groupId>org.kie</groupId>
    <artifactId>drools-compiler</artifactId>
    <version>7.48.0.Final</version>
</dependency>
```

## Build the Project:
Use Maven to install the necessary dependencies and build the project:

mvn clean install


## Run the Application:
Once the dependencies are set up and the database is configured, you can run the project through your IDE or via the command line:

java -jar target/automated-academic-query-system.jar


## Usage

- **Student Interface**: Allows students to submit academic queries, check responses, and track the progress of their queries.
- **Admin Interface**: Enables admins to manage incoming queries, monitor responses, and define rules in the Drools-based system for automated query handling.
- **Automated Query Processing**: The system processes and resolves queries based on predefined rules, providing quick and accurate responses without manual intervention.
