import java.util.ArrayList;
import java.util.Scanner;

public class BookTicket {
    static int passengerId=1;
    public static void main(String[] args) {
        ArrayList<Flight> flights=new ArrayList<Flight>();
        for (int i = 0; i < 2; i++) {
            flights.add(new Flight());
        }
        Scanner scan=new Scanner(System.in);

        while (true){
            System.out.println("welcome....\n 1-->book ticket \n 2-->cancel ticket \n 3-->print details");
            int choice= scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("enter flight id to book...");
                    int fId = scan.nextInt();
                    if (fId > flights.size()) {
                        System.out.println("this type of flight not available...");
                        break;
                    }
                    Flight currentFlight = null;
                    for (Flight f : flights) {
                        if (fId == f.flightID) {
                            currentFlight = f;
                            break;
                        }
                    }
                    System.out.println("enter the tickets....");
                    int tickets = scan.nextInt();
                    if (tickets > currentFlight.tickets) {
                        System.out.println("this count of tickets is not available...");
                        break;
                    }
                    bookTicket(currentFlight, tickets);
                    passengerId++;
                }
                break;
                case 2:{
                System.out.println("Enter flight ID and passenger ID to cancel booking");
                int fid = scan.nextInt();

                //check if flight id is valid
                if (fid > flights.size()) {
                    System.out.println("Invalid flight ID");
                    break;
                }
                //find the corresponding flight
                Flight currentFlight = null;
                for (Flight f : flights) {
                    if (f.flightID == fid) {
                        currentFlight = f;
                        break;
                    }
                }
                //get passengerID from passenger to find the booking
                int id = scan.nextInt();

                //call the cancel Booking
                cancel(currentFlight, id);
            }
                break;
            //print details of flight along with passenger details
            case 3:
            {
                //loop though all available flights
                for(Flight f : flights)
                {
                    //check if flight has atleast 1 passenger detail
                    if(f.passengerDetails.size() == 0)
                    {
                        System.out.println("No passenger Details for  - Flight " + f.flightID);

                    }
                    else
                        print(f);
                }

                break;
            }
            default:
            {
                break;
            }
            }
        }
    }

    private static void bookTicket(Flight currentFlight, int tickets) {
        String passengerDetail="";

        // create detail about the passenger with the ID
        passengerDetail = "Passenger ID " + passengerId + " -- " + " Number of Tickets Booked "
                + tickets + " -- " + "Total cost " + currentFlight.price * tickets;
        //add passenger detail to flight object
        currentFlight.addPassengerDetails(passengerDetail,tickets,passengerId);

        currentFlight.summary();
        currentFlight.printDetails();
    }
    public static void cancel(Flight currentFlight, int passengerID)
    {
        // calling cancel function on the flight object
        currentFlight.cancelTicket(passengerID);
        currentFlight.summary();
        currentFlight.printDetails();
    }

    //function to print flightdetails
    public static void print(Flight f)
    {
        f.printDetails();
    }
}