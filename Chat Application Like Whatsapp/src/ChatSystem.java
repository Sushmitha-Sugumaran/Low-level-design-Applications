import java.time.format.DateTimeFormatter;
import java.util.*;

// ChatSystem class manages the entire chat application
class ChatSystem {
    private Map<Integer, User> usersById;
    private Map<String, User> usersByUsername;
    private Map<Integer, Group> groups;
    private User currentUser;
    private int nextMessageId = 1;
    private int nextGroupId = 1;

    // Constructor initializes empty maps for users and groups
    public ChatSystem() {
        this.usersById = new HashMap<>();
        this.usersByUsername = new HashMap<>();
        this.groups = new HashMap<>();
    }

    // Method to register a new user
    public void registerUser(int userId, String userName, String password) {
        User newUser = new User(userId, userName, password);
        usersById.put(userId, newUser);
        usersByUsername.put(userName, newUser);
    }

    // Method to authenticate and log in a user
    public boolean login(String userName, String password) {
        User user = usersByUsername.get(userName);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    // Method to log out the current user
    public void logout() {
        currentUser = null;
    }

    // Method to send a message to another user
    public void sendMessage(int receiverId, String content) {
        User receiver = usersById.get(receiverId);
        if (receiver != null) {
            Message message = new Message(nextMessageId++, currentUser, receiver, content);
            currentUser.getSentMessages().add(message);
            receiver.getReceivedMessages().add(message);
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Receiver not found.");
        }
    }

    // Method to get chat history with another user
    public List<Message> getChatHistory(int userId) {
        List<Message> chatHistory = new ArrayList<>();
        User otherUser = usersById.get(userId);
        if (otherUser != null) {
            chatHistory.addAll(currentUser.getSentMessages().stream()
                    .filter(m -> m.getReceiver() != null && m.getReceiver().equals(otherUser))
                    .toList());
            chatHistory.addAll(currentUser.getReceivedMessages().stream()
                    .filter(m -> m.getSender().equals(otherUser))
                    .toList());
            chatHistory.sort(Comparator.comparing(Message::getTimestamp).reversed());
        }
        return chatHistory;
    }


    // Method to send messages to multiple users
    public void sendMultipleMessages(List<Integer> receiverIds, String content) {
        for (int receiverId : receiverIds) {
            sendMessage(receiverId, content);
        }
    }

    // Method to create a new group
    public void createGroup(String groupName) {
        Group newGroup = new Group(nextGroupId++, groupName);
        newGroup.addMember(currentUser);
        groups.put(newGroup.getGroupId(), newGroup);
        currentUser.getGroups().add(newGroup);
        System.out.println("Group created successfully: " + groupName);
    }


    // Method to display chat history with another user
    public void displayChatHistory(int userId) {
        List<Message> chatHistory = getChatHistory(userId);
        User otherUser = usersById.get(userId);
        if (otherUser == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Chat history with " + otherUser.getUserName() + ":");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Message message : chatHistory) {
            String senderName = message.getSender().equals(currentUser) ? "You" : message.getSender().getUserName();
            System.out.println("[" + message.getMessageId() + "] " +
                    "[" + message.getTimestamp().format(formatter) + "] " +
                    senderName + ": " + message.getContent());
        }
    }

    // Method to send a message to a group
    public void sendGroupMessage(int groupId, String content) {
        Group group = groups.get(groupId);
        if (group != null && group.getMembers().contains(currentUser)) {
            Message message = new Message(nextMessageId++, currentUser, null, content);
            group.sendMessage(message);
            System.out.println("Group message sent successfully.");
        } else {
            System.out.println("Group not found or you're not a member.");
        }
    }

    // Method to delete a message
    public void deleteMessage(int messageId) {
        Message message = findMessage(messageId);
        if (message != null && message.getSender().equals(currentUser) && !message.isDeleted()) {
            message.delete();
            System.out.println("Message deleted successfully.");
        } else {
            System.out.println("Message not found or cannot be deleted.");
        }
    }

    // Helper method to find a message by its ID
    private Message findMessage(int messageId) {
        for (Message message : currentUser.getSentMessages()) {
            if (message.getMessageId() == messageId) {
                return message;
            }
        }
        for (Group group : currentUser.getGroups()) {
            for (Message message : group.getMessages()) {
                if (message.getMessageId() == messageId) {
                    return message;
                }
            }
        }
        return null;
    }

    // Method to display all groups the current user is a member of
    public void displayGroupChats() {
        System.out.println("Your Groups:");
        for (Group group : currentUser.getGroups()) {
            System.out.println(group.getGroupId() + ": " + group.getGroupName());
        }
    }

    // Method to display chat history of a group
    public void displayGroupChatHistory(int groupId) {
        Group group = groups.get(groupId);
        if (group != null && group.getMembers().contains(currentUser)) {
            System.out.println("Chat history for group: " + group.getGroupName());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Message message : group.getMessages()) {
                System.out.println("[" + message.getTimestamp().format(formatter) + "] " +
                        message.getSender().getUserName() + ": " + message.getContent());
            }
        } else {
            System.out.println("Group not found or you're not a member.");
        }
    }

    // Method to display all chats (individual and group) for the current user
    public void displayAllChats() {
        System.out.println("All your chats:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Display individual chats
        for (User user : usersById.values()) {
            if (!user.equals(currentUser)) {
                List<Message> chatHistory = getChatHistory(user.getUserId());
                if (!chatHistory.isEmpty()) {
                    System.out.println("\nChat with " + user.getUserName() + ":");
                    for (Message message : chatHistory) {
                        String senderName = message.getSender().equals(currentUser) ? "You" : message.getSender().getUserName();
                        System.out.println("[" + message.getTimestamp().format(formatter) + "] " +
                                senderName + ": " + message.getContent());
                    }
                }
            }
        }

        // Display group chats
        for (Group group : currentUser.getGroups()) {
            System.out.println("\nGroup: " + group.getGroupName());
            for (Message message : group.getMessages()) {
                System.out.println("[" + message.getTimestamp().format(formatter) + "] " +
                        message.getSender().getUserName() + ": " + message.getContent());
            }
        }
    }

    // Method to add a user to a group
    public void addUserToGroup(int groupId, int userId) {
        Group group = groups.get(groupId);
        User userToAdd = usersById.get(userId);

        if (group != null && userToAdd != null) {
            if (group.getMembers().contains(userToAdd)) {
                System.out.println("User is already a member of the group.");
            } else {
                group.addMember(userToAdd);
                userToAdd.getGroups().add(group);
                System.out.println("User " + userToAdd.getUserName() + " added to group " + group.getGroupName() + " successfully.");
            }
        } else {
            System.out.println("Group or user not found.");
        }
    }

    // Getter for current user
    public User getCurrentUser() {
        return currentUser;
    }
}
