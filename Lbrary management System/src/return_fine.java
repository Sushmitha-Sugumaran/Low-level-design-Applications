class return_fine extends Library {
    int user_id, book_id;
    String fine_amt, days;

    return_fine(int user_id, int book_id, String fine_amt, String days) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.fine_amt = fine_amt;
        this.days = days;
    }
}
