class borrow extends Library {
    String b_title, b_auth, b_type;
    int b_id, b_count, u_id;
    double b_price;

    borrow(String b_title, String b_auth, String b_type, double b_price, int b_id, int b_count, int u_id) {
        this.b_title = b_title;
        this.b_auth = b_auth;
        this.b_price = b_price;
        this.b_id = b_id;
        this.b_type = b_type;
        this.b_count = b_count;
        this.u_id = u_id;
    }

    public String toString() {
        return b_title + " " + b_auth + " " + b_type + " " + b_price + " " + b_id + " " + b_count;
    }
}
