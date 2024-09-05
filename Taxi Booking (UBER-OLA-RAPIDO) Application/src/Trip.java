public class Trip {
    int riderId;
    char pickUpPoint;
    char droppoint;
    int pickUpTime;
    int dropTime;

    public Trip(int riderId, char pickUpPoint, char droppoint, int pickUpTime, int dropTime) {
        this.riderId = riderId;
        this.pickUpPoint = pickUpPoint;
        this.droppoint = droppoint;
        this.pickUpTime = pickUpTime;
        this.dropTime = dropTime;
    }

    public int getRiderId() {
        return riderId;
    }

    public char getPickUpPoint() {
        return pickUpPoint;
    }

    public char getDroppoint() {
        return droppoint;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "riderId=" + riderId +
                ", pickUpPoint=" + pickUpPoint +
                ", droppoint=" + droppoint +
                ", pickUpTime=" + pickUpTime +
                ", dropTime=" + dropTime +
                '}';
    }
}
