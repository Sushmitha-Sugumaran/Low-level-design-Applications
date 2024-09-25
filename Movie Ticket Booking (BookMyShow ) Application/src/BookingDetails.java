import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingDetails{
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
    static int id = 1234;
    int userId;
    int bookId;
    String showTime;
    static LocalDateTime dateFormat;
    String todayTime;
    String userName;
    String movieName;
    String language;
    int tickets;
    int amount;

    public BookingDetails(int userId,String showTime, String userName, String movieName, String language, int tickets, int amount) {
        this.userId = userId;
        this.bookId = id++;
        this.showTime = showTime;
        dateFormat = LocalDateTime.now();
        this.todayTime = myFormatObj.format(dateFormat);
        this.userName = userName;
        this.movieName = movieName;
        this.language = language;
        this.tickets = tickets;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getTodayTime() {
        return todayTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getLanguage() {
        return language;
    }

    public int getTickets() {
        return tickets;
    }

    public int getAmount() {
        return amount;
    }
}
