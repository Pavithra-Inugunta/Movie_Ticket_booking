package movie_ticket_booking_system;

import java.util.Scanner;

public class SilverScreenCinemas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Customer customer = new Customer();
        Movie movie = new Movie();
        TheatreManager manager = new TheatreManager();

        // Load Default Movies
        movie.addMovies();

        int choice;

        do {

            System.out.println("\n+------------------------------------------------------+");
            System.out.println("|******************************************************|");
            System.out.println("|        WELCOME TO SILVER SCREEN CINEMAS              |");
            System.out.println("|           MOVIE TICKET BOOKING SYSTEM                |");
            System.out.println("|======================================================|");
            System.out.println("|        Lights... Camera... Action!                   |");
            System.out.println("|      Book Your Favourite Movie Tickets Easily!       |");
            System.out.println("|******************************************************|");
            System.out.println("+------------------------------------------------------+");

            System.out.println("1. Customer Registration");
            System.out.println("2. Customer Login");
            System.out.println("3. Theatre Manager Login");
            System.out.println("4. Exit");

            System.out.print("\nEnter Your Choice : ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid Input! Please enter digits only.");
                sc.next();
                System.out.print("\nEnter Your Choice : ");
            }

            choice = sc.nextInt();

            switch (choice) {

            case 1:
                customer.registerCustomer();
                break;

            case 2:

                if (customer.customerLogin()) {

                    int customerChoice;

                    do {

                        System.out.println("\n========================================");
                        System.out.println("             CUSTOMER MENU");
                        System.out.println("========================================");
                        System.out.println("1. View Movies");
                        System.out.println("2. Search Movie");
                        System.out.println("3. Book Ticket");
                        System.out.println("4. View My Booking");
                        System.out.println("5. Cancel Ticket");
                        System.out.println("6. Logout");

                        System.out.print("\nEnter Your Choice : ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid Input! Please enter digits only.");
                            sc.next();
                            System.out.print("\nEnter Your Choice : ");
                        }

                        customerChoice = sc.nextInt();

                        switch (customerChoice) {
                        case 1:
                            movie.viewMovies();
                            break;

                        case 2:
                            movie.searchMovie();
                            break;

                        case 3:
                            Booking.bookTicket();
                            break;

                        case 4:
                            Booking.viewMyBooking(Customer.loggedInCustomer.getMobileNumber());
                            break;

                        case 5:
                            Booking.cancelTicket();
                            break;

                        case 6:
                            System.out.println("\nLogged Out Successfully.");
                            break;

                        default:
                            System.out.println("Invalid Choice.");
                    }

                } while (customerChoice != 6);
            }

            break;

        case 3:

            if (manager.managerLogin()) {

                int managerChoice;

                do {

                    System.out.println("\n========================================");
                    System.out.println("         THEATRE MANAGER MENU");
                    System.out.println("========================================");
                    System.out.println("1. View Movies");
                    System.out.println("2. View Registered Customers");
                    System.out.println("3. View All Bookings");
                    System.out.println("4. Revenue Report");
                    System.out.println("5. Logout");

                    System.out.print("\nEnter Your Choice : ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid Input! Please enter digits only.");
                        sc.next();
                        System.out.print("\nEnter Your Choice : ");
                    }

                    managerChoice = sc.nextInt();

                    switch (managerChoice) {
                    case 1:
                        movie.viewMovies();
                        break;

                    case 2:
                        customer.displayCustomers();
                        break;

                    case 3:
                        Booking.viewAllBookings();
                        break;

                    case 4:
                        Booking.revenueReport();
                        break;

                    case 5:
                        System.out.println("\nLogged Out Successfully.");
                        break;

                    default:
                        System.out.println("Invalid Choice.");
                    }

                } while (managerChoice != 5);
            }

            break;

        case 4:

            System.out.println("\n====================================================");
            System.out.println("      THANK YOU FOR VISITING SILVER SCREEN CINEMAS");
            System.out.println("          Enjoy Your Movie! Visit Again!");
            System.out.println("====================================================");
            break;

        default:
            System.out.println("Invalid Choice.");

        }

    } while (choice != 4);

    sc.close();
}
}
                      