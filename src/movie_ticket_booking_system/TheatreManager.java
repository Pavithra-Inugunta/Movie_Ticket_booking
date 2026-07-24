package movie_ticket_booking_system;

import java.util.Scanner;

public class TheatreManager {

    private final String userName = "admin";
    private final String password = "admin123";

    static Scanner sc = new Scanner(System.in);

    public boolean managerLogin() {

        System.out.println("\n========== THEATRE MANAGER LOGIN ==========");

        System.out.print("Enter Username : ");
        String user = sc.nextLine();

        System.out.print("Enter Password : ");
        String pass = sc.nextLine();

        if (userName.equals(user) && password.equals(pass)) {

            System.out.println("\nLogin Successful.");
            return true;
        }

        System.out.println("\nInvalid Username or Password.");
        return false;
    }

    public void viewRegisteredCustomers(Customer customer) {

        customer.displayCustomers();

    }

    public void viewAllBookings() {

        System.out.println("\nView All Bookings - Under Development");

    }

    public void viewRevenueReport() {

        System.out.println("\nRevenue Report - Under Development");

    }


    public void viewTheatreStatistics() {

        System.out.println("\nTheatre Statistics - Under Development");

    }

}
