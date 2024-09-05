import java.util.*;
public class UBERManagement {
    static Scanner scan;
    static int driverInd;
    static int riderInd;
    static Map<String, Rider> riderMap;
    static Map<String, Drivers> driversMap;
    static Map<Integer, List<Rating>> ratingMap;
    static List<Rider> riderList;
    static List<Drivers> driversList;
    static List<Rating> ratingList;
    static List<Trip> tripList;
    static Map<Integer, List<Trip>> tripsMap;


    //----USE SINGLETON PATTERN DOUBLE LOCKING----
    private static volatile UBERManagement management; // STEP 1 OF PATTERN - CREATE VOLATILE STATIC PRIVATE CLASS INITIALIZATION

    private UBERManagement() {
        scan = new Scanner(System.in);
        riderMap = new HashMap<>();
        driversMap = new HashMap<>();
        ratingMap = new HashMap<>();
        riderList = new ArrayList<>();
        driversList = new ArrayList<>();
        ratingList = new ArrayList<>();
        tripList = new ArrayList<>();
        tripsMap = new HashMap<>();
    } // SETP 2: CREATE PRIVATE CONSTRUCTOR

    public static UBERManagement getManagement() {
        if (management == null) {
            synchronized (UBERManagement.class) {
                if (management == null) {
                    management = new UBERManagement();
                }
            }
        }
        return management;
    }//SETP 3 - DOUBLE LOCKING GET INSTANCE METHOD TO GET CLASS CREATION OBJECT AT ONCE

    //----INITIALIZE 4 DRIVERS----
    public void initializeDrivers() {
        driversList.add(new Drivers("Ram", "12345", "Male", "1111", "ram123@gmail.com", "Ram@123"));
        driversList.add(new Drivers("Shiva", "67890", "Male", "2222", "shiva123@gmail.com", "Shiva@123"));
        driversList.add(new Drivers("Mahi", "12367", "Male", "3333", "mahi123@gmail.com", "Mahi@123"));
        driversList.add(new Drivers("Seetha", "67812", "Female", "4444", "seetha123@gmail.com", "Seetha@123"));
        for (Drivers drivers : driversList) {
            driversMap.put(drivers.getMailId(), drivers);
        }
    }

    //----INITIALIZE 4 RIDERS----
    public void initializeRiders() {
        riderList.add(new Rider("Raman", "12345", "Male", 100,"raman123@gmail.com", "Raman@123"));
        riderList.add(new Rider("Shivani", "67890", "Female", 100,"shivani123@gmail.com", "Shivani@123"));
        riderList.add(new Rider("Maha", "12367", "Female", 100,"maha123@gmail.com", "Maha@123"));
        riderList.add(new Rider("Rajesh", "67812", "Male", 100,"rajesh123@gmail.com", "Rajesh@123"));
        for (Rider rider : riderList) {
            riderMap.put(rider.getMailId(), rider);
        }
    }

    //----UBER'S FRONT SECTION----
    public static void receptionSection() {
        System.out.println("----WELCOME TO UBER----");
        System.out.println("1-->DRIVER");
        System.out.println("2-->RIDER");
        System.out.println("3-->EXIT");
        System.out.print("Enter Your Choice --> ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                driverMenu();
                break;
            case 2:
                riderMenu();
                break;
            case 3:
                System.out.println("HAVE A GREAT DAY");
                System.exit(0);
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                receptionSection();
                break;
        }
    }

    //----DRIVER'S SECTION TO REGISTER OR LOGIN----
    private static void driverMenu() {
        System.out.println("--WELCOME TO DRIVER SECTION--");
        System.out.println("1-->REGISTER TO JOIN UBER TO DRIVE");
        System.out.println("2-->LOGIN");
        System.out.println("3-->EXIT");
        System.out.print("Enter Your Choice --> ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                newDriver();
                break;
            case 2:
                loginDriver();
                break;
            case 3:
                receptionSection();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                break;
        }
    }

