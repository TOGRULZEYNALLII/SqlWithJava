import java.sql.SQLException;
import service.StudentService;

import static service.Scanner.scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        service.StudentService service = new StudentService();
        while (true) {
            System.out.println("Welcome to Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

             int choice = scanner.nextInt();
             switch (choice) {
                 case 1:
                     service.AddStudent();
                     break;
                 case 2:
                     service.ViewAllStudents();
                     break;
                 case 3:
                     service.UpdateStudent();
                     break;
                 case 4:
                     service.DeleteStudent();
                     break;
                 case 5:
                     System.out.println("Exiting...");
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
        }
    }
}