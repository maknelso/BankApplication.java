/* 
    Display welcome message to user and then give user five options
    Five options: check balance, deposit, withdraw, check previous transaction, and exit
    User will be allowed to make a choice and then their corresponding function will be executed
*/

import java.util.Scanner;   // import the Scanner class
import java.math.BigDecimal;    // BigDecimal class provides operations on double numbers for rounding and other things

// public top-level class - there can only be one because the name of java file is same as name of public class
public class BankingApplicationNelson{
    public static void main(String[] args){
        // create new BankAccount Object
        BankAccount myObj = new BankAccount("00001", "Nelson Mak");
        // invoke myObj's showMenu method
        myObj.showMenu();    
    }
}

// purpose of multiple class in one source file is to bundle related support functionalities 
class BankAccount{
    // instance variables - space is allocated in the heap when "new" keyword is used
    double balance = 0.00;
    double previousTrans = 0.00;  // shows whether it was deposit or withdrawal
    String customerID;
    String customerName;

    // constructor
    public BankAccount(String customerID, String customerName){
        this.customerID = customerID;
        this.customerName = customerName;
    }

    // Creating new methods:
    // Check balance method       
    void checkBalance(){
        // not returning 2 decimal places for some reason - always just 1 -- format string not really working
        System.out.println("Your balance is: %.3f %n" + BigDecimal.valueOf(balance));
    }

    // Deposit method
    void deposit(double amount){
        // controls
        if(amount > 0.00){
            balance += amount;
            previousTrans = amount;
            System.out.println("Your balance is now: " + BigDecimal.valueOf(balance));
        }
        else{
            System.out.println("Please enter a valid positive amount to deposit.");
        }
    }

    // Withdraw method
    void withdraw(double amount){
        // if - if this works, then skip the follow else if statements
        if(amount < 0.00){
            System.out.println("Please enter a valid positive amount to WITHDRAW");
        }
        else if(balance >= amount){
            balance -= amount;
            previousTrans = -amount;
            System.out.println("Your balance is now: " + BigDecimal.valueOf(balance));
        }
        else{
            System.out.println("Cannot proceed - your account has insufficient funds. Your current balance is still: " + BigDecimal.valueOf(balance));
        }
    }

    // Check previous transaction method
    void getPreviousTransaction(){
        if(previousTrans > 0.00){
            System.out.println("Your previous transaction was a deposit of: " + BigDecimal.valueOf(previousTrans));
        }
        else if (previousTrans < 0.00){
            // Math class in package java.lang is static imported
            // static import means fields and methods in a class can be used in code without specifying their class
            // if they are defined as public static
            System.out.println("Your previous transaction was a withdrawal of: " + Math.abs(previousTrans));
        }
        else{
            System.out.println("There was no previous transaction.");
        }
    }

    // display menu in front of the user
    void showMenu(){
        // initialize char variable named option
        char option='\0';

        // create new Scanner object named scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome " + customerName);  // concatenate
        System.out.println("Your ID is " + customerID);
        System.out.println("\n");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Previous transaction");
        System.out.println("5. Exit");

        // do/while loop
        do{
            // 5 lines
            System.out.println("======");
            System.out.println("Enter an option");
            option = scanner.next().charAt(0);

            // option variable compares to each of the case and finds a match
            switch(option){
                case '1':   // check balance
                    System.out.println("-----");
                    System.out.println("Balance = " + balance);
                    System.out.println("-----");
                    break;

                case '2': // customer ID
                    System.out.println("-----");
                    System.out.println("Enter an amount to deposit: ");
                    System.out.println("-----");
                    // use scanner object to take int input
                    double dep_amount = scanner.nextDouble();
                    // call deposit method with amount as input
                    deposit(dep_amount);
                    break;
                
                case '3': // withdraw
                    System.out.println("-----");
                    System.out.println("Enter an amount to withdraw: ");
                    System.out.println("-----");

                    double with_amount = scanner.nextDouble();
                    withdraw(with_amount);
                    break;
                
                case '4': // previous transaction
                    System.out.println("-----");
                    getPreviousTransaction();
                    System.out.println("-----");
                    break;

                case '5':   // exit
                    System.out.println("*****");
                    break;    

                // if there is no case match
                default:
                    System.out.println("Invalid Option! Please enter again!");
            }

        } while(option != '5');    // E stands for Exit
            System.out.println("Thank-you for using our services, we hope to see you again soon!");
            scanner.close();
    }
}

