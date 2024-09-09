public class Main {
    public static void main(String[] args) {
        BOOKMYSHOWManagament management = BOOKMYSHOWManagament.getmanagement();
        management.initializeUser();
        MoviesSection.initializeMovies();
        EventsSection.initializeEvents();
        management.receptionSection();
    }
}