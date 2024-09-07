public class Events {
    static int id = 1;
    int eventsId;
    String showSchedule;
    String showTime;
    String language;
    String eventName;
    String genre;
    int seatCount;
    int perTicketPrice;

    public Events(String showSchedule, String showTime, String language, String eventName, String genre, int seatCount, int perTicketPrice) {
        this.eventsId = id++;
        this.showSchedule = showSchedule;
        this.showTime = showTime;
        this.language = language;
        this.eventName = eventName;
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

    public String getEventName() {
        return eventName;
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

    public int getEventsId() {
        return eventsId;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
