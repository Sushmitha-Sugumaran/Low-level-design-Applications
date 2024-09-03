class users extends Library {
    String u_name, u_pass;
    int u_id;
    double u_amt;

    users(String u_name, String u_pass, double u_amt, int u_id) {
        this.u_name = u_name;
        this.u_pass = u_pass;
        this.u_amt = u_amt;
        this.u_id = u_id;
    }
}
