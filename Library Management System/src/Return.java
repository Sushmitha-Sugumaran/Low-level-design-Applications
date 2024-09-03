public class Return extends Library {
    int b_id,user_id;
    String fine_amt,days;

    public Return(int b_id, int user_id, String fine_amt, String days) {
        this.b_id = b_id;
        this.user_id = user_id;
        this.fine_amt = fine_amt;
        this.days = days;
    }

    @Override
    public String toString() {
        return "Return{" +
                "b_id=" + b_id +
                ", user_id=" + user_id +
                ", fine_amt='" + fine_amt + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
