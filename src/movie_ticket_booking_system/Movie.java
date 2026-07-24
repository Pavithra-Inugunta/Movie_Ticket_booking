package movie_ticket_booking_system;

import java.util.HashMap;
import java.util.Scanner;

public class Movie {

    private int movieId;
    private String movieName;
    private String category;
    private String language;
    private String duration;
    private String showTimings;

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, Movie> movies = new HashMap<>();

    // Default Constructor
    public Movie() {

    }

    // Parameterized Constructor
    public Movie(int movieId, String movieName, String category,
                 String language, String duration, String showTimings) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.category = category;
        this.language = language;
        this.duration = duration;
        this.showTimings = showTimings;
    }

    // Getters
    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getDuration() {
        return duration;
    }

    public String getShowTimings() {
        return showTimings;
    }

    // Add Default Movies
    public void addMovies() {

        movies.put(101, new Movie(101, "Kalki 2898 AD", "Sci-Fi", "Telugu", "2h 45m", "10:00 AM | 2:00 PM | 6:00 PM"));
        movies.put(102, new Movie(102, "Pushpa 2", "Action", "Telugu", "3h 10m", "11:00 AM | 3:00 PM | 7:00 PM"));
        movies.put(103, new Movie(103, "Coolie", "Action", "Tamil", "2h 40m", "9:30 AM | 1:30 PM | 5:30 PM"));
        movies.put(104, new Movie(104, "Kingdom", "Action", "Telugu", "2h 30m", "10:30 AM | 2:30 PM | 6:30 PM"));
        movies.put(105, new Movie(105, "Leo", "Action", "Tamil", "2h 44m", "11:30 AM | 3:30 PM | 7:30 PM"));
        movies.put(106, new Movie(106, "Hi Nanna", "Romance", "Telugu", "2h 35m", "9:00 AM | 1:00 PM | 5:00 PM"));
        movies.put(107, new Movie(107, "Salaar", "Action", "Telugu", "2h 55m", "10:15 AM | 2:15 PM | 6:15 PM"));
        movies.put(108, new Movie(108, "Jailer", "Action", "Tamil", "2h 48m", "11:45 AM | 3:45 PM | 7:45 PM"));
        movies.put(109, new Movie(109, "Lucky Baskhar", "Drama", "Telugu", "2h 30m", "9:45 AM | 1:45 PM | 5:45 PM"));
        movies.put(110, new Movie(110, "Amaran", "Biography", "Tamil", "2h 49m", "10:45 AM | 2:45 PM | 6:45 PM"));
    }

    // View Movies
 // View Movies
    public void viewMovies() {

        System.out.println("\n+---------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                         AVAILABLE MOVIES                                                     |");
        System.out.println("+---------------------------------------------------------------------------------------------------------------+");

        System.out.printf("| %-4s | %-18s | %-10s | %-8s | %-8s | %-10s | %-10s | %-10s |%n",
                "ID", "Movie Name", "Category", "Language", "Duration",
                "Morning", "Afternoon", "Evening");

        System.out.println("+------+--------------------+------------+----------+----------+------------+------------+------------+");

        for (Movie movie : movies.values()) {

            String[] timings = movie.getShowTimings().split("\\|");

            System.out.printf("| %-4d | %-18s | %-10s | %-8s | %-8s | %-10s | %-10s | %-10s |%n",
                    movie.getMovieId(),
                    movie.getMovieName(),
                    movie.getCategory(),
                    movie.getLanguage(),
                    movie.getDuration(),
                    timings[0].trim(),
                    timings[1].trim(),
                    timings[2].trim());
        }

        System.out.println("+------+--------------------+------------+----------+----------+------------+------------+------------+");
    }
      

    // Search Movie
    public void searchMovie() {

        System.out.print("Enter Movie ID: ");
        int id = sc.nextInt();

        if (movies.containsKey(id)) {

            Movie movie = movies.get(id);

            System.out.println("\n================ MOVIE DETAILS ================");

            System.out.println("Movie ID      : " + movie.movieId);
            System.out.println("Movie Name    : " + movie.movieName);
            System.out.println("Category      : " + movie.category);
            System.out.println("Language      : " + movie.language);
            System.out.println("Duration      : " + movie.duration);
            System.out.println("Show Timings  : " + movie.showTimings);

        } else {

            System.out.println("Movie Not Found.");

        }
    }
}