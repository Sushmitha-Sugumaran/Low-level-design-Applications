public class Movies {
    static int id = 1;
    int moviesId;
    String showSchedule;
    String showTime;
    String language;
    String movieName;
    String genre;
    int seatCount;
    int perTicketPrice;

    public Movies(String showSchedule, String showTime, String language, String movieName, String genre, int seatCount, int perTicketPrice) {
        this.moviesId = id++;
        this.showSchedule = showSchedule;
        this.showTime = showTime;
        this.language = language;
        this.movieName = movieName;
        this.genre = genre;
        this.seatCount = seatCount;
        this.perTicketPrice = perTicketPrice;
    }

    public String getShowSchedule() {
        return showSchedule;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getLanguage() {
        return language;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public int getPerTicketPrice() {
        return perTicketPrice;
    }

    public int getMoviesId() {
        return moviesId;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
