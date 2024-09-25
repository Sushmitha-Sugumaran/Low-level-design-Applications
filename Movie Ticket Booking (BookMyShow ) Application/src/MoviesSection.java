import java.util.*;

public class MoviesSection {
    static List<Movies> moviesList = new ArrayList<>();
    static Map<Integer,Movies> moviesMap = new HashMap<>();
    static int movieInd ;
    static Scanner scan = new Scanner(System.in);
    static List<BookingDetails> bookedList = new ArrayList<>();
    static Map<Integer,List<BookingDetails>> bookedMap = new HashMap<>();


    public MoviesSection(){
    }

    public static void initializeMovies() {
        moviesList.add(new Movies("Morning","9:00AM","Tamil","LovelyStory","Love",40,100));
        moviesList.add(new Movies("Afternoon","1:00PM","Tamil","Enemy","Action",40,95));
        moviesList.add(new Movies("Afternoon","1:00PM","Tamil","Secret","Action",40,200));
        moviesList.add(new Movies("Evening","6:00PM","Telugu","Lover","Love",10,195));
        moviesList.add(new Movies("Night","10:00PM","Tamil","SeventhProof","Thriller",20,150));
        for (int i = 0; i < moviesList.size(); i++) {
            moviesMap.put(moviesList.get(i).getMoviesId(),moviesList.get(i));
        }
    }

    public static void bookMovie() {
        System.out.println("Enter your show schedule timing that you want");
        System.out.println("1-->MORNING");
        System.out.println("2-->AFTERNOON");
        System.out.println("3-->EVENING");
        System.out.println("4-->NIGHT");
        System.out.println("5-->EXIT");
        System.out.println("Enter Your Choice");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                String schedule1 = "Morning";
                bookingPart(schedule1);
                break;
            case 2:
                String schedule2 = "Afternoon";
                bookingPart(schedule2);
                break;
            case 3:
                String schedule3 = "Evening";
                bookingPart(schedule3);
                break;
            case 4:
                String schedule4 = "Night";
                bookingPart(schedule4);
                break;
            case 5:
                BOOKMYSHOWManagament.orgUserMenu();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                bookMovie();
                break;
        }
    }

    private static void bookingPart(String schedule) {
        displayMovies(schedule);
        String language = chooseLanguage();
        String genre = chooseGenre();
        String movie = chooseMovie(schedule,language,genre);
        int tickets = chooseTickets();
        String showTime = moviesMap.get(moviesList.get(movieInd).getMoviesId()).getShowTime();
        String userName = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getName();
        boolean found = true;
        if (isValid(language,genre,movie,tickets)) {

            int totalTicketPrice = CalculatePrice.moviePay(moviesList, moviesMap, movieInd, BOOKMYSHOWManagament.userList, BOOKMYSHOWManagament.userInd, found, tickets);
            if (totalTicketPrice!=0) {

                System.out.println("TICKET BOOKED SUCCESSFULLY");

                BookingDetails booked = new BookingDetails(BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getUserId(), showTime, userName, movie, language, tickets, totalTicketPrice);
                bookedList.add(booked);

                User user = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd);
                user.setWallet(user.getWallet() - totalTicketPrice);
                //System.out.println(user.getWallet());

                moviesList.get(movieInd).setSeatCount(moviesList.get(movieInd).getSeatCount()-tickets);
                moviesMap.get(moviesList.get(movieInd).getMoviesId()).setSeatCount(moviesMap.get(moviesList.get(movieInd).getMoviesId()).getSeatCount()-tickets);

                int userId = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getUserId();
                bookedMap.computeIfAbsent(userId, k -> new ArrayList<BookingDetails>()).add(booked);
                showTicket(booked);
            }
            else {
                System.out.println("ADD MONEY IN YOUR WALLET THEN BOOK YOUR TICKET");
                BOOKMYSHOWManagament.addMoneyInWallet();
                bookingPart(schedule);
            }
        }
        else {
            System.out.println("ENTER VALID DETAILS");
            bookingPart(schedule);
        }
        BOOKMYSHOWManagament.orgUserMenu();
    }

    private static boolean isValid(String language, String genre, String movie, int tickets) {
        for (Movies mov :
                moviesMap.values()) {
            if (mov.getLanguage().equals(language) && mov.getGenre().equals(genre) && mov.getMovieName().equals(movie) && mov.getSeatCount() >= tickets){
                return true;
            }
        }
        return false;
    }

    public static void displayMovies(String schedule) {
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s  %-15s",
                "Movie ID", "Movie Lang", "Movie Name","Movie genre","Tickets", "Per Ticket Cost"));
        boolean foundMovies = false;
        for (int i = 0; i < moviesList.size(); i++) {
            if (moviesList.get(i).getShowSchedule().equals(schedule)) {
                System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s  %-15s",moviesList.get(i).getMoviesId(),moviesList.get(i).getLanguage(),moviesList.get(i).getMovieName(),
                        moviesList.get(i).getGenre(),moviesList.get(i).getSeatCount() ,moviesList.get(i).getPerTicketPrice()));
                foundMovies = true;
            }
        }
        if (!foundMovies) {
            System.out.println("No movies found for the given schedule.");
            BOOKMYSHOWManagament.orgUserMenu();
        }
    }

    private static String chooseLanguage() {
        System.out.println("Enter Language");
        String language = scan.next();
        return language;
    }

    private static String chooseGenre() {
        System.out.println("Enter Genre");
        String genre = scan.next();
        return genre;
    }

    private static String chooseMovie(String Schedule, String language, String genre) {
        System.out.println("Enter Movie Name");
        String movie = scan.next();
        for (int i = 0;i < moviesList.size();i++) {
            if (moviesList.get(i).getShowSchedule().equals(Schedule) && moviesList.get(i).getLanguage().equals(language) && moviesList.get(i).getGenre().equals(genre) && moviesList.get(i).getMovieName().equals(movie)){
                movieInd = i;
                break;
            }
        }
        //System.out.println(movieInd);
        return movie;
    }

    private static int chooseTickets() {
        int totalTickets = moviesList.get(movieInd).getSeatCount();
        System.out.println("Total Tickets For this Movie : "+totalTickets);
        System.out.println("Enter ticket count that you want");
        int tickets = scan.nextInt();
        if (tickets>totalTickets){
            System.out.println("CHOOSE VALID TICKET COUNT WHICH SHOULD BE LESS THAN TOTAL TICKETS");
            chooseTickets();
        }
        return tickets;
    }

    public static void showTicket(BookingDetails booked) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("---------------------------MOVIE TICKET---------------------------");
        System.out.println("--------------TODAY DATE : "+booked.getTodayTime()+"--------------");
        System.out.println("----------------------BOOKED ID : "+booked.getBookId()+"----------------------------");
        System.out.println("----------------------SHOW TIME : "+booked.getShowTime()+"--------------------------");
        System.out.println("----------------------BOOKER NAME : "+booked.getUserName()+"------------------------");
        System.out.println("----------------------MOVIE NAME : "+booked.getMovieName()+"-------------------------");
        System.out.println("----------------------LANGUAGE : "+booked.getLanguage()+"----------------------------");
        System.out.println("----------------------TICKETS : "+booked.getTickets()+"---------------------------------");
        System.out.println("--------------TOTAL PRICE OF THE TICKET : "+booked.getAmount()+"---------------------");
        System.out.println("------------------------------------------------------------------");
    }
}
