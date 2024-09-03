import java.util.ArrayList;

public class Flight {
    // id for flight which is alloted everytime a new Flight Object is created
    static int id = 0;
    int flightID;
    //Availabel number of tickets currently in flight
    int tickets;
    //current price
    int price;
    //string list of all passenger details for printing
    ArrayList<String> passengerDetails;
    //list of all passenger IDs
    ArrayList<Integer> passengerIDs;
    //list of number of tickets booked by every passenger ID
    ArrayList<Integer> bookedTicketsPerPassenger;
    //list of cost paid by every passenger ID, used to calculate refund while cancelling
    ArrayList<Integer> passengerCost;

    Flight(){
        this.id=id+1;
        this.flightID=id;
        this.tickets=50;
        this.price=5000;
        this.passengerDetails=new ArrayList<>();
        this.bookedTicketsPerPassenger=new ArrayList<>();
        this.passengerIDs=new ArrayList<>();
        this.passengerCost=new ArrayList<>();
    }

    public void addPassengerDetails(String passengerDetail, int numberOfTickets, int passengerId) {
        passengerDetails.add(passengerDetail);
        bookedTicketsPerPassenger.add(tickets);
        passengerIDs.add(passengerId);
        passengerCost.add(tickets*price);
        //updating price using logic in the problem statement
        price+=200 * numberOfTickets;

        //updating available number of tickets
        tickets-= numberOfTickets;
        System.out.println("Booked successfully....");
    }
    public void cancelTicket(int passengerID) {
         int indexToRemove=passengerIDs.indexOf(passengerID);
         int ticketsToCancel=passengerCost.get(indexToRemove);
         if (indexToRemove<0){
             System.out.println("passenger id can not found...");
         }
         tickets-=ticketsToCancel;
         price-=200*ticketsToCancel;
         String passengerDetail=passengerDetails.get(indexToRemove);
        //calculate refund
        System.out.println("Refund Amount after cancel : " + passengerCost.get(indexToRemove));

        //remove details of passenger from all lists
        bookedTicketsPerPassenger.remove(indexToRemove);
        passengerIDs.remove(Integer.valueOf(passengerID));
        passengerDetails.remove(indexToRemove);
        passengerCost.remove(indexToRemove);

        System.out.println("Cancelled Booked Tickets Successfully!");

    }
    public void summary()
    {
        System.out.println("Flight ID " + flightID + " --" + "Remaining Tickets " + tickets + " --" +
                "Current Ticket Price " + price);
    }
    public void printDetails()
    {
        System.out.println("Flight ID " + flightID + "->");
        for(String detail : passengerDetails)
            System.out.println(detail);
    }
}
