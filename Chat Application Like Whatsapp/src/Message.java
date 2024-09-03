import java.time.LocalDateTime;

// Message class represents a single message in the chat system
class Message {
    private int messageId;
    private User sender;
    private User receiver;
    private String content;
    private LocalDateTime timestamp;
    private boolean isDeleted;

    // Constructor creates a new message with current timestamp
    public Message(int messageId, User sender, User receiver, String content) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isDeleted = false;
    }

    // Method to mark a message as deleted
    public void delete() {
        this.isDeleted = true;
        this.content = "deleted";
    }

    // Getters for message properties
    public int getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return isDeleted ? "deleted" : content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
