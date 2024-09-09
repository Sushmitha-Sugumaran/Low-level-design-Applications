import java.util.List;
import java.util.Map;

public class CalculatePrice {

    public static int moviePay(List<Movies> moviesList, Map<Integer, Movies> moviesMap, int movieInd, List<User> userList, int userInd, boolean found, int tickets) {
        int amount = moviesList.get(movieInd).getPerTicketPrice()*tickets;
        //System.out.println(amount);
        if (amount>userList.get(userInd).getWallet()){
            found = false;
            amount = 0;
        }
        return amount;
    }

    public static int eventPay(List<Events> eventsList, Map<Integer, Events> eventsMap, int eventInd, List<User> userList, int userInd, boolean found, int tickets) {
        int amount = eventsList.get(eventInd).getPerTicketPrice()*tickets;
        if (amount>userList.get(userInd).getWallet()){
            found = false;
            amount = 0;
        }
        return amount;
    }
}
