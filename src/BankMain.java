import java.util.ArrayList;

// Parent class
abstract class Account {

    // Encapsulation: keep data private
    private int accountNumber;
    private String name;
    private double balance;

    // Constructor
    Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    // Give information about account
    public void showDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    // Deposit money
    public void deposit(double money) {
        if (money > 0) {
            balance = balance + money;
            System.out.println("Deposited: " + money);
        } else {
            System.out.println("Invalid amount");
        }
    }

    // Child classes can change balance
    protected void changeBalance(double money) {
        balance = balance + money;
    }

    // Get balance
    public double getBalance() {
        return balance;
    }

    // Every account must have withdrawal
    abstract void withdraw(double money);
}


// Savings Account
// Inheritance: SavingsAccount gets properties from Account
class SavingsAccount extends Account {

    SavingsAccount(int accountNumber, String name, double balance) {
        super(accountNumber, name, balance);
    }

    // Polymorphism
    // Savings account cannot withdraw more than balance
    @Override
    void withdraw(double money) {

        if (money <= getBalance()) {
            changeBalance(-money);
            System.out.println("Savings: Money withdrawn = " + money);
        } else {
            System.out.println("Savings: Not enough balance");
        }
    }
}


// Current Account
// Inheritance
class CurrentAccount extends Account {

    CurrentAccount(int accountNumber, String name, double balance) {
        super(accountNumber, name, balance);
    }

    // Polymorphism
    // Current account allows overdraft of 5000
    @Override
    void withdraw(double money) {

        if (money <= getBalance() + 5000) {
            changeBalance(-money);
            System.out.println("Current: Money withdrawn = " + money);
        } else {
            System.out.println("Current: Withdrawal limit exceeded");
        }
    }
}


// Main class
public class BankMain {

    public static void main(String[] args) {

        // Human logic:
        // Create a savings account
        Account savings = new SavingsAccount(
                101,
                "Gouthami",
                10000
        );

        // Create a current account
        Account current = new CurrentAccount(
                102,
                "Rahul",
                5000
        );

        // Savings account
        System.out.println("SAVINGS ACCOUNT");
        savings.showDetails();

        savings.deposit(2000);
        savings.withdraw(5000);

        System.out.println("Final Balance: "
                + savings.getBalance());

        System.out.println();


        // Current account
        System.out.println("CURRENT ACCOUNT");
        current.showDetails();

        current.deposit(3000);
        current.withdraw(8000);

        System.out.println("Final Balance: "
                + current.getBalance());
    }
}