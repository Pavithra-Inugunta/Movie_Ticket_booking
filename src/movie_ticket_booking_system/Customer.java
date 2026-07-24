package movie_ticket_booking_system;

import java.util.HashMap;
import java.util.Scanner;

public class Customer {

    private String customerName;
    private int age;
    private long mobileNumber;
    private String password;

    static Scanner sc = new Scanner(System.in);

    static HashMap<Long, Customer> customers = new HashMap<>();

    public static Customer loggedInCustomer = null;

    // Default Constructor
    public Customer() {

    }

    // Parameterized Constructor
    public Customer(String customerName, int age, long mobileNumber, String password) {
        this.customerName = customerName;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public int getAge() {
        return age;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    // Customer Registration
    public void registerCustomer() {

        System.out.println("\n========== CUSTOMER REGISTRATION ==========");

        String name;

        while (true) {

            System.out.print("Enter Customer Name : ");
            name = sc.nextLine();

            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid Name! Please enter alphabets only.");
            }
        }

        int age;

        while (true) {

            System.out.print("Enter Age : ");

            if (sc.hasNextInt()) {

                age = sc.nextInt();

                if (age < 18) {
                    System.out.println("Registration Failed! Customer must be 18 years or above.");
                    return;
                }

                break;

            } else {

                System.out.println("Invalid Input! Please enter digits only.");
                sc.next();
            }
        }

        long mobile;

        while (true) {

            System.out.print("Enter Mobile Number : ");

            if (sc.hasNextLong()) {
                mobile = sc.nextLong();
                break;
            } else {
                System.out.println("Invalid Input! Please enter digits only.");
                sc.next();
            }
        }

        String mobileStr = String.valueOf(mobile);

        if (mobileStr.length() != 10 ||
                mobileStr.charAt(0) == '0' ||
                mobileStr.charAt(0) == '1') {

            System.out.println("Invalid Mobile Number.");
            return;
        }

        if (customers.containsKey(mobile)) {
            System.out.println("Mobile Number already registered.");
            return;
        }

        sc.nextLine();

        String pass;

        while (true) {

            System.out.print("Create Password : ");
            pass = sc.nextLine();

            if (pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$")) {
                break;
            } else {
                System.out.println("Invalid Password!");
                System.out.println("Password must:");
                System.out.println("- Be at least 6 characters");
                System.out.println("- Contain at least one uppercase letter");
                System.out.println("- Contain at least one lowercase letter");
                System.out.println("- Contain at least one number");
                System.out.println("- Contain at least one special character (@#$%^&+=!)");
            }
        }

        Customer customer = new Customer(name, age, mobile, pass);

        customers.put(mobile, customer);

        System.out.println("\nCustomer Registered Successfully.");
    }

    // Customer Login
    public boolean customerLogin() {

        System.out.println("\n========== CUSTOMER LOGIN ==========");

        long mobile;

        while (true) {

            System.out.print("Enter Mobile Number : ");

            if (sc.hasNextLong()) {
                mobile = sc.nextLong();
                sc.nextLine();
                break;
            } else {
                System.out.println("Invalid Input! Please enter digits only.");
                sc.next();
            }
        }

        System.out.print("Enter Password : ");
        String pass = sc.nextLine();

        if (customers.containsKey(mobile)) {

            Customer customer = customers.get(mobile);

            if (customer.getPassword().equals(pass)) {

                loggedInCustomer = customer;

                System.out.println("\nLogin Successful.");
                System.out.println("Welcome " + customer.getCustomerName() + "!");

                return true;
            }
        }

        System.out.println("\nInvalid Mobile Number or Password.");
        return false;
    }

    // Display Registered Customers
    public void displayCustomers() {

        System.out.println("\n========== REGISTERED CUSTOMERS ==========");

        if (customers.isEmpty()) {
            System.out.println("No Customers Registered.");
            return;
        }

        for (Customer customer : customers.values()) {

            System.out.println("--------------------------------------");
            System.out.println("Customer Name : " + customer.getCustomerName());
            System.out.println("Age           : " + customer.getAge());
            System.out.println("Mobile Number : " + customer.getMobileNumber());
        }
    }
}