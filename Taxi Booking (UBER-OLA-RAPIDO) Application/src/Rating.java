public class Rating {
    int driverId;
    String riderName;
    String driverName;
    int points;

    public Rating(int driverId, String riderName, String driverName, int points) {
        this.driverId = driverId;
        this.riderName = riderName;
        this.driverName = driverName;
        this.points = points;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getRiderName() {
        return riderName;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getPoints() {
        return points;
    }
}
