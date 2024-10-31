package ExpenseTracker;

import java.io.*;
import java.util.*;

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.txt";
    private static final Map<String, List<Double>> expenses = new HashMap<>();

    public static void main(String[] args) {
        loadExpenses();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: 1. Add Expense 2. View Expenses 3. Delete Expense 4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    deleteExpenses();
                    break;
                case 4:
                    saveExpenses();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addExpense(Scanner scanner) {
        System.out.println("Enter category:");
        String category = scanner.next();
        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();

        expenses.computeIfAbsent(category, k -> new ArrayList<>()).add(amount);
        System.out.println("Expense added.");
    }

    private static void viewExpenses() {
        System.out.println("Expense Summary:");
        for (String category : expenses.keySet()) {
            System.out.println("Category: " + category);
            double total = expenses.get(category).stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("Total: " + total);
        }
    }

    private static void deleteExpenses() {
        expenses.clear();
        System.out.println("All expenses deleted.");
    }

    private static void saveExpenses() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(expenses);
            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private static void loadExpenses() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = in.readObject();
            if (obj instanceof Map) {
                expenses.putAll((Map<String, List<Double>>) obj);
                System.out.println("Expenses loaded from file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved expenses found.");
        }
    }
}
