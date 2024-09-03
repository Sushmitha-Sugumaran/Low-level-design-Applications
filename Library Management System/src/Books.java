public class Books extends Library{
    String b_title,b_author,b_type;
    int b_id,b_count,b_stock;
    double b_price;

    public Books(String b_title, String b_author, String b_type, int b_id, int b_count, int b_stock, double b_price) {
        this.b_title = b_title;
        this.b_author = b_author;
        this.b_type = b_type;
        this.b_id = b_id;
        this.b_count = b_count;
        this.b_stock = b_stock;
        this.b_price = b_price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "b_title='" + b_title + '\'' +
                ", b_author='" + b_author + '\'' +
                ", b_type='" + b_type + '\'' +
                ", b_id=" + b_id +
                ", b_count=" + b_count +
                ", b_stock=" + b_stock +
                ", b_price='" + b_price + '\'' +
                '}';
    }
}
