import java.util.*;

public class Library {
    private static int user_id = 53, index = 0;
    private static ArrayList<book> book_list = new ArrayList<book>();
    private static ArrayList<users> user_list = new ArrayList<users>();
    private static ArrayList<borrow> bor_book = new ArrayList<borrow>();
    private static ArrayList<return_fine> return_list = new ArrayList<return_fine>();
    private static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        books();
        user_lib();
        library_home();
    }

    private static void books() {
        book_list.add(new book("The Greatness of Guide", "Robin Sharma", "General", 300, 100, 0, 10));
        book_list.add(new book("The Adventures of Tow Sawyer", "Mark Twain", "Novel", 200, 101, 0, 10));
        book_list.add(new book("My Earl Life", "C.S.Lewis", "Novel", 150, 102, 0, 10));
        book_list.add(new book("Daily Inspiration", "Robin Sharma", "Motivation", 270, 103, 0, 10));
        book_list.add(new book("Savrola", "Winston Churchill", "Political", 300, 104, 0, 10));
    }

    private static void user_lib() {
        user_list.add(new users("Niveth", "1234", 1500, 50));
        user_list.add(new users("Rahul", "1111", 1500, 51));
        user_list.add(new users("Sanjay", "2222", 1500, 52));
    }

    private static void library_home() {
        System.out.println("\t-----------Welcome to EC Library Management System------------");
        System.out.println("1.Admin LogIn");
        System.out.println("2.User LogIn");
        System.out.println("3.Exit");
        System.out.print("Enter your Choice :");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                admin();
                break;
            case 2:
                user();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Enter valid Input... ");
                library_home();
                break;
        }
    }

    private static void admin() {
        System.out.println("--------Welcome Admin--------");
        System.out.print("Enter Admin ID :");
        int admin_id = scan.nextInt();
        System.out.print("Enter Admin Password :");
        int admin_pass = scan.nextInt();
        if (admin_id == 100 && admin_pass == 1234) {
            orgAdmin();
        } else {
            System.out.println("Please Enter valid Admin Credentials....");
            admin();
        }
    }

    private static void orgAdmin() {

        System.out.println("\t\t------- Welcome Admin Amrith --------");
        System.out.println("1.Add Book");
        System.out.println("2.Book Details");
        System.out.println("3.Modify Book");
        System.out.println("4.Delete Book");
        System.out.println("5.Search Book");
        System.out.println("6.User Details");
        System.out.println("7.Borrow Details");
        System.out.println("8.Return and Fine");
        System.out.println("9.Exit");
        System.out.print("Enter Your Choice :");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                bookDetails();
                break;
            case 3:
                modifyBook();
                break;
            case 4:
                deleteBook();
                break;
            case 5:
                searchBook();
                break;
            case 6:
                userDetails();
                break;
            case 7:
                borrow_details();
                break;
            case 8:
                return_fine();
                break;
            case 9:
                library_home();
                break;
            default:
                System.out.println("Please Enter valid Input...");
                orgAdmin();
        }
    }

    // ----------------------- add book -------------------------//

    private static void addBook() {
        System.out.println("--------- Add Book ---------");
        System.out.print("Enter the Book Title :");
        String b_title = scan.next();
        System.out.print("Enter the Book Author :");
        String b_auth = scan.next();
        System.out.print("Enter the Book Type :");
        String b_type = scan.next();
        System.out.print("Enter the Book Price :");
        double b_price = scan.nextDouble();
        System.out.print("Enter the Book ISBN :");
        int b_id = scan.nextInt();
        System.out.print("Enter the Quantity of the book :");
        int quant = scan.nextInt();
        book_list.add(new book(b_title, b_auth, b_type, b_price, b_id, 0, quant));
        System.out.println("Book added Successfully....:)");
        orgAdmin();
    }

    // ------------------- book list ---------------------//
    private static void bookDetails() {
        System.out.println("\t\t------------Available Books in the Library----------");
        System.out.println();
        System.out.printf("  %-25s ||  %-15s||  %-20s||  %-15s||  %-15s||  %-15s||\n", "Book Title", "Book Author",
                "Book Type", "ISBN", "Book Price", "Book In/Out");
        System.out.println();

        for (int i = 0; i < book_list.size(); i++) {
            int cnt = book_list.get(i).b_count;
            String in_out = "";
            if (cnt > 0) {
                in_out = "Out";
            } else {
                in_out = "In";
            }
            System.out.printf("%-25s ||  %-15s||  %-20s||  %-15s||  %-15s||  %-15s||\n",
                    book_list.get(i).b_title,
                    book_list.get(i).b_auth,
                    book_list.get(i).b_type,
                    book_list.get(i).b_id,
                    book_list.get(i).b_price,
                    in_out);
        }
        int max = 0;
        int min = 10;
        for (int x = 0; x < book_list.size(); x++) {
            if (book_list.get(x).stock > max) {
                max = book_list.get(x).stock;
            } else if (book_list.get(x).stock < min) {
                min = book_list.get(x).stock;
            }
        }
        System.out.println("Most frequently bought book : ");
        System.out.println();
        for (int y = 0; y < book_list.size(); y++) {
            if (book_list.get(y).stock == min) {
                System.out.println(book_list.get(y).b_id + "    " + book_list.get(y).b_title + "    "
                        + book_list.get(y).b_auth);
            }
        }
        System.out.println();
        System.out.println("Least frequently bought book : ");
        System.out.println();
        for (int z = 0; z < book_list.size(); z++) {
            if (book_list.get(z).stock == max) {
                System.out.println(book_list.get(z).b_id + "    " + book_list.get(z).b_title + "    "
                        + book_list.get(z).b_auth);
            }
        }
        System.out.println();
        orgAdmin();
    }

    // ---------------------------------- return fine ----------------------- //
    private static void return_fine() {
        System.out.println("--------------------Return and Fine Details---------------------");
        int flag = 0;
        System.out.printf("  %-15s||  %-15s||  %-15s||  %-15s||\n", "User ID", "ISBN", "Days Taken", "Fine Amount");
        for (int i = 0; i < return_list.size(); i++) {
            System.out.printf("  %-15s||  %-15s||  %-15s||  %-15s||\n", return_list.get(i).user_id,
                    return_list.get(i).book_id,
                    return_list.get(i).days, return_list.get(i).fine_amt);
            flag++;
        }
        if (flag == 0) {
            System.out.println("No Returns so far.... ");
        }
        orgAdmin();
    }

    // ----------------------------- borrow details ------------------------------//
    private static void borrow_details() {
        System.out.printf("  %-15s||  %-26s ||  %-15s||  %-20s||  %-15s||  %-15s||\n", "User ID", "Book Title",
                "Book Author", "Book Type", "ISBN", "Book Price");
        int bor_count = 0;
        for (int i = 0; i < bor_book.size(); i++) {
            System.out.printf("  %-15s||  %-26s ||  %-15s||  %-20s||  %-15s||  %-15s||\n", bor_book.get(i).u_id,
                    bor_book.get(i).b_title, bor_book.get(i).b_auth, bor_book.get(i).b_type, bor_book.get(i).b_id,
                    bor_book.get(i).b_price);
            bor_count++;
        }
        if (bor_count == 0) {
            System.out.println("No Borrow left....");
        }
        orgAdmin();
    }


    // ----------------------------modify book-----------------------//
    private static void modifyBook() {
        System.out.print("Enter the ISBN to modify Book :");
        int mod_count = 0;
        int temp_isbn = scan.nextInt();
        for (int i = 0; i < book_list.size(); i++) {
            if (book_list.get(i).b_id == temp_isbn) {
                mod_count++;
                System.out.println("1.Modify Book Title");
                System.out.println("2.Modify Book Author");
                System.out.println("3.Modify Book Type");
                System.out.println("4.Modify Book ISBN");
                System.out.println("5.Modify Book Price");
                int mod_book = scan.nextInt();
                if (mod_book == 1) {
                    mod_tit(i);
                }
                if (mod_book == 2) {
                    mod_auth(i);
                }
                if (mod_book == 3) {
                    mod_type(i);
                }
                if (mod_book == 4) {
                    mod_isbn(i);
                }
                if (mod_book == 5) {
                    mod_price(i);
                }
            }
        }
        if (mod_count == 0) {
            System.out.println("Incorrect ISBN or Book not Found....");
        }
        orgAdmin();

    }

    private static void mod_tit(int i) {
        System.out.print("Enter new book Title :");
        String book_tit = scan.next();
        book_list.get(i).b_title = book_tit;
        System.out.println("Book Title Changed Successfully....");
    }

    private static void mod_auth(int i) {
        System.out.print("Enter new book Title :");
        String book_auth = scan.next();
        book_list.get(i).b_title = book_auth;
        System.out.println("Book Author Changed Successfully....");
    }

    private static void mod_type(int i) {
        System.out.print("Enter new book Title :");
        String book_type = scan.next();
        book_list.get(i).b_title = book_type;
        System.out.println("Book Type Changed Successfully....");
    }

    private static void mod_isbn(int i) {
        System.out.print("Enter new book Title :");
        String book_isbn = scan.next();
        book_list.get(i).b_title = book_isbn;
        System.out.println("Book ISBN Changed Successfully....");
    }

    private static void mod_price(int i) {
        System.out.print("Enter new book Title :");
        String book_price = scan.next();
        book_list.get(i).b_title = book_price;
        System.out.println("Book Title Changed Successfully....");
    }

    // -----------------------delete book----------------------//
    private static void deleteBook() {
        System.out.println("Enter Book ISBN :");
        int n = scan.nextInt();
        int flag = 0;
        for (int i = 0; i < book_list.size(); i++) {
            if (book_list.get(i).b_id == n) {
                System.out.println("Book " + book_list.get(i).b_title + " Deleted Successfully...");
                book_list.remove(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("No Book is presented for the ISBN");
        }
        orgAdmin();
    }

    private static void searchBook() {
        System.out.println("Enter the Book Title :");
        String sc = scan.next();
        int flag = 0;
        for (int i = 0; i < book_list.size(); i++) {
            if (book_list.get(i).b_title.equals(sc)) {
                int cnt = book_list.get(i).b_count;
                String in_out = "";
                if (cnt > 0) {
                    in_out = "Out";
                } else {
                    in_out = "In";
                }
                System.out.printf("  %-25s ||  %-15s||  %-20s||  %-15s||  %-15s||  %-15s||\n", "Book Title",
                        "Book Author", "Book Type", "ISBN", "Book Price", "Book IN/Out");
                System.out.printf("  %-25s ||  %-15s||  %-20s||  %-15s||  %-15s||  %-15s||\n", book_list.get(i).b_title,
                        book_list.get(i).b_auth, book_list.get(i).b_type, book_list.get(i).b_id,
                        book_list.get(i).b_price, in_out);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Invalid Book Title or Book is not Presented...");
        }
        orgAdmin();
    }

    // ---------------------------- User Details -------------------------//
    private static void userDetails() {
        System.out.println("------------ Library User Details -------------");
        System.out.printf("  %-15s ||  %-15s||  %-15s||  %-20s||\n", "User Id", "User Name", "User Password",
                "User Library Balance");
        int count = 0;
        for (int i = 0; i < user_list.size(); i++) {
            count++;
            System.out.printf("  %-15s ||  %-15s||  %-15s||  %-20s||\n", user_list.get(i).u_id, user_list.get(i).u_name,
                    "**", user_list.get(i).u_amt);
        }
        if (count == 0) {
            System.out.println("No user To be shown");
        }
        orgAdmin();
    }

    // ---------------------------------users-------------------------------//
    private static void user() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t------------Welcome Library Users-----------");
        System.out.println("1.New User");
        System.out.println("2.Existing User");
        System.out.println("3.Exit");
        System.out.print("Enter your choice :");
        int ch = scan.nextInt();
        switch (ch) {
            case 1:
                newUser();
                break;
            case 2:
                existUser();
                break;
            case 3:
                library_home();
                break;
            default:
                System.out.println("Enter valid Input");
        }

    }

    // --------------------------------- New User
    // -----------------------------------//
    private static void newUser() {
        System.out.print("Enter User Name:");
        String u_name = scan.next();
        System.out.print("Set User Password");
        String u_pass = scan.next();
        System.out.print("Set Initial Library Amount:");
        double u_amt = scan.nextDouble();
        user_list.add(new users(u_name, u_pass, u_amt, user_id));
        System.out.println("Your User Id is " + user_id + " Please Remember this for Login purposes");
        user_id++;
        System.out.println("User Created Successfully...Try to log In");
        user();
    }

    // ------------------------- existing user ------------------------//
    private static void existUser() {
        System.out.print("Enter User ID: ");
        int u_id = scan.nextInt();
        System.out.print("User Password: ");
        String u_pass = scan.next();
        if (check(user_list, u_id, u_pass)) {
            orgUser();
        } else {
            System.out.println("Invalid login Credentials Try again...");
        }
    }

    private static boolean check(ArrayList<users> user_list2, int u_id, String u_pass) {
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).u_id == u_id && user_list.get(i).u_pass.equals(u_pass)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void orgUser() {
        System.out.println("Welcome " + user_list.get(index).u_name + " to Library.....:)");
        System.out.println("1.Borrow Book");
        System.out.println("2.Borrow History");
        System.out.println("3.Return");
        System.out.println("4.Wallet");
        System.out.println("5.Exit");
        System.out.print("Enter your choice :");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                borrow_book();
                break;
            case 2:
                borrow_history();
                break;
            case 3:
                book_return();
                break;
            case 4:
                wallet();
                break;
            case 5:
                user();
                break;
            default:
                System.out.println("Enter valid Input.....");
                orgUser();
                break;
        }
    }

    private static void borrow_book() {
        System.out.println("\t\t------------Available Books in the Library----------");
        System.out.println();
        System.out.printf("  %-23s ||  %-15s||  %-15s||  %-15s||  %-15s||  %-15s||\n", "Book Title", "Book Author",
                "Book Type", "ISBN", "Book Price", "Book Quantity");
        System.out.println();
        for (int i = 0; i < book_list.size(); i++) {
            System.out.printf("%-23s ||  %-15s||  %-15s||  %-15s||  %-15s||  %-15s||\n",
                    book_list.get(i).b_title,
                    book_list.get(i).b_auth,
                    book_list.get(i).b_type,
                    book_list.get(i).b_id,
                    book_list.get(i).b_price,
                    book_list.get(i).stock);
        }
        System.out.print("Enter the number of bookings :");
        int bo_no = scan.nextInt();
        if (bo_no > 3) {
            System.out.println("One time borrow limit is 3 ");
        } else {
            for (int j = 0; j < bo_no; j++) {
                boolean flag = true;
                int book_exit = 0;
                if (user_list.get(index).u_amt > 500) {
                    System.out.print("Enter the book ISBN to place your order :");
                    int n = scan.nextInt();
                    for (int i = 0; i < book_list.size(); i++) {
                        if (book_list.get(i).b_id == n && book_list.get(i).stock > 0) {
                            for (int k = 0; k < bor_book.size(); k++) {
                                if (bor_book.get(k).u_id == user_list.get(index).u_id && bor_book.get(k).b_id == n) {
                                    book_exit++;
                                }
                            }
                            if (book_exit != 0) {
                                System.out.println("Book already borrowed....");
                                break;
                            } else {
                                bor_book.add(new borrow(book_list.get(i).b_title, book_list.get(i).b_auth,
                                        book_list.get(i).b_type,
                                        book_list.get(i).b_price,
                                        book_list.get(i).b_id, book_list.get(i).b_count, user_list.get(index).u_id));
                                book_list.get(i).b_count += 1;
                                book_list.get(i).stock--;
                                System.out.println(
                                        "Book Borrowed successfully!!! You must return the book within 15 days otherwise you will be fined...");
                                flag = false;
                            }
                        }
                    }
                    if (flag == true) {
                        System.out.println("Book not available or Out of stock..");
                    }
                } else {
                    System.out.println("Your Available Library balance is too low.....");
                }
            }
        }
        orgUser();
    }

    private static void borrow_history() {
        System.out.println("------------- Your Borrow History ------------- ");
        System.out.println();
        System.out.printf("  %-26s ||  %-15s||  %-20s||  %-15s||  %-15s||  \n", "Book Title", "Book Author",
                "Book Type", "ISBN", "Book Price");
        int f = 0;
        for (int i = 0; i < bor_book.size(); i++) {
            if (user_list.get(index).u_id == bor_book.get(i).u_id) {
                System.out.printf("  %-26s ||  %-15s||  %-20s||  %-15s||  %-15s||\n", bor_book.get(i).b_title,
                        bor_book.get(i).b_auth, bor_book.get(i).b_type, bor_book.get(i).b_id, bor_book.get(i).b_price,
                        bor_book.get(i).u_id);
                f++;
            }
        }
        if (f == 0) {
            System.out.println("No Borrow History left....");
        }
        orgUser();
    }

    private static void book_return() {
        System.out.println("---------- Book Return Section -------");
        System.out.println("1.Book Return");
        System.out.println("2.Book Miss");
        System.out.print("Enter Your Choice :");
        int ch = scan.nextInt();
        if (ch == 1) {
            book_ret();
        } else if (ch == 2) {
            book_miss();
        } else {
            System.out.println("Please enter Valid input....");
            book_return();
        }
        orgUser();

    }

    // -------------------- book-return -------------------------//
    private static void book_ret() {
        System.out.print("Enter the Book ISBN number:");
        int temp_isbn = scan.nextInt();
        for (int i = 0; i < bor_book.size(); i++) {
            if (user_list.get(index).u_id == bor_book.get(i).u_id && bor_book.get(i).b_id == temp_isbn) {
                System.out.print("Enter the Duration taken for the Book since Borrow :");
                int days = scan.nextInt();
                if (days <= 15) {
                    bor_book.remove(i);
                    System.out.println("Book Returned Successfully....:)");
                    book_list.get(i).stock++;
                    book_list.get(i).b_count = 0;
                    String str = "No Fine";
                    String duration = "" + days;
                    return_list.add(new return_fine(user_list.get(index).u_id, temp_isbn, str, duration));
                    bor_book.remove(i);
                } else if (days >= 16) {
                    String str = "";
                    String duration = "" + days;
                    int temp = days - 15;
                    double temp_amt = temp * 15;
                    str = "" + temp_amt;
                    System.out.println("You have Fined Amount of " + temp_amt + " due to exceed of returning date...");
                    user_list.get(index).u_amt -= temp_amt;
                    System.out.println("Book Returned Successfully....:)");
                    book_list.get(i).stock++;
                    book_list.get(i).b_count = 0;
                    return_list.add(new return_fine(user_list.get(index).u_id, temp_isbn, str, duration));
                    bor_book.remove(i);
                }
            } else {
                System.out.println("Entered ISBN is not in your Borrowed History please Try again..");
            }
        }
        orgUser();
    }

    // -------------------- book Miss -------------------------//
    private static void book_miss() {
        System.out.print("Enter the Book ISBN number:");
        int temp_isbn = scan.nextInt();
        for (int i = 0; i < bor_book.size(); i++) {
            if (user_list.get(index).u_id == bor_book.get(i).u_id && bor_book.get(i).b_id == temp_isbn) {
                double temp_amt = bor_book.get(i).b_price / 2;
                System.out.println("You have Fined Amount of " + temp_amt + " due to Lose the Book...");
                user_list.get(i).u_amt -= temp_amt;
                book_list.get(i).b_count = 0;
                String str = "" + temp_amt;
                String duration = "Missed";
                return_list.add(new return_fine(user_list.get(index).u_id, temp_isbn, str, duration));
                bor_book.remove(i);
            } else {
                System.out.println("Entered ISBN is not in your Borrow History Please Try again...");
            }
        }
        orgUser();
    }

    private static void wallet() {
        System.out.println("Your Current Library balance is : " + user_list.get(index).u_amt);
        System.out.println("Press Enter to continue");
        orgUser();
    }

}

