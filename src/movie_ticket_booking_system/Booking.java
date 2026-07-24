package movie_ticket_booking_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Booking {

    // Booking Details
    private int bookingId;
    private long mobileNumber;
    private int movieId;
    private String movieName;
    private String showTiming;
    private String ticketClass;
    private ArrayList<String> seatNumbers;
    private int noOfTickets;
    private double totalAmount;
    private String paymentMethod;
    private String bookingStatus;

    // Static Variables
    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, Booking> bookings = new HashMap<>();
    static ArrayList<String> bookedSeats = new ArrayList<>();

    static int bookingCounter = 1001;

    // Default Constructor
    public Booking() {

    }

    // Parameterized Constructor
    public Booking(long mobileNumber,
                   int movieId,
                   String movieName,
                   String showTiming,
                   String ticketClass,
                   ArrayList<String> seatNumbers,
                   int noOfTickets,
                   double totalAmount,
                   String paymentMethod) {

        this.bookingId = bookingCounter++;
        this.mobileNumber = mobileNumber;
        this.movieId = movieId;
        this.movieName = movieName;
        this.showTiming = showTiming;
        this.ticketClass = ticketClass;
        this.seatNumbers = seatNumbers;
        this.noOfTickets = noOfTickets;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.bookingStatus = "CONFIRMED";
    }

    public static void displaySeatLayout() {

        System.out.println("\n==============================================");
        System.out.println("              SCREEN THIS WAY");
        System.out.println("==============================================");

        System.out.println("\nPLATINUM (₹300)");

        for (char row = 'P'; row <= 'Q'; row++) {

            for (int i = 1; i <= 5; i++) {

                String seat = row + String.valueOf(i);

                if (bookedSeats.contains(seat))
                    System.out.print("[XX] ");
                else
                    System.out.print("[" + seat + "] ");
            }

            System.out.println();
        }

        System.out.println("\nGOLD (₹200)");

        for (char row = 'G'; row <= 'I'; row++) {

            for (int i = 1; i <= 5; i++) {

                String seat = row + String.valueOf(i);

                if (bookedSeats.contains(seat))
                    System.out.print("[XX] ");
                else
                    System.out.print("[" + seat + "] ");
            }

            System.out.println();
        }

        System.out.println("\nSILVER (₹150)");

        for (char row = 'S'; row <= 'U'; row++) {

            for (int i = 1; i <= 5; i++) {

                String seat = row + String.valueOf(i);

                if (bookedSeats.contains(seat))
                    System.out.print("[XX] ");
                else
                    System.out.print("[" + seat + "] ");
            }

            System.out.println();
        }

        System.out.println("\nXX = Already Booked");
        System.out.println("==============================================");
    }

    public static boolean isSeatAvailable(String seat) {

        seat = seat.toUpperCase();

        if (bookedSeats.contains(seat)) {

            System.out.println("Seat already booked.");
            return false;
        }

        return true;
    }

    public static double getTicketPrice(int choice) {

        switch (choice) {

            case 1:
                return 150;

            case 2:
                return 200;

            case 3:
                return 300;

            default:
                return 0;
        }
    }

    public static String getTicketClass(int choice) {

        switch (choice) {

            case 1:
                return "Silver";

            case 2:
                return "Gold";

            case 3:
                return "Platinum";

            default:
                return "";
        }
    }

    public static void bookTicket() {

        if (Customer.customers.isEmpty()) {

            System.out.println("\nNo Customers Registered.");
            return;
        }

        if (Movie.movies.isEmpty()) {

            System.out.println("\nNo Movies Available.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("             MOVIE TICKET BOOKING");
        System.out.println("==============================================");

        Customer customer = Customer.loggedInCustomer;

        if (customer == null) {

            System.out.println("Please Login First.");
            return;
        }

        long mobileNumber = customer.getMobileNumber();

        System.out.println("\nWelcome " + customer.getCustomerName() + "!");

        Movie movieObj = new Movie();
        movieObj.viewMovies();

        System.out.print("\nEnter Movie ID : ");
        int movieId = sc.nextInt();

        Movie movie = Movie.movies.get(movieId);

        if (movie == null) {

            System.out.println("Invalid Movie ID.");
            return;
        }

        String[] timings = movie.getShowTimings().split("\\|");

        System.out.println("\nChoose Show Timing");

        for (int i = 0; i < timings.length; i++) {

            System.out.println((i + 1) + ". " + timings[i].trim());
        }

        System.out.print("Enter Choice : ");
        int timingChoice = sc.nextInt();

        if (timingChoice < 1 || timingChoice > timings.length) {

            System.out.println("Invalid Show Timing.");
            return;
        }

        String selectedTiming = timings[timingChoice - 1].trim();

        System.out.println("\nTicket Classes");
        System.out.println("1. Silver (₹150)");
        System.out.println("2. Gold (₹200)");
        System.out.println("3. Platinum (₹300)");

        System.out.print("Choose Ticket Class : ");
        int classChoice = sc.nextInt();

        if (classChoice < 1 || classChoice > 3) {

            System.out.println("Invalid Ticket Class.");
            return;
        }

        String ticketClass = getTicketClass(classChoice);
        double ticketPrice = getTicketPrice(classChoice);

        int ticketCount;

        while (true) {

            System.out.print("Enter Number of Tickets (1-6) : ");
            ticketCount = sc.nextInt();

            if (ticketCount >= 1 && ticketCount <= 6)
                break;

            System.out.println("You can book minimum 1 and maximum 6 tickets.");
        }

        displaySeatLayout();

        ArrayList<String> seats = new ArrayList<>();

        System.out.println("\nEnter Seat Numbers");

        while (seats.size() < ticketCount) {

            System.out.print("Seat " + (seats.size() + 1) + " : ");
            String seat = sc.next().toUpperCase().trim();

            if (!seat.matches("^[PQGHISTU][1-5]$")) {
                System.out.println("Invalid Seat Number!");
                System.out.println("Valid Examples: P1, Q3, G5, H2, I4, S1, T3, U5");
                continue;
            }

            if (seats.contains(seat)) {
                System.out.println("Seat already entered.");
                continue;
            }

            if (bookedSeats.contains(seat)) {
                System.out.println("Seat already booked.");
                continue;
            }

            if (ticketClass.equalsIgnoreCase("Silver")
                    && !(seat.startsWith("S") || seat.startsWith("T") || seat.startsWith("U"))) {

                System.out.println("Choose only Silver seats (S, T, U).");
                continue;
            }

            if (ticketClass.equalsIgnoreCase("Gold")
                    && !(seat.startsWith("G") || seat.startsWith("H") || seat.startsWith("I"))) {

                System.out.println("Choose only Gold seats (G, H, I).");
                continue;
            }

            if (ticketClass.equalsIgnoreCase("Platinum")
                    && !(seat.startsWith("P") || seat.startsWith("Q"))) {

                System.out.println("Choose only Platinum seats (P, Q).");
                continue;
            }

            seats.add(seat);
            bookedSeats.add(seat);
        }

        double totalAmount = ticketPrice * ticketCount;

        System.out.println("\n=========== PAYMENT ===========");

        System.out.println("1. UPI");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.println("4. Cash");

        System.out.print("Choose Payment Method : ");
        int paymentChoice = sc.nextInt();

        String paymentMethod = "";

        switch (paymentChoice) {

            case 1:
                paymentMethod = "UPI";
                break;

            case 2:
                paymentMethod = "Debit Card";
                break;

            case 3:
                paymentMethod = "Credit Card";
                break;

            case 4:
                paymentMethod = "Cash";
                break;

            default:
                System.out.println("Invalid Payment Method.");
                return;
        }
        Booking booking = new Booking(
                mobileNumber,
                movie.getMovieId(),
                movie.getMovieName(),
                selectedTiming,
                ticketClass,
                seats,
                ticketCount,
                totalAmount,
                paymentMethod);

        bookings.put(booking.bookingId, booking);

        System.out.println("\n==============================================");
        System.out.println("          BOOKING SUCCESSFUL");
        System.out.println("==============================================");

        System.out.println("Booking ID      : " + booking.bookingId);
        System.out.println("Customer Name   : " + customer.getCustomerName());
        System.out.println("Mobile Number   : " + customer.getMobileNumber());
        System.out.println("Movie           : " + movie.getMovieName());
        System.out.println("Language        : " + movie.getLanguage());
        System.out.println("Show Timing     : " + selectedTiming);
        System.out.println("Ticket Class    : " + ticketClass);
        System.out.println("Seats           : " + seats);
        System.out.println("No. of Tickets  : " + ticketCount);
        System.out.println("Amount Paid     : ₹" + totalAmount);
        System.out.println("Payment Method  : " + paymentMethod);
        System.out.println("Booking Status  : CONFIRMED");

        System.out.println("==============================================");
        System.out.println("Thank You for Booking with");
        System.out.println("🎬 SILVER SCREEN CINEMAS 🎬");
        System.out.println("Enjoy Your Show!");
        System.out.println("==============================================");
    }

    // ==========================================
    // VIEW MY BOOKING
    // ==========================================

    public static void viewMyBooking(long mobileNumber) {

        boolean found = false;

        System.out.println("\n============== MY BOOKINGS ==============");

        for (Booking booking : bookings.values()) {

            if (booking.mobileNumber == mobileNumber) {

                found = true;

                System.out.println("-----------------------------------------");
                System.out.println("Booking ID      : " + booking.bookingId);
                System.out.println("Movie           : " + booking.movieName);
                System.out.println("Show Timing     : " + booking.showTiming);
                System.out.println("Ticket Class    : " + booking.ticketClass);
                System.out.println("Seats           : " + booking.seatNumbers);
                System.out.println("Tickets         : " + booking.noOfTickets);
                System.out.println("Amount          : ₹" + booking.totalAmount);
                System.out.println("Payment         : " + booking.paymentMethod);
                System.out.println("Status          : " + booking.bookingStatus);
            }
        }

        if (!found) {
            System.out.println("No Bookings Found.");
        }
    }

    // ==========================================
    // VIEW ALL BOOKINGS
    // ==========================================

    public static void viewAllBookings() {

        if (bookings.isEmpty()) {

            System.out.println("\nNo Bookings Available.");
            return;
        }

        System.out.println("\n============== ALL BOOKINGS ==============");

        for (Booking booking : bookings.values()) {

            System.out.println("-----------------------------------------");
            System.out.println("Booking ID      : " + booking.bookingId);
            System.out.println("Mobile Number   : " + booking.mobileNumber);
            System.out.println("Movie           : " + booking.movieName);
            System.out.println("Show Timing     : " + booking.showTiming);
            System.out.println("Ticket Class    : " + booking.ticketClass);
            System.out.println("Seats           : " + booking.seatNumbers);
            System.out.println("Tickets         : " + booking.noOfTickets);
            System.out.println("Amount          : ₹" + booking.totalAmount);
            System.out.println("Payment         : " + booking.paymentMethod);
            System.out.println("Status          : " + booking.bookingStatus);
        }
    }

    // ==========================================
    // CANCEL TICKET
    // ==========================================

    public static void cancelTicket() {

        if (bookings.isEmpty()) {

            System.out.println("\nNo Bookings Available.");
            return;
        }

        System.out.print("\nEnter Booking ID to Cancel : ");
        int bookingId = sc.nextInt();

        if (!bookings.containsKey(bookingId)) {

            System.out.println("Invalid Booking ID.");
            return;
        }

        Booking booking = bookings.get(bookingId);

        if (booking.bookingStatus.equalsIgnoreCase("CANCELLED")) {

            System.out.println("Booking Already Cancelled.");
            return;
        }

        booking.bookingStatus = "CANCELLED";

        for (String seat : booking.seatNumbers) {
            bookedSeats.remove(seat);
        }

        System.out.println("\nBooking Cancelled Successfully.");
    }

    // ==========================================
    // REVENUE REPORT
    // ==========================================

    public static void revenueReport() {

        double totalRevenue = 0;
        int totalBookings = 0;

        for (Booking booking : bookings.values()) {

            if (booking.bookingStatus.equalsIgnoreCase("CONFIRMED")) {

                totalRevenue += booking.totalAmount;
                totalBookings++;
            }
        }

        System.out.println("\n==========================================");
        System.out.println("            REVENUE REPORT");
        System.out.println("==========================================");
        System.out.println("Total Confirmed Bookings : " + totalBookings);
        System.out.println("Total Revenue            : ₹" + totalRevenue);
        System.out.println("==========================================");
    }

    // ==========================================
    // GETTER METHODS
    // ==========================================

    public int getBookingId() {
        return bookingId;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTiming() {
        return showTiming;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public ArrayList<String> getSeatNumbers() {
        return seatNumbers;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

}