public class Borrow extends Library{
    String b_title,b_author,b_type;
    int b_id,b_count,user_id;
    double b_price;

    public Borrow(String b_title, String b_author, String b_type, int b_id, int b_count, int user_id, double b_price) {
        this.b_title = b_title;
        this.b_author = b_author;
        this.b_type = b_type;
        this.b_id = b_id;
        this.b_count = b_count;
        this.user_id = user_id;
        this.b_price = b_price;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "b_title='" + b_title + '\'' +
                ", b_author='" + b_author + '\'' +
                ", b_type='" + b_type + '\'' +
                ", b_id=" + b_id +
                ", b_count=" + b_count +
                ", user_id=" + user_id +
                ", b_price='" + b_price + '\'' +
                '}';
    }
}
