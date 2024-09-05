import java.util.*;
public class CancelSection {
    public static void cancelTrip(int id) {
        Trip trip = UBERManagement.tripList.get(id);
        int riderId = trip.getRiderId();

        UBERManagement.tripList.remove(id);
        UBERManagement.tripsMap.computeIfAbsent(riderId, v -> new ArrayList<>()).remove(id);

        Drivers driver = UBERManagement.driversList.get(UBERManagement.riderInd);
        driver.setAvailable(true);
        driver.setCurrentSpot(trip.getPickUpPoint());
        driver.setFreeTime(trip.getPickUpTime());

        int distanceBetweenPickupDrop = distance(trip.getPickUpPoint(), trip.getDroppoint());
        int earning = calculateEarnings(distanceBetweenPickupDrop);
        System.out.println("YOU HAVE TO PAY THIS AMOUNT TO CANCELLATION : "+ earning/2);

        if (UBERManagement.riderList.get(UBERManagement.riderInd).getWallet() < earning){
            System.out.println("YOU CAN NOT CANCEL YOUR CAB....BALANCE IS LOW.SO ,FIRST ADD AMOUNT IN YOUR WALLET THEN CANCEL THE CAB");
            UBERManagement.checkBalanceInWallet();
            UBERManagement.addMoneyInWallet();
        }
        Rider rider = UBERManagement.riderList.get(UBERManagement.riderInd);
        rider.setWallet(rider.getWallet()-earning);

        driver.setTotalEarnings(driver.getTotalEarnings() - earning);
        System.out.println("CANCELLED SUCCESSFULLY ...");
        UBERManagement.orgRider();
    }

    private static int calculateEarnings(int distanceBetweenPickupDrop) {
        return (distanceBetweenPickupDrop - 5) * 10 + 100;
    }

    private static int distance(char currentSpot, char pickUpPoint) {
        return Math.abs(currentSpot-pickUpPoint)*15;
    }
}
