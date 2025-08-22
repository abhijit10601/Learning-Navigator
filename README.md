
## **Learning Navigator: LMS Exam Enrollment Service** 📚🎓

This project is a RESTful API service built with **Spring Boot** to manage the exam enrollment process within a simplified Learning Management System (LMS). It provides a robust backend for handling students, subjects, and exams, demonstrating a solid understanding of layered architecture, database management with **MySQL**, and best practices for developing a production-ready API.

## **Features** ✨

  * **Comprehensive CRUD Operations**: Full Create, Read, Update, and Delete functionality for Students, Subjects, and Exams.
  * **Relational Data Model**: Uses **MySQL** with **Spring Data JPA** to manage relationships between entities (Students, Subjects, Exams) using foreign keys.
  * **Enrollment Logic**: Enforces business rules, such as requiring a student to be enrolled in a subject before they can register for its exam.
  * **Centralized Error Handling**: A global exception handler using `@ControllerAdvice` provides consistent and meaningful error messages with appropriate HTTP status codes (e.g., 404 Not Found).
  * **Unit Testing**: Includes basic unit tests using **MockMvc** and **Mockito** to ensure the core functionalities are working as expected.
  * **Easter Egg Feature**: A hidden endpoint that provides a random fact about a number by integrating with the **Numbers API**.

## **Technologies Used** 💻

  * **Spring Boot**: The core framework for building the API.
  * **Spring Data JPA**: For database interactions and object-relational mapping.
  * **MySQL**: The relational database used to persist all data.
  * **Lombok**: Reduces boilerplate code for entities and DTOs.
  * **Maven**: Dependency management and build tool.
  * **JUnit 5, Mockito, MockMvc**: For writing and running unit tests.
  * **Numbers API**: Used for the hidden "Easter Egg" feature.

## **Getting Started** 🚀

### **Prerequisites**

  * **Java Development Kit (JDK) 17 or higher**
  * **MySQL Database**
  * **Gradle**

## **1. Database Setup**

1.  Ensure you have a running MySQL instance.
2.  Create a new database for the application, for example, `lms_db`.
3.  Update the `src/main/resources/application.properties` file with your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

## **2. Running the Application**

1.  Clone this repository to your local machine.
2.  Navigate to the project's root directory.
3.  Use the Gradle Wrapper (`./gradlew`) to build the project and run tests:
    ```bash
    ./gradlew build
    ```
4.  Run the application using the Spring Boot Gradle plugin:
    ```bash
    ./gradlew bootRun
    ```

The application will start on `http://localhost:8081`.

-----

### **Testing** 🧪

You can run all the tests using the Gradle command:

```bash
./gradlew test
```

## **API Endpoints** 🎯

The API base URL is `http://localhost:8081`.

### **Student Endpoints**

  * **`POST /students`**

      * **Description**: Creates a new student.
      * **Request Body**: `{ "name": "akash" }`
      * **Response Code**: `201 Created`

  * **`GET /students/{studentId}`**

      * **Description**: Retrieves a student by their ID.
      * **Response Code**: `200 OK`

  * **`GET /students`**

      * **Description**: Retrieves a list of all students.
      * **Response Code**: `200 OK`

### **Subject Endpoints**

  * **`POST /subjects`**

      * **Description**: Creates a new subject.
      * **Request Body**: `{ "name": "DSA" }`
      * **Response Code**: `201 Created`

  * **`GET /subjects/{subjectId}`**

      * **Description**: Retrieves a subject by its ID.
      * **Response Code**: `200 OK`

  * **`GET /subjects`**

      * **Description**: Retrieves a list of all subjects.
      * **Response Code**: `200 OK`

### **Exam Endpoints**

  * **`POST /exams/subjects/{subjectId}`**

      * **Description**: Creates a new exam for a specific subject.
      * **Response Code**: `201 Created`

  * **`GET /exams/{examId}`**

      * **Description**: Retrieves an exam by its ID.
      * **Response Code**: `200 OK`

  * **`GET /exams`**

      * **Description**: Retrieves a list of all exams.
      * **Response Code**: `200 OK`

### **Enrollment Endpoints**

  * **`POST /students/{studentId}/subjects/{subjectId}`**

      * **Description**: Enrolls a student in a subject.
      * **Response Code**: `200 OK`

  * **`POST /students/{studentId}/exams/{examId}`**

      * **Description**: Registers a student for an exam. This requires the student to be enrolled in the corresponding subject first.
      * **Response Code**: `200 OK`

### **Easter Egg Feature** 🥚

  * **`GET /easter-egg/hidden-feature/{number}`**
      * **Description**: Unlocks a hidden feature that returns a random fact about the provided number.
      * **Response Code**: `200 OK`
      * **Example Response**:
        ```json
        {
          "message": "Great! You have found the hidden number fact ",
          "response": "2 is the smallest prime number."
        }
        ```




