public class User {
    static int id = 1;
    int userId;
    String name;
    String phoneNumber;
    int wallet;
    String mailId;
    String password;

    public User(String name, String phoneNumber, int wallet, String mailId, String password) {
        this.userId = id++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
        this.mailId = mailId;
        this.password = password;
    }

    public static int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getWallet() {
        return wallet;
    }

    public String getMailId() {
        return mailId;
    }

    public String getPassword() {
        return password;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", wallet=" + wallet +
                ", mailId='" + mailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
