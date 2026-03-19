package service;

import modul.Student;

import java.sql.*;

import static service.Scanner.scanner;

public class StudentService {

     public void AddStudent() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "1234"
        );


        System.out.println("Enter student details:");
        System.out.print("ID: ");
         int id = scanner.nextInt();
         scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
         System.out.print("Age: ");
        int age = scanner.nextInt();
         System.out.print("Grade: ");
        int grade = scanner.nextInt();
         scanner.nextLine();
         System.out.print("City: ");
        String city = scanner.nextLine();
         Student student = new Student(id, name, age, grade, city);//?
         PreparedStatement statement = connection.prepareStatement(
                 "INSERT INTO students (id, name, age, grade, city) VALUES (?, ?, ?, ?, ?)"
         );

         statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setInt(4, student.getGrade());
            statement.setString(5, student.getCity());
       if (statement.executeUpdate() == 1) {
           System.out.println("Student added successfully");
       }else {
           System.out.println("Student not added successfully");
       }
        statement.close();
        connection.close();
    }


   public void UpdateStudent() throws SQLException {
       Connection connection=DriverManager.getConnection(
               "jdbc:postgresql://localhost:5432/postgres",
               "postgres",
               "1234"
       );
       System.out.print("Enter student ID to update: ");
       int UpdateID = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Enter new name: ");
       String name = scanner.nextLine();
       System.out.print("Enter new age: ");
       int age = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Enter new grade: ");
       int grade = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Enter new city: ");
       String city = scanner.nextLine();
       Student student = new Student(UpdateID, name, age, grade, city);
       PreparedStatement statement = connection.prepareStatement(
               "UPDATE  students SET name=?,age=?,grade=?,city=? WHERE id=?"
       );

       statement.setString(1, student.getName());
       statement.setInt(2, student.getAge());
       statement.setInt(3, student.getGrade());
       statement.setString(4, student.getCity());
       statement.setInt(5, student.getId());
        if (statement.executeUpdate() > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("No student found with the given ID.");
        }
       statement.close();
       connection.close();
    }
    public void DeleteStudent() throws SQLException {
        Connection connection=DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "1234"
        );
        System.out.print("Enter student ID to update: ");
        int DeletedID = scanner.nextInt();

        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students  WHERE id=?"
        );

        statement.setInt(1, DeletedID);
        if (statement.executeUpdate() > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("No student found with the given ID.");
        }
        statement.close();
        connection.close();

    }
    public void ViewAllStudents() throws SQLException {
        Connection connection=DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "1234"
        );
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM students");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Age: " + rs.getInt("age"));
            System.out.println("Grade: " + rs.getInt("grade"));
            System.out.println("City: " + rs.getString("city"));
            System.out.println("-----------------------");
        }
        rs.close();
        statement.close();
        connection.close();
    }
}

