// File: StudentManagementSystem.java
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentManagementSystem extends JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    JTextField idField, nameField, courseField;
    JTextArea output;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Student ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel courseLabel = new JLabel("Course:");

        idField = new JTextField(10);
        nameField = new JTextField(10);
        courseField = new JTextField(10);

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton deleteBtn = new JButton("Delete");

        output = new JTextArea(10, 40);
        output.setEditable(false);

        add(idLabel); add(idField);
        add(nameLabel); add(nameField);
        add(courseLabel); add(courseField);
        add(addBtn); add(viewBtn); add(deleteBtn);
        add(new JScrollPane(output));

        connect();

        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewStudents());
        deleteBtn.addActionListener(e -> deleteStudent());

        setVisible(true);
    }

    void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "yourpassword");
            output.append("Connected to database.\n");
        } catch (Exception e) {
            output.append("Connection failed: " + e.getMessage() + "\n");
        }
    }

    void addStudent() {
        try {
            pst = conn.prepareStatement("INSERT INTO students(id, name, course) VALUES (?, ?, ?)");
            pst.setString(1, idField.getText());
            pst.setString(2, nameField.getText());
            pst.setString(3, courseField.getText());
            pst.executeUpdate();
            output.append("Student added successfully.\n");
        } catch (Exception e) {
            output.append("Error: " + e.getMessage() + "\n");
        }
    }

    void viewStudents() {
        try {
            pst = conn.prepareStatement("SELECT * FROM students");
            rs = pst.executeQuery();
            output.setText("");
            while (rs.next()) {
                output.append(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("course") + "\n");
            }
        } catch (Exception e) {
            output.append("Error: " + e.getMessage() + "\n");
        }
    }

    void deleteStudent() {
        try {
            pst = conn.prepareStatement("DELETE FROM students WHERE id=?");
            pst.setString(1, idField.getText());
            pst.executeUpdate();
            output.append("Student deleted.\n");
        } catch (Exception e) {
            output.append("Error: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
