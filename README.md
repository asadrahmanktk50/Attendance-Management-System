# Attendance Management System

## About the Project

This project is a Java-based system developed for our OOP semester course.
The purpose of this project is to manage students, teachers, courses, sections, and attendance through a simple graphical interface.

Instead of handling attendance manually, this system allows teachers and admins to perform all tasks in an organized way using a GUI built with Java Swing.

---

## Objective

The main goal of this project was to apply Object-Oriented Programming concepts in a practical system and understand how different parts of a software system work together.

---

## Group Member(Only Myself)

Asad Khan
CMS ID: 023-25-0203
Section: BSAI-B

---

## OOP Concepts Used

* Classes and Objects
* Encapsulation (private variables with methods)
* Inheritance (User → Admin, Teacher, Student)
* Abstraction (abstract User class)
* Polymorphism (method overriding like toString)

---

## Project Structure

The project is divided into different parts:

* model → contains classes like User, Student, Section
* dao → handles database queries
* service → contains logic of the system
* ui → all GUI screens
* main → starting point of the program

---

## Main Features

Admin can:

* Add teachers
* Add students
* Create courses and sections
* Enroll students into sections
* Approve new admins

Teacher can:

* Create class sessions
* Mark attendance using checkboxes
* View attendance by student
* View attendance by session

---

## Attendance System

The teacher selects a section and session.
All students in that section appear as a list with checkboxes.

* Checked → Present
* Unchecked → Absent

Attendance is stored in the database and duplicate entries are prevented.

---

## Database

We used MySQL for storing data.

Main tables:

* users
* courses
* sections
* enrollments
* class_sessions
* attendance

---

## Tools Used

* Java (JDK 8 or above)
* Java Swing
* MySQL
* JDBC

---

## How to Run

1. Compile the project:

javac -cp "lib/*" -d bin src/model/*.java src/util/*.java src/dao/*.java src/service/*.java src/ui/*.java src/main/*.java

2. Run the project:

java -cp "bin;lib/*" main.MainApp

3. Make sure MySQL is running and database credentials are correct in DatabaseManager.java.

---

## What I Learned

Through this project, I learned:

* How to design a system using OOP
* How Java connects with a database
* How GUI applications are built
* How to debug and fix real issues

---

## Limitations

* UI is basic
* No password security
* No student login

---

## Future Improvements

* Improve UI design
* Add reports
* Add quiz functionality
* Add student panel

---

## Demo Video

link will be shared here

---

## Conclusion

This project helped me understand how different programming concepts work together in a real application. It was a good learning experience, especially in combining GUI, database, and logic in one system.
