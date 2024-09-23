import java.util.*;

public class BOOKMYSHOWManagament {
    static Scanner scan;
    static List<User> userList;
    static Map<String,User> userMap;

    static int userInd;

    //USE SINGLETON PATTERN TO OBJECT CREATION DONE AT ONCE
    public static volatile BOOKMYSHOWManagament managament; //STEP 1:OBJECT INSTANSIATED

    private BOOKMYSHOWManagament() {// STEP 2 PRIVATE CONSTRUCTOR
        scan = new Scanner(System.in);
        userList = new ArrayList<>();
        userMap = new HashMap<>();
    }

    public static BOOKMYSHOWManagament getmanagement() {
        if (managament==null){
            synchronized (BOOKMYSHOWManagament.class){
                if (managament==null){
                    managament = new BOOKMYSHOWManagament();
                }
            }
        }
        return managament;
    }//STEP 3: METHOD TO GET OBJECT

    // INITIALIZE SOME USERS
    public void initializeUser() {
        userList.add(new User("Ram","12345",500,"ram123@gmail.com","Ram@123"));
        userList.add(new User("Shiva","567890",300,"shiva123@gmail.com","Shiva@123"));
        userList.add(new User("Sita","12367",400,"sita123@gmail.com","Sita@123"));
        userList.add(new User("Ramesh","67845",500,"ramesh123@gmail.com","Ramesh@123"));
        userList.add(new User("Priya","06745",600,"priya123@gmail.com","Priya@123"));
        for (int i = 0; i < userList.size(); i++) {
            userMap.put(userList.get(i).getMailId(),userList.get(i));
        }
    }

