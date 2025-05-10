import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ExpenseManager extends JFrame {
    private JTextField dateField, categoryField, amountField;
    private JTextArea displayArea;
    private final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        setTitle("Expense Manager");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(15);
        add(dateField);

        add(new JLabel("Category:"));
        categoryField = new JTextField(15);
        add(categoryField);

        add(new JLabel("Amount:"));
        amountField = new JTextField(15);
        add(amountField);

        JButton addButton = new JButton("Add Expense");
        add(addButton);

        JButton viewButton = new JButton("View Expenses");
        add(viewButton);

        displayArea = new JTextArea(10, 40);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> addExpense());
        viewButton.addActionListener(e -> viewExpenses());

        setVisible(true);
    }

    private void addExpense() {
        try {
            String date = dateField.getText();
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Expense expense = new Expense(date, category, amount);
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(expense.toString() + "\n");
            writer.close();

            displayArea.setText("Expense added successfully!");
            clearFields();
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void viewExpenses() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            double total = 0.0;

            displayArea.setText("Expenses:\n");
            while ((line = reader.readLine()) != null) {
                Expense expense = Expense.fromString(line);
                displayArea.append("Date: " + expense.getDate() + ", Category: " + expense.getCategory() + ", Amount: ₹" + expense.getAmount() + "\n");
                total += expense.getAmount();
            }
            reader.close();

            displayArea.append("\nTotal Expenses: ₹" + total);
        } catch (Exception e) {
            displayArea.setText("Error reading file: " + e.getMessage());
        }
    }

    private void clearFields() {
        dateField.setText("");
        categoryField.setText("");
        amountField.setText("");
    }

    public static void main(String[] args) {
        new ExpenseManager();
    }
}
