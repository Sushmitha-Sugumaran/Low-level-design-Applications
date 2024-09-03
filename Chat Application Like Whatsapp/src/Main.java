import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Main class to run the chat application
public class Main {
    private static ChatSystem chatSystem = new ChatSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();

        // Main application loop
        while (true) {
            if (chatSystem.getCurrentUser() == null) {
                loginMenu();
            } else {
                mainMenu();
            }
        }
    }

    // Method to initialize some users for testing
    private static void initializeUsers() {
        chatSystem.registerUser(100, "Ram", "Ram12#4");
        chatSystem.registerUser(101, "Mani", "Mani#134");
        chatSystem.registerUser(102, "Suresh", "Suresh#01");
        chatSystem.registerUser(103, "Geetha", "Geetha#329");
        chatSystem.registerUser(104, "Vignesh", "Vignesh#213");
    }

    // Method to display login menu and handle user authentication
    private static void loginMenu() {
        System.out.println("\n--- Login Menu ---");
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (chatSystem.login(userName, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    // Method to display main menu and handle user actions
    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Send Message");
            System.out.println("2. View Chat History");
            System.out.println("3. Create Group");
            System.out.println("4. View Group Chats");
            System.out.println("5. Send Group Message");
            System.out.println("6. Delete Message");
            System.out.println("7. View All Chats");
            System.out.println("8. Add Users to Group");
            System.out.println("9. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sendMessageMenu();
                    break;
                case 2:
                    viewChatHistoryMenu();
                    break;
                case 3:
                    createGroupMenu();
                    break;
                case 4:
                    viewGroupChatsMenu();
                    break;
                case 5:
                    sendGroupMessageMenu();
                    break;
                case 6:
                    deleteMessageMenu();
                    break;
                case 7:
                    chatSystem.displayAllChats();
                    break;
                case 8:
                    addUsersToGroupMenu();
                    break;
                case 9:
                    chatSystem.logout();
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to handle sending a message
    private static void sendMessageMenu() {
        System.out.print("Enter receiver's user ID: ");
        int receiverId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        chatSystem.sendMessage(receiverId, message);
    }

    private static void viewChatHistoryMenu() {
        System.out.print("Enter user ID to view chat history: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        chatSystem.displayChatHistory(userId);
    }

    private static void deleteMessageMenu() {
        // Step 1: Ask for the user whose message you want to delete
        System.out.print("Enter user ID to delete message from: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Step 2: Display chat history with the selected user
        chatSystem.displayChatHistory(userId);

        // Step 3: Ask for the message ID to delete
        System.out.print("Enter message ID to delete: ");
        int messageId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Step 4: Attempt to delete the message
        chatSystem.deleteMessage(messageId);
    }


    // Method to handle adding users to a group
    private static void addUsersToGroupMenu() {
        chatSystem.displayGroupChats();
        System.out.print("Enter the group ID to add users to: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter user ID to add to the group: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        chatSystem.addUserToGroup(groupId, userId);
    }

    private static void createGroupMenu() {
        System.out.print("Enter group name: ");
        String groupName = scanner.nextLine();
        chatSystem.createGroup(groupName);
    }

    private static void viewGroupChatsMenu() {
        chatSystem.displayGroupChats();
        System.out.print("Enter group ID to view chat history (0 to cancel): ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (groupId != 0) {
            chatSystem.displayGroupChatHistory(groupId);
        }
    }

    private static void sendGroupMessageMenu() {
        chatSystem.displayGroupChats();
        System.out.print("Enter group ID to send message: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        chatSystem.sendGroupMessage(groupId, message);
    }

}