    //----NEW DRIVER THEN REGISTER FIRST----
    private static void newDriver() {
        System.out.println("Enter your name,phone number,license number,gender,mail id,password");
        String name = scan.next();
        String phoneNo = scan.next();
        String gender = scan.next();
        String licenseNo = scan.next();
        String mailId = scan.next();
        String passWord = scan.next();
        if (!isValidDriver(driversMap, phoneNo, licenseNo, mailId, passWord)) {
            driversList.add(new Drivers(name, phoneNo, gender, licenseNo, mailId, passWord));
            driversMap.put(driversList.get(driversList.size() - 1).mailId, driversList.get(driversList.size() - 1));
        } else {
            System.out.println("USER ALREADY EXITS..ENTER VALID DETAILS");
            newDriver();
        }
        System.out.println("TRY TO LOGIN NOW");
        driverMenu();
        /* to check the list and map for correction
        for (Map.Entry<String, Drivers> ans :
                    driversMap.entrySet()) {
                System.out.println(ans.getKey()+" "+ans.getValue());
            }
        for (int i = 0; i < driversList.size(); i++) {
            System.out.println(driversList.get(i));
        }*/
    }

    //---CHECK ALREADY EXITING VALUES OR NOT
    private static boolean isValidDriver(Map<String, Drivers> driversMap, String phoneNo, String licenseNo, String mailId, String passWord) {
        for (Drivers map :
                driversMap.values()) {
            if (map.getPhoneNo().equals(phoneNo) || map.getLicenseNo().equals(licenseNo) || map.getMailId().equals(mailId) || map.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    //----EXISTING DRIVER THEN LOGIN----
    private static void loginDriver() {
        System.out.println("Enter your mailid and password");
        String mailId = scan.next();
        String passWord = scan.next();
        if (driversMap.containsKey(mailId)) {
            if (driversMap.get(mailId).getPassWord().equals(passWord)) {
                driverInd = driversList.indexOf(driversMap.get(mailId));
                System.out.println("SUCCESSFULLY LOGGED IN " + driversMap.get(mailId).getName());
                orgDriver();
            }
        } else {
            System.out.println("ENTER VALID DETAILS");
            loginDriver();
        }
    }

    //----RIDER'S SECTION TO REGISTER OR LOGIN----
    private static void riderMenu() {
        System.out.println("--WELCOME TO RIDER SECTION--");
        System.out.println("1-->REGISTER TO JOIN UBER TO BOOK YOUR TRIP");
        System.out.println("2-->LOGIN");
        System.out.println("3-->EXIT");
        System.out.print("Enter Your Choice --> ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                newRider();
                break;
            case 2:
                loginRider();
                break;
            case 3:
                receptionSection();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                riderMenu();
                break;
        }
    }

    //----NEW RIDER THEN REGISTER FIRST----
    private static void newRider() {
        System.out.println("Enter your name,phone number,gender,amount to add your wallet(min 100),mail id,password");
        String name = scan.next();
        String phoneNo = scan.next();
        String gender = scan.next();
        int wallet = scan.nextInt();
        String mailId = scan.next();
        String passWord = scan.next();
        if (!isValidRider(riderMap, phoneNo, mailId, passWord)) {
            riderList.add(new Rider(name, phoneNo, gender,wallet, mailId, passWord));
            riderMap.put(riderList.get(riderList.size() - 1).mailId, riderList.get(riderList.size() - 1));
        } else {
            System.out.println("USER ALREADY EXITS..ENTER VALID DETAILS");
            newRider();
        }
        System.out.println("TRY TO LOGIN NOW");
        riderMenu();
    }

    //---CHECK ALREADY EXITING VALUES OR NOT
    private static boolean isValidRider(Map<String, Rider> riderMap, String phoneNo, String mailId, String passWord) {
        for (Rider map : riderMap.values()) {
            if (map.getPhoneNo().equals(phoneNo) || map.getMailId().equals(mailId) || map.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    //----EXISTING RIDER THEN LOGIN TO BOOK YOUR TRIP----
    private static void loginRider() {
        System.out.println("Enter your mailid and password");
        String mailId = scan.next();
        String passWord = scan.next();
        if (riderMap.containsKey(mailId)) {
            if (riderMap.get(mailId).getPassWord().equals(passWord)) {
                riderInd = riderList.indexOf(riderMap.get(mailId));
                //System.out.println(riderInd);
                System.out.println("SUCCESSFULLY LOGGED IN " + riderMap.get(mailId).getName());
                orgRider();
            }
        } else {
            System.out.println("ENTER VALID DETAILS");
            loginRider();
        }
    }

    //----DRIVER'S SECTION TO SEE SUCH AS RATINGS,BONUS ETC...
    private static void orgDriver() {
        System.out.println("----WELCOME " + driversList.get(driverInd).getName() + " ----");
        System.out.println("1-->DISPLAY RATINGS WHICH IS GIVEN BY RIDERS TO ME");
        System.out.println("2-->DISPLAY AVERAGE OF MY OVERALL RATING SCORE");
        System.out.println("3-->DISPLAY BONUS POINTS");
        System.out.println("4-->EXIT");
        System.out.println("Enter Your Choice");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                ratingsOfEachRiders(driversList.get(driverInd).getDriverId());
                break;
            case 2:
                averageScoreOfRating(driversList.get(driverInd).getDriverId());
                break;
            case 3:
                bonusPoints();
                break;
            case 4:
                driverMenu();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                orgDriver();
                break;
        }
    }

    //----DISPLAY RATINGS WHICH IS GIVEN BY RIDERS TO THAT DRIVER
    private static void ratingsOfEachRiders(int driverId) {
        if (ratingMap.containsKey(driverId)) {
            for (Rating ratingOfDriver :
                    ratingMap.get(driverId)) {
                System.out.println(ratingOfDriver.getRiderName() + " Gives " + ratingOfDriver.getPoints());
            }
        }
        else{
            System.out.println("RATINGS ARE NOT AVAILABLE");
        }
        orgDriver();
    }

    //----DISPLAY AVERAGE OF  OVERALL RATING SCORE OF THAT DRIVER
    private static void averageScoreOfRating(int driverId) {
        int sum = 0;
        if (ratingMap.containsKey(driverId)) {
            for (Rating ratingOfDriver :
                    ratingMap.get(driverId)) {
                sum += ratingOfDriver.getPoints();
            }
            System.out.println("Average Score of Overall Rating " + sum / ratingMap.get(driverId).size());
        }
        else{
            System.out.println("RATINGS ARE NOT AVAILABLE");
        }
        orgDriver();
    }

    //----DISPLAY BONUS POINTS TO THAT DRIVER
    private static void bonusPoints() {
        if (driversMap.containsKey(driversList.get(driverInd).getMailId())) {
            System.out.println("Bonus Point : " + driversMap.get(driversList.get(driverInd).getMailId()).getBonusPt());
        }
        else {
            System.out.println("Bonus Point : 0");
        }
        orgDriver();
    }

    //----RIDER'S SECTION TO BOOK TRIP
    public static void orgRider() {
        System.out.println("----WELCOME " + riderList.get(riderInd).getName() + " ----");
        System.out.println("1-->BOOK TRIP");
        System.out.println("2-->CANCEL TRIP");
        System.out.println("3-->DISPLAY TRIPS HISTORY");
        System.out.println("4-->ADD MONEY IN WALLET");
        System.out.println("5-->CHECK BALANCE IN WALLET");
        System.out.println("6-->EXIT");
        System.out.println("Enter Your Choice");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                bookTrip();
                break;
            case 2:
                cancelTrip();
                break;
            case 3:
                displayTripsHistory();
                break;
            case 4:
                addMoneyInWallet();
                break;
            case 5:
                checkBalanceInWallet();
                break;
            case 6:
                riderMenu();
                break;
            default:
                System.out.println("ENTER VALID CHOICE");
                orgRider();
                break;
        }
    }

    //----DETAILS OF SOURCE AND DESTINATION TO BOOK TRIP
    public static void bookTrip() {
        if (riderList.get(riderInd).getWallet()>=100) {
            System.out.println("Enter pickUpPoint(A-F),dropPoint(A-F),pickUpTime");
            char pickUpPoint = scan.next().charAt(0);
            char dropPoint = scan.next().charAt(0);
            int pickUpTime = scan.nextInt();
            int riderId = riderList.get(riderInd).getRiderId();
            if (pickUpPoint >= 'A' && pickUpPoint <= 'F' && dropPoint >= 'A' && dropPoint <= 'F') {
                List<Drivers> availDrivers = availableDrivers(pickUpTime, pickUpPoint);
                BookingSection.bookTrip(riderId, pickUpPoint, dropPoint, pickUpTime, availDrivers);
            } else {
                System.out.println("ENTER VALID PICK UP AND DROP POINT");
                bookTrip();
            }
        }else {
            System.out.println("YOU HAVE TOO LOW BALANCE IN WALLET.SO CHECK YOUR WALLET");
            orgRider();
        }
    }

    //----GET FREE TAXIS OF THE DRIVERS
    private static List<Drivers> availableDrivers(int pickUpTime, char pickUpPoint) {
        List<Drivers> freeTaxisOfDrivers = new ArrayList<>();
        for (Drivers d : driversList) {
            if (d.isAvailable() && d.getFreeTime() <= pickUpTime && (Math.abs((d.getCurrentSpot() - '0') - (pickUpPoint - '0')) <= pickUpTime - d.getFreeTime())) {
                freeTaxisOfDrivers.add(d);
            }
        }
        return freeTaxisOfDrivers;
    }

    //----DETAILS TO CANCEL THE TRIP
    private static void cancelTrip() {
        //SHOW TRIP DETAILS OF THAT RIDER TO CANCEL
        boolean found = tripsHistoryToCancel();
        if (found) {
            System.out.println("Enter Trip Id ");
            int id = scan.nextInt();
            if (id < tripList.size()){
                CancelSection.cancelTrip(id);
            }
            else {
                System.out.println("ENTER VALID ID");
                cancelTrip();
            }
        } else {
            orgRider();
        }
    }

    //----DISPLAY TRIPS HISTORY OF THAT RIDER TO CANCEL THE TRIP
    private static boolean tripsHistoryToCancel() {
        int riderId = riderList.get(riderInd).getRiderId();
        if (!tripList.isEmpty()) {
            if (tripsMap.containsKey(riderInd)) {
                int i = 0;
                List<Trip> t = tripsMap.get(riderId);
                if (t != null && !t.isEmpty()) {
                    for (Trip trips : tripsMap.get(riderId)) {
                        System.out.print("TRIP ID "+ i + " : ");
                        System.out.println("PICKUP POINT : "+trips.getPickUpPoint()+" | DROP POINT : "+trips.droppoint+" | PICKUP TIME : "+trips.pickUpTime+" | DROP TIME : "+trips.dropTime);
                        i++;
                    }
                    return true;
                }
            }
        }
            System.out.println("TRIPS ARE NOT AVAILABLE TO CANCEL");
            return false;
    }

    //----DISPLAY TRIPS HISTORY OF THAT RIDER
    private static void displayTripsHistory () {
        int riderId = riderList.get(riderInd).getRiderId();
        if (!tripList.isEmpty()) {
            if (tripsMap.containsKey(riderInd)) {
                List<Trip> t = tripsMap.get(riderId);
                int i = 0;
                if (t != null && !t.isEmpty()) {
                    for (Trip trips : tripsMap.get(riderId)) {
                        System.out.print("TRIP ID "+ i + " : ");
                        System.out.println("PICKUP POINT : "+trips.getPickUpPoint()+" | DROP POINT : "+trips.droppoint+" | PICKUP TIME : "+trips.pickUpTime+" | DROP TIME : "+trips.dropTime);
                        i++;                    }
                }
            }
        }
        else{
            System.out.println("TRIPS HISTORY ARE NOT AVAILABLE");
        }
        orgRider();
    }

    //----ADD MONEY IN WALLET
    public static void addMoneyInWallet() {
        if (!riderMap.isEmpty()) {
            System.out.println("Enter your amount to add your wallet( Min Balace is required : 100)");
            int amount = scan.nextInt();
            Rider rider = riderList.get(riderInd);
            rider.setWallet(amount + rider.getWallet());
            System.out.println("WALLET UPDATED SUCCESSFULLY.. NEW BALANCE : " + rider.getWallet());
        }
        else{
            System.out.println("RIDER IS NOT AVAILABLE");
        }
        orgRider();
    }

    //----CHECK THE BALANCE IN WALLET
    public static void checkBalanceInWallet() {
        System.out.println("WALLET BALANCE IS : "+riderList.get(riderInd).getWallet());
        orgRider();
    }
}
