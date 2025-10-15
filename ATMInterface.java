import java.util.InputMismatchException;
import java.util.Scanner;

// Main class to run the ATM application
public class ATMInterface {

    public static void main(String[] args) {
   
        ATM atm = new ATM();
        atm.start();
    }
}


class ATM {
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM() {
        this.scanner = new Scanner(System.in);
        // Changed account holder name to "CodSoft Intern"
        this.currentAccount = new BankAccount("123456789", "CodSoft Intern", 1500.75, "1234");
        System.out.println("Welcome to the ATM!");
        System.out.println("Please insert your card (i.e., provide account details).");
        System.out.println("Account loaded for: " + currentAccount.getAccountHolderName());
        System.out.println("Current Balance: $" + String.format("%.2f", currentAccount.getBalance()));
        System.out.println("Use PIN: " + currentAccount.getPin() + " to log in.");
    }

    public void start() {
        if (!authenticateUser()) {
            System.out.println("Authentication failed. Exiting ATM.");
            scanner.close(); 
            return;
        }

        int choice;
        do {
            displayMenu(); 
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    checkBalance(); 
                    break;
                case 2:
                    withdraw();    
                    break;
                case 3:
                    deposit();  
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!"); // Point 7: Exit message
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Point 7: Invalid choice
            }
            System.out.println(); 
        } while (choice != 4);

        scanner.close(); 
    }

    
    private boolean authenticateUser() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your 4-digit PIN: ");
            String enteredPin = scanner.nextLine();
            if (currentAccount.getPin().equals(enteredPin)) {
                System.out.println("PIN accepted. Access granted.");
                return true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. You have " + (MAX_ATTEMPTS - attempts) + " attempts left.");
            }
        }
        return false;
    }

    private void displayMenu() {
        System.out.println("--- ATM Options ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    
    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
            System.out.print("Enter your choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }


    private void checkBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.printf("Your current balance is: $%.2f%n", currentAccount.getBalance()); 
    }

  
    private void withdraw() {
        System.out.println("\n--- Withdraw ---");
        System.out.print("Enter amount to withdraw: $");
        double amount = getValidatedAmount(); 

        if (amount == -1) { 
            System.out.println("Withdrawal cancelled due to invalid amount.");
            return;
        }

        
        currentAccount.withdraw(amount);
    }

    private void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Enter amount to deposit: $");
        double amount = getValidatedAmount();

        if (amount == -1) { 
            System.out.println("Deposit cancelled due to invalid amount.");
            return;
        }

        currentAccount.deposit(amount); 
    }


    private double getValidatedAmount() {
        while (true) {
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("Amount must be positive. Please enter a valid amount:");
                    System.out.print("$");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical value:");
                scanner.nextLine(); // Consume the invalid input
                System.out.print("$");
            }
        }
    }
}


class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin; 

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            return true;
        } else {
            System.out.printf("Insufficient balance for withdrawal. Current balance: $%.2f%n", balance);
            return false;
        }
    }

   
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolderName +
               "\nCurrent Balance: $" + String.format("%.2f", balance);
    }
}