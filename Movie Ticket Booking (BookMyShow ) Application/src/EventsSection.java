import java.util.*;

public class EventsSection {
    static List<Events> eventsList = new ArrayList<>();
    static Map<Integer,Events> eventsMap = new HashMap<>();
    static int eventInd;
    static Scanner scan = new Scanner(System.in);

    public EventsSection(){
    }

        public static void initializeEvents() {
            eventsList.add(new Events("Morning","9:00AM","Tamil","Lovely","Love",40,100));
            eventsList.add(new Events("Afternoon","1:00PM","Tamil","ruby","Comedy",40,95));
            eventsList.add(new Events("Afternoon","1:00PM","Tamil","MagicShow","Comedy",40,200));
            eventsList.add(new Events("Evening","6:00PM","Telugu","LoveConcert","Music",10,195));
            eventsList.add(new Events("Night","10:00PM","Tamil","Alian","Comedy",20,150));
            for (int i = 0; i < eventsList.size(); i++) {
                eventsMap.put(eventsList.get(i).getEventsId(),eventsList.get(i));
            }
        }
    public static void bookEvent() {
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
                bookEvent();
                break;
        }
    }

    private static void bookingPart(String schedule) {
        displayEvents(schedule);
        String language = chooseLanguage();
        String genre = chooseGenre();
        String movie = chooseEvent(schedule,language,genre);
        int tickets = chooseTickets();
        String showTime = eventsMap.get(eventsList.get(eventInd).getEventsId()).getShowTime();
        String userName = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getName();
        boolean found = true;
        if (isValid(language,genre,movie,tickets)) {

            int totalTicketPrice = CalculatePrice.eventPay(eventsList, eventsMap, eventInd, BOOKMYSHOWManagament.userList, BOOKMYSHOWManagament.userInd, found, tickets);
            if (totalTicketPrice!=0) {

                System.out.println("TICKET BOOKED SUCCESSFULLY");

                BookingDetails booked = new BookingDetails(BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getUserId(), showTime, userName, movie, language, tickets, totalTicketPrice);
                MoviesSection.bookedList.add(booked);

                User user = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd);
                user.setWallet(user.getWallet() - totalTicketPrice);
                //System.out.println(user.getWallet());

                eventsList.get(eventInd).setSeatCount(eventsList.get(eventInd).getSeatCount()-tickets);
                eventsMap.get(eventsList.get(eventInd).getEventsId()).setSeatCount(eventsMap.get(eventsList.get(eventInd).getEventsId()).getSeatCount()-tickets);

                int userId = BOOKMYSHOWManagament.userList.get(BOOKMYSHOWManagament.userInd).getUserId();
                MoviesSection.bookedMap.computeIfAbsent(userId, k -> new ArrayList<BookingDetails>()).add(booked);
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
        for (Events mov :
                eventsMap.values()) {
            if (mov.getLanguage().equals(language) && mov.getGenre().equals(genre) && mov.getEventName().equals(movie) && mov.getSeatCount() >= tickets){
                return true;
            }
        }
        return false;
    }

    public static void displayEvents(String schedule) {
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s",
                "Event ID", "Event Lang", "Event Name","Event genre","Tickets", "Per Ticket Cost"));
        boolean foundMovies = false;
        for (int i = 0; i < eventsList.size(); i++) {
            if (eventsList.get(i).getShowSchedule().equals(schedule)) {
                System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s",eventsList.get(i).getEventsId() , eventsList.get(i).getLanguage() , eventsList.get(i).getEventName() ,
                        eventsList.get(i).getGenre() ,eventsList.get(i).getSeatCount() ,eventsList.get(i).getPerTicketPrice()));
                foundMovies = true;
            }
        }
        if (!foundMovies) {
            System.out.println("No events found for the given schedule.");
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

    private static String chooseEvent(String Schedule, String language, String genre) {
        System.out.println("Enter Event Name");
        String movie = scan.next();
        for (int i = 0; i < eventsList.size(); i++) {
            if (eventsList.get(i).getShowSchedule().equals(Schedule) && eventsList.get(i).getLanguage().equals(language) && eventsList.get(i).getGenre().equals(genre) && eventsList.get(i).getEventName().equals(movie)){
                eventInd = i;
                break;
            }
        }
        System.out.println(eventInd);
        return movie;
    }

    private static int chooseTickets() {
        int totalTickets = eventsList.get(eventInd).getSeatCount();
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
        System.out.println("----------------------EVENT NAME : "+booked.getMovieName()+"-------------------------");
        System.out.println("----------------------LANGUAGE : "+booked.getLanguage()+"----------------------------");
        System.out.println("----------------------TICKETS : "+booked.getTickets()+"---------------------------------");
        System.out.println("--------------TOTAL PRICE OF THE TICKET : "+booked.getAmount()+"---------------------");
        System.out.println("------------------------------------------------------------------");
    }
}
