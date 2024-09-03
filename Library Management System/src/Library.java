import java.util.*;

public class Library {
    static ArrayList<Books> book_list = new ArrayList<Books>();
    static ArrayList<Users> user_list = new ArrayList<Users>();
    static ArrayList<Borrow> bor_list = new ArrayList<Borrow>();
    static ArrayList<Return> return_list = new ArrayList<Return>();
    static Scanner scan = new Scanner(System.in);
    static int ids=0;

    public static void main(String[] args) {
        books();
        users();
        library_home();
    }

    private static void books() {
        book_list.add(new Books("The Greatness of Guide", "Robin Sharma", "General", 300, 100, 0, 10));
        book_list.add(new Books("The Adventures of Tow Sawyer", "Mark Twain", "Novel", 200, 101, 0, 10));
        book_list.add(new Books("My Earl Life", "C.S.Lewis", "Novel", 150, 102, 0, 10));
        book_list.add(new Books("Daily Inspiration", "Robin Sharma", "Motivation", 270, 103, 0, 10));
        book_list.add(new Books("Savrola", "Winston Churchill", "Political", 300, 104, 0, 10));
    }

    private static void users() {
        user_list.add(new Users("Niveth", "1234", 1500, 50));
        user_list.add(new Users("Rahul", "1111", 1500, 51));
        user_list.add(new Users("Sanjay", "2222", 1500, 52));
    }

    private static void library_home() {
        System.out.println("-----------------welcome to ec library-------------------");
        System.out.println("1.user login");
        System.out.println("2.admin login");
        System.out.println("3.exit");
        System.out.println("enter your choice");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                user();
                break;
            case 2:
                // admin();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("enter valid choice...");
                library_home();
                break;
        }
    }

    private static void user() {
        System.out.println("welcome user...");
        System.out.println("1.new user");
        System.out.println("2.existing user");
        System.out.println("3.exit");
        System.out.println("enter your choice");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                newUser();
                break;
            case 2:
                login();
                break;
            case 3:
                library_home();
                break;
            default:
                System.out.println("enter valid choice...");
                user();
                break;
        }
    }

    private static void login() {
        System.out.println("enter your userid and password..");
        int id= scan.nextInt();
        String pass= scan.next();
        boolean found=false;
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).user_id==id && user_list.get(i).user_password.equals(pass)){
                found=true;
                orgUser();
            }
        }
        if (!found){
            System.out.println("enter vaild id/password..");
            login();
        }
    }

    private static void orgUser() {

    }

    private static void newUser() {
        System.out.println("enter username,password to create account...");
        String u_name= scan.next();
        String u_password= scan.next();
        System.out.println("enter amt ....");
        int u_amt= scan.nextInt();
        user_list.add(new Users(u_name,u_password,ids,u_amt));
        ids++;
        System.out.println("try to remind this password"+u_password);
        System.out.println("now try to login");
        login();
    }
}
