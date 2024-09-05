public class Rider {
    static int id = 0;
    int riderId;
    String name;
    String phoneNo;
    String gender;
    int wallet;
    String mailId;
    String passWord;

    public Rider(String name, String phoneNo, String gender,int wallet, String mailId, String passWord) {
        this.riderId =id++;
        this.name = name;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.wallet = wallet;
        this.mailId = mailId;
        this.passWord = passWord;
    }

    public static int getId() {
        return id;
    }

    public int getRiderId() {
        return riderId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMailId() {
        return mailId;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
