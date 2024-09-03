public class Users extends Library{
    String user_name, user_password;
    int user_id;
    double user_amt;

    public Users(String u_name, String u_password, int user_id, double user_amt) {
        this.user_name = u_name;
        this.user_password = u_password;
        this.user_id = user_id;
        this.user_amt = user_amt;
    }

    @Override
    public String toString() {
        return "Users{" +
                "u_name='" + user_name + '\'' +
                ", u_password='" + user_password + '\'' +
                ", user_id=" + user_id +
                ", user_amt=" + user_amt +
                '}';
    }
}
