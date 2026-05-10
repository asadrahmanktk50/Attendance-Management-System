# Attendance Management System

## Project Description

This project is a Java-based GUI application developed for the Object-Oriented Programming semester project. The system is designed to manage teachers, students, courses, sections, sessions, and attendance in an organized way.

The application is developed using Java Swing for the graphical interface and MySQL for database management.

The main purpose of this project is to apply Object-Oriented Programming concepts in a practical system and understand how GUI, database, and business logic work together in a real application.

---

## Group Member

Name: Asad Rahman
CMS ID: 023-25-0203
Section: BS(AI)-B-II

---

## Purpose of the Project

In many educational environments, attendance and section management are handled manually. This project provides a simple computerized solution where Admins and Teachers can manage academic records more efficiently.

The system supports role-based access and provides separate functionalities for Admin and Teacher users.

---

## Main Modules

### Model Package

Contains entity classes such as:

* User
* Admin
* Teacher
* Student
* Course
* Section
* ClassSession

### DAO Package

Handles database operations and SQL queries.

### Service Package

Contains the business logic of the application.

### UI Package

Contains all GUI screens developed using Java Swing.

### Main Package

Contains the main class used to start the application.

---

## Main Features

### Admin Features

* Add Teachers
* Add Students
* Create Courses
* Create Sections
* Enroll Students into Sections
* Approve New Admins

### Teacher Features

* Create Class Sessions
* Mark Attendance using Checkboxes
* View Attendance by Student
* View Attendance by Session

---

## OOP Concepts Used

The following OOP concepts are used in this project:

* Classes and Objects
* Encapsulation
* Inheritance
* Abstraction
* Polymorphism
* Collections
* Exception Handling

Example:

* User is an abstract base class.
* Admin, Teacher, and Student inherit from User.
* Method overriding is used through methods like toString().

---

## Database

The project uses MySQL database.

Main tables:

* users
* courses
* sections
* enrollments
* class_sessions
* attendance

The database and tables are automatically created by the program when the application starts.

---

## Requirements

* Java JDK 8 or above
* MySQL Server
* MySQL Connector JAR file

---

## How to Compile

Compile the project using:

javac -cp "lib/*" -d bin src/model/*.java src/util/*.java src/dao/*.java src/service/*.java src/ui/*.java src/main/*.java

---

## How to Run

Run the project using:

java -cp "bin;lib/*" main.MainApp

---

## Database Configuration

Update database username and password in:

util/DatabaseManager.java

Make sure MySQL server is running before starting the application.

---

## Demo Video

link will be shared here for video.

Example:
https://youtube.com/my-video-link

---

## GitHub Repository

Click on the link below:
https://github.com/asadrahmanktk50/Attendance-Management-System/tree/main

---

## Conclusion

This project helped in understanding how Object-Oriented Programming concepts are applied in real-world applications. It also provided practical experience in GUI development, database connectivity, and project organization using Java.
