import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined Demo Account Credentials
        String validUserId = "ankita123";
        String validPin = "1234";
        double balance = 10000.00; // Initial Balance
        ArrayList<String> transactionHistory = new ArrayList<>();

        System.out.println("=========================================");
        System.out.println("        WELCOME TO OASIS ATM INTERFACE   ");
        System.out.println("=========================================");

        // Login Authentication
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (!userId.equals(validUserId) || !pin.equals(validPin)) {
            System.out.println("\nInvalid User ID or PIN! Access Denied.");
            scanner.close();
            return;
        }

        System.out.println("\nLogin Successful! Welcome, " + userId + ".");

        boolean running = true;
        while (running) {
            System.out.println("\n-----------------------------------------");
            System.out.println("               ATM MENU                  ");
            System.out.println("-----------------------------------------");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option (1-5): ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Transaction History ---");
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions performed yet.");
                    } else {
                        for (String record : transactionHistory) {
                            System.out.println("- " + record);
                        }
                    }
                    System.out.printf("Current Balance: Rs. %.2f\n", balance);
                    break;

                case 2:
                    System.out.print("\nEnter amount to withdraw: Rs. ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid amount! Must be greater than 0.");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= withdrawAmount;
                        transactionHistory.add(String.format("Withdrawn: Rs. %.2f", withdrawAmount));
                        System.out.printf("Successfully withdrawn Rs. %.2f\n", withdrawAmount);
                        System.out.printf("Remaining Balance: Rs. %.2f\n", balance);
                    }
                    break;

                case 3:
                    System.out.print("\nEnter amount to deposit: Rs. ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (depositAmount <= 0) {
                        System.out.println("Invalid amount! Must be greater than 0.");
                    } else {
                        balance += depositAmount;
                        transactionHistory.add(String.format("Deposited: Rs. %.2f", depositAmount));
                        System.out.printf("Successfully deposited Rs. %.2f\n", depositAmount);
                        System.out.printf("Updated Balance: Rs. %.2f\n", balance);
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Recipient Account Number / Name: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter amount to transfer: Rs. ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (transferAmount <= 0) {
                        System.out.println("Invalid amount! Must be greater than 0.");
                    } else if (transferAmount > balance) {
                        System.out.println("Insufficient balance for transfer!");
                    } else {
                        balance -= transferAmount;
                        transactionHistory.add(String.format("Transferred Rs. %.2f to %s", transferAmount, recipient));
                        System.out.printf("Successfully transferred Rs. %.2f to %s\n", transferAmount, recipient);
                        System.out.printf("Remaining Balance: Rs. %.2f\n", balance);
                    }
                    break;

                case 5:
                    System.out.println("\nThank you for using Oasis ATM. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Please choose between 1 and 5.");
            }
        }

        scanner.close();
    }
}
