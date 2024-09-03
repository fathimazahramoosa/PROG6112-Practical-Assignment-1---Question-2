/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.person;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
// Person.java
class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

// Student.java
class Student extends Person {
    private final String studentId;
    private final String course;

    public Student(String name, int age, String studentId, String course) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student ID: " + studentId + ", Course: " + course;
    }
}

// StudentManager.java
class StudentManager {
    private final Student[] students;
    private int studentCount;

    public StudentManager(int capacity) {
        students = new Student[capacity];
        studentCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("No more students can be added.");
        }
    }

    public Student findStudentById(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(studentId)) {
                return students[i];
            }
        }
        return null;
    }

    public void listAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students registered.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                System.out.println(students[i]);
            }
        }
    }
}

// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager(10); // capacity for 10 students

        while (true) {
            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Enter course: ");
                String course = scanner.nextLine();

                Student student = new Student(name, age, studentId, course);
                manager.addStudent(student);
            } else if (option == 2) {
                manager.listAllStudents();
            } else if (option == 3) {
                System.out.print("Enter student ID to search: ");
                String studentId = scanner.nextLine();
                Student student = manager.findStudentById(studentId);
                if (student != null) {
                    System.out.println(student);
                } else {
                    System.out.println("Student not found.");
                }
            } else if (option == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }

        scanner.close();
    }
}

// StudentManagerTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {
    @Test
    public void testAddAndFindStudent() {
        StudentManager manager = new StudentManager(2);
        Student student1 = new Student("John Doe", 20, "S123", "Computer Science");
        Student student2 = new Student("Jane Doe", 22, "S124", "Mathematics");

        manager.addStudent(student1);
        manager.addStudent(student2);

        assertEquals(student1, manager.findStudentById("S123"));
        assertEquals(student2, manager.findStudentById("S124"));
    }

    @Test
    public void testFindNonExistingStudent() {
        StudentManager manager = new StudentManager(2);
        assertNull(manager.findStudentById("S999"));
    }

    @Test
    public void testListAllStudents() {
        StudentManager manager = new StudentManager(2);
        Student student1 = new Student("John Doe", 20, "S123", "Computer Science");

        manager.addStudent(student1);
        // You can extend this with more unit tests or use a mock output stream to capture the console output
    }
}
