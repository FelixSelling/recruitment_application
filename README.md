# Recruitment application

## Table of Contents

- [Installation](#installation)
  - [Software Requirements](#software-requirements)
  - [Creating a Local Database](#creating-a-local-database)
  - [Environment Variables](#environment-variables)

## Installation

### Software Requirements

To install and run the project as intended, you will need the following software:

- Java 21
- Maven
- PostgreSQL
- Visual Studio Code (VSCode) with the following extension packages:
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) by Microsoft
  - [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack) by VMware

Please make sure you have these software installed before proceeding.

### Creating a Local Database

To create a new database for the recruitment application, follow these steps:

1. Open your preferred database management tool (e.g., pgAdmin, DBeaver).
2. Connect to your PostgreSQL server using the appropriate credentials.
3. Create a new database with a desired name (e.g., `recruitment_db`).
4. Run sql script located in /dbscript/dbinit.sql

### Environment Variables

The following environment variables need to be set before running the project:

- `JDBC_DATABASE_URL`: The URL of the database connection.
- `JDBC_DATABASE_USERNAME`: The username for accessing the database.
- `JDBC_DATABASE_PASSWORD`: The password for accessing the database.
- `KTH_USERNAME`: The username for SMTP authentication.
- `KTH_PASSWORD`: The password for SMTP authentication.
  Make sure to set these environment variables with the appropriate values before running the project.
- `BASE_URL`: The url of the website. For local setup use "http://localhost:8080".
