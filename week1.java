// Main.java
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ExpenseManager expenseManager = new ExpenseManager();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. Generate Report");
            System.out.println("6. Check Palindrome");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> expenseManager.addExpense();
                case 2 -> expenseManager.editExpense();
                case 3 -> expenseManager.deleteExpense();
                case 4 -> expenseManager.viewExpenses();
                case 5 -> expenseManager.generateReport();
                case 6 -> checkPalindrome();
                case 7 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
    }

    public static void checkPalindrome() {
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        String cleaned = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        if (cleaned.equals(reversed)) {
            System.out.println("It's a palindrome!");
        } else {
            System.out.println("Not a palindrome.");
        }
    }
}

// ExpenseManager.java
import java.util.*;

class Expense {
    int id;
    double amount;
    String category;
    String date;

    Expense(int id, double amount, String category, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}

public class ExpenseManager {
    private final List<Expense> expenses = new ArrayList<>();
    private int idCounter = 1;
    private final Scanner scanner = new Scanner(System.in);

    public void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        expenses.add(new Expense(idCounter++, amount, category, date));
        System.out.println("Expense added.");
    }

    public void editExpense() {
        System.out.print("Enter expense ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Expense exp : expenses) {
            if (exp.id == id) {
                System.out.print("Enter new amount: ");
                exp.amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter new category: ");
                exp.category = scanner.nextLine();
                System.out.print("Enter new date: ");
                exp.date = scanner.nextLine();
                System.out.println("Expense updated.");
                return;
            }
        }
        System.out.println("Expense ID not found.");
    }

    public void deleteExpense() {
        System.out.print("Enter expense ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        expenses.removeIf(exp -> exp.id == id);
        System.out.println("Expense deleted if ID existed.");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Expense exp : expenses) {
                System.out.printf("ID: %d | Amount: %.2f | Category: %s | Date: %s\n",
                        exp.id, exp.amount, exp.category, exp.date);
            }
        }
    }

    public void generateReport() {
        Map<String, Double> categoryMap = new HashMap<>();
        for (Expense exp : expenses) {
            categoryMap.put(exp.category, categoryMap.getOrDefault(exp.category, 0.0) + exp.amount);
        }
        System.out.println("\n=== Expense Report ===");
        for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
            System.out.printf("Category: %s | Total: %.2f\n", entry.getKey(), entry.getValue());
        }
    }
}
