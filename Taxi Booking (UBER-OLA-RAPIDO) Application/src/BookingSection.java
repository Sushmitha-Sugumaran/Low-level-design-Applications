import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BookingSection {
    //----BOOK THE TRIP
    public static void bookTrip(int riderId, char pickUpPoint, char dropPoint, int pickUpTime, List<Drivers> availDrivers) {
        PriorityQueue<Drivers> nearByDrivers = new PriorityQueue<>((a, b) ->
                Integer.compare(distance(a.getCurrentSpot(), pickUpPoint), distance(b.getCurrentSpot(), pickUpPoint)));

        // Only add available drivers to the queue
        for (Drivers driver : availDrivers) {
            if (driver.isAvailable() && driver.getFreeTime() <= pickUpTime) {
                nearByDrivers.add(driver);
            }
        }

        Drivers bookedDriver = null;
        while (!nearByDrivers.isEmpty() && bookedDriver == null) {
            Drivers candidateDriver = nearByDrivers.poll();
            if (candidateDriver.isAvailable() && candidateDriver.getFreeTime() <= pickUpTime) {
                bookedDriver = candidateDriver;
            }
        }

        if (bookedDriver == null) {
            System.out.println("NO DRIVERS AVAILABLE");
            UBERManagement.orgRider();
            return;
        }

        int distanceBetweenPickupDrop = distance(pickUpPoint, dropPoint);
        int earning = calculateEarnings(distanceBetweenPickupDrop);
        int dropTime = pickUpTime + distanceBetweenPickupDrop / 15;

        String mailId = UBERManagement.riderList.get(UBERManagement.riderInd).getMailId();
        if (!UBERManagement.riderMap.isEmpty() && earning > UBERManagement.riderMap.get(mailId).getWallet()){
            System.out.println("YOU CAN NOT BOOK YOUR CAB....BALANCE IS LOW");
            UBERManagement.checkBalanceInWallet();
            UBERManagement.addMoneyInWallet();
        }

        Rider rider = UBERManagement.riderList.get(UBERManagement.riderInd);
        rider.setWallet(rider.getWallet()-earning);

        Trip newTrip = new Trip(riderId, pickUpPoint, dropPoint, pickUpTime, dropTime);
        UBERManagement.tripList.add(newTrip);

        UBERManagement.tripsMap.computeIfAbsent(riderId, v -> new ArrayList<>()).add(newTrip);

        bookedDriver.setAvailable(false);
        bookedDriver.setCurrentSpot(dropPoint);
        bookedDriver.setFreeTime(dropTime);
        bookedDriver.setTotalEarnings(bookedDriver.getTotalEarnings() + earning);

        System.out.println("BOOKED SUCCESSFULLY ...");
        System.out.println("DRIVER'S NAME : " + bookedDriver.getName());
        getRatings();
        UBERManagement.orgRider();
    }

    //----RATINGS GIVEN BY RIDER
    private static void getRatings() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Give Ratings of the trip");
        int rating = scan.nextInt();
        Drivers d = UBERManagement.driversList.get(UBERManagement.driverInd);

        if (rating <= 2) {
            d.setBonusPt(d.getBonusPt() + 2);
        } else {
            d.setBonusPt(d.getBonusPt() + 4);
        }

        int riderInd = UBERManagement.riderInd;
        if (UBERManagement.ratingMap.containsKey(d.getName())) {
            UBERManagement.ratingMap.get(d.getName()).add(new Rating(d.getDriverId(), UBERManagement.riderList.get(riderInd).getName(), d.getName(), rating));
        }
        else{
            List<Rating> r = new ArrayList<>();
            r.add(new Rating(d.getDriverId(), UBERManagement.riderList.get(riderInd).getName(), d.getName(), rating));
            UBERManagement.ratingMap.put(d.getDriverId(),r);
        }

        System.out.println("THANK YOU FOR YOUR FEEDBACK");
    }

    //----CALCULATE THE TOTAL EARNINGS
    private static int calculateEarnings(int distanceBetweenPickupDrop) {
        return (distanceBetweenPickupDrop - 5) * 10 + 100;
    }

    //----CALCULATE THE DISTANCE OF CURRENT SPOT OF CAB DRIVER  AND PICK UP POINT OF RIDER
    private static int distance(char currentSpot, char pickUpPoint) {
        return Math.abs(currentSpot-pickUpPoint)*15;
    }
}
