import java.util.ArrayList;
import java.util.List;

// Group class represents a chat group
class Group {
    private int groupId;
    private String groupName;
    private List<User> members;
    private List<Message> messages;

    // Constructor initializes a new group with empty lists for members and messages
    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Method to add a new member to the group
    public void addMember(User user) {
        members.add(user);
    }

    // Method to add a new message to the group chat
    public void sendMessage(Message message) {
        messages.add(message);
    }

    // Getters for group properties
    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