    // CHOOSE CHOICE TO LOGIN OR REGISTER
    public static void receptionSection() {
        System.out.println("--WELCOME TO BOOKMYSHOW APPLICATION");
        System.out.println("1-->ALREADY HAVE ACCOUNT --> LOGIN");
        System.out.println("2-->DON'T HAVE ACCOUNT --> REGISTER");
        System.out.println("3-->EXIT");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                loginMenu();
                break;
            case 2:
                registerMenu();
                break;
            case 3:
                System.out.println("THANK YOU");
                System.exit(0);
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                receptionSection();
                break;
        }
    }

    // LOGIN IF EXISTING USER
    public static void loginMenu() {
        System.out.println("Enter your Email and Password To Login..");
        String email = scan.next();
        String password = scan.next();
        if (userMap.containsKey(email)){
            if (userMap.get(email).getPassword().equals(password)){
                userInd = userList.indexOf(userMap.get(email));
                //System.out.println(userInd);
                System.out.println("LOGGED IN SUCCESSFULLY : "+userMap.get(email).getName());
                orgUserMenu();
            }
        }
        else {
            System.out.println("ENTER VALID DETAILS");
            loginMenu();
        }
    }

    // REGISTER IF NEW USER
    public static void registerMenu() {
        System.out.println("Enter Name ,Phonenumber, amount to store your wallet, Mailid and set your Password To Register");
        String name = scan.next();
        String phoneNumber = scan.next();
        int wallet = scan.nextInt();
        String mailId = scan.next();
        String password = scan.next();
        if (!isValid(userMap,phoneNumber,mailId,password)){
            userList.add(new User(name, phoneNumber, wallet, mailId, password));
            userMap.put(userList.get(userList.size()-1).getMailId(),userList.get(userList.size()-1));
        }
        else {
            System.out.println("USER ALREADY EXISTS...TRY AGAIN");
            registerMenu();
        }
        System.out.println("NOW TRY TO LOGIN");
        receptionSection();
    }

    //---CHECK ALREADY EXITING VALUES OR NOT
    private static boolean isValid(Map<String, User> userMap, String phoneNumber, String mailId, String password) {
        for (User users :
                userMap.values()) {
            if (users.getPhoneNumber().equals(phoneNumber) || users.getMailId().equals(mailId) || users.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    // MAIN SECTION TO CHOOSING CHOICE LIKE MOVIE,PLAYS,EVENTS ETC.,
    public static void orgUserMenu() {
        System.out.println("WELCOME "+userList.get(userInd).getName());
        System.out.println("BOOK YOUR ONLINE TICKET FROM BELOW CATOGORIES..");

        System.out.println("1-->MOVIES");//COMEDY , ACTIONS MOVIES ETC.,
        System.out.println("2-->EVENTS");//MUSIC SHOWS ,COMEDY SHOWS, KIDS SHOWS ETC .,
        System.out.println("3-->SEE BOOKED HISTORY");
        System.out.println("4-->SHOW TICKETS");
        System.out.println("5-->ADD MONEY IN WALET");
        System.out.println("6-->DISPLAY WALLET");
        System.out.println("7-->EXIT");

        int choice = scan.nextInt();
        switch (choice){
            case 1:
                MoviesSection.bookMovie();
                break;
            case 2:
                EventsSection.bookEvent();
                break;
            case 3:
                bookedHistory();
                break;
            case 4:
                showTicket();
                break;
            case 5:
                addMoneyInWallet();
                break;
            case 6:
                displayWallet();
                break;
            case 7:
                receptionSection();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                orgUserMenu();
                break;
        }
    }

    // DISPLAY BOOKED HISTORY
    private static void bookedHistory() {
        if (!MoviesSection.bookedList.isEmpty()) {
            System.out.println(String.format("%-15s%-35s%-15s%-15s%-15s%-15s%-15s",
                    "Booking ID", "Today Time", "Show Time", "Movie Name", "Movie Lang", "Tickets", "Total Ticket Cost"));
            for (BookingDetails movie : MoviesSection.bookedList) {
                if (movie.getUserId() == userList.get(userInd).getUserId()) {
                    System.out.println(String.format("%-15s%-35s%-15s%-15s%-15s%-15s%-15s",movie.getBookId() ,movie.getTodayTime() ,  movie.getShowTime() ,movie.getMovieName(),
                            movie.getLanguage() ,movie.getTickets() , movie.getAmount())); }
            }
        }
        else {
            System.out.println("Booking History is empty..");
            orgUserMenu();
        }
        orgUserMenu();
    }

    // SHOW THE BOOKED TICKET
    private static void showTicket() {
        for (BookingDetails movie : MoviesSection.bookedList) {
            System.out.println(String.format("%-15s%-35s%-15s%-15s%-15s%-15s%-15s",
                    "Booking ID", "Today Time", "Show Time", "Movie Name", "Movie Lang", "Tickets", "Total Ticket Cost"));
            if (movie.getUserId() == userList.get(userInd).getUserId()) {
                System.out.println(String.format("%-15s%-35s%-15s%-15s%-15s%-15s%-15s",movie.getBookId() ,movie.getTodayTime() ,  movie.getShowTime() ,movie.getMovieName(),
                        movie.getLanguage() ,movie.getTickets() , movie.getAmount()));
                //System.out.println();
            }
        }

        if (!MoviesSection.bookedList.isEmpty()) {
            System.out.println("Enter booking id to see your ticket");
            int bookId = scan.nextInt();
            boolean found = false;
            List<BookingDetails> bookedl = MoviesSection.bookedMap.get(userList.get(userInd).getUserId());
            for (int i = 0; i < MoviesSection.bookedList.size(); i++) {
                if (MoviesSection.bookedList.get(i).getBookId() == bookId) {
                    BookingDetails booked = MoviesSection.bookedList.get(i);
                    MoviesSection.showTicket(booked);
                    found = true;
                    orgUserMenu();
                }
            }
            if (!found) {
                System.out.println("ENTER VALID BOOKING ID");
                showTicket();
            }
        }
        else{
            System.out.println("BOOKING HISTORY IS EMPTY..");
            orgUserMenu();
        }
    }

    // DISPLAY MONEY IN WALLET
    private static void displayWallet() {
        System.out.println("TOTAL AMOUNT IN YOUR WALLET : "+userList.get(userInd).getWallet());
        orgUserMenu();
    }

    // ADD MONEY IN WALLET
    public static void addMoneyInWallet() {
        System.out.println("Enter the amount");
        int wallet = scan.nextInt();
        userList.get(userInd).setWallet(wallet + userList.get(userInd).getWallet());
        userMap.get(userList.get(userInd).getMailId()).setWallet(wallet + userMap.get(userList.get(userInd).getMailId()).getWallet());
        System.out.println("AMOUNT ADDED IN YOUR WALLET SUCCESSFULLY");
        orgUserMenu();
    }

}
