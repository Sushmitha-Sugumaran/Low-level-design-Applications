import java.util.ArrayList;
import java.util.List;

// User class represents a user in the chat system
class User {
    private int userId;
    private String userName;
    private String password;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
    private List<Group> groups;

    // Constructor initializes a new user with empty lists for messages and groups
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    // Getters for user properties
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
