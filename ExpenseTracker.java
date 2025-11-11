// File: ExpenseTracker.java
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ExpenseTracker extends JFrame {
    JTextField categoryField, amountField;
    JTextArea displayArea;
    java.util.List<String> expenses = new ArrayList<>();

    public ExpenseTracker() {
        setTitle("Expense Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Category:"));
        categoryField = new JTextField(10);
        add(categoryField);

        add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        add(amountField);

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View Expenses");
        JButton clearBtn = new JButton("Clear All");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        add(addBtn);
        add(viewBtn);
        add(clearBtn);
        add(new JScrollPane(displayArea));

        addBtn.addActionListener(e -> addExpense());
        viewBtn.addActionListener(e -> viewExpenses());
        clearBtn.addActionListener(e -> clearExpenses());

        setVisible(true);
    }

    void addExpense() {
        try {
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String entry = category + " - Rs. " + amount;
            expenses.add(entry);
            displayArea.append("Added: " + entry + "\n");
            saveToFile();
        } catch (Exception e) {
            displayArea.append("Invalid input. Please try again.\n");
        }
    }

    void viewExpenses() {
        displayArea.setText("");
        double total = 0;
        for (String exp : expenses) {
            displayArea.append(exp + "\n");
            total += Double.parseDouble(exp.split("Rs. ")[1]);
        }
        displayArea.append("\nTotal Expense: Rs. " + total + "\n");
    }

    void clearExpenses() {
        expenses.clear();
        new File("expenses.txt").delete();
        displayArea.setText("All records cleared.\n");
    }

    void saveToFile() throws IOException {
        FileWriter fw = new FileWriter("expenses.txt");
        for (String exp : expenses) {
            fw.write(exp + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        new ExpenseTracker();
    }
}
