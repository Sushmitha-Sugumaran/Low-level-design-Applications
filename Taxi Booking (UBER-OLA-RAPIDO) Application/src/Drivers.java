public class Drivers {
    static int taxiIds = 0;
    static int id = 0;
    int driverId;
    String name;
    String phoneNo;
    String gender;
    String licenseNo;
    String mailId;
    String passWord;
    int taxiId;
    int bonusPt;
    boolean available;
    char currentSpot; //WHERE TAXI IS NOW
    int freeTime; //WHEN TAXI BECOMES FREE
    int totalEarnings; //TOTAL EARNINGS OF TAXI

    public Drivers(String name, String phoneNo, String gender, String licenseNo, String mailId, String passWord) {
        this.driverId = id++;
        this.taxiId = taxiIds++;
        this.name = name;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.licenseNo = licenseNo;
        this.mailId = mailId;
        this.passWord = passWord;
        this.bonusPt = 0;
        this.available = true;
        this.currentSpot = 'A';
        this.freeTime = 9;
        this.totalEarnings = 0;
    }

    public static int getId() {
        return id;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getMailId() {
        return mailId;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getBonusPt() {
        return bonusPt;
    }

    public boolean isAvailable() {
        return available;
    }

    public char getCurrentSpot() {
        return currentSpot;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBonusPt(int bonusPt) {
        this.bonusPt = bonusPt;
    }

    public void setCurrentSpot(char currentSpot) {
        this.currentSpot = currentSpot;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", gender='" + gender + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                ", mailId='" + mailId + '\'' +
                ", passWord='" + passWord + '\'' +
                ", taxiId=" + taxiId +
                ", bonusPt=" + bonusPt +
                ", available=" + available +
                ", currentSpot=" + currentSpot +
                ", freeTime=" + freeTime +
                ", totalEarnings=" + totalEarnings +
                '}';
    }
}
