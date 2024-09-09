import java.util.Scanner;
public  class ElevatorSystem {
        private final Building building;
        private final Scanner scan;

        // CONSTRUCTOR TO ASSIGN FLOOR AND ELEVATORS
        public ElevatorSystem() {
            this.building = new Building(10, 2); // 10 floors, 2 elevators
            this.scan = new Scanner(System.in);
        }

        // METHOD TO START THE ELEVATOR
        public void start() {
            while (true) {
                System.out.println("1. ENTER TO USE ELEVATOR");
                System.out.println("2. EXIT");
                int choice = getValidInput(1, 2);

                if (choice == 1) {
                    handleElevatorRequest();
                } else {
                    break;
                }
            }
            System.out.println("Thank you for using the elevator system.");
        }

        // SET METHOD'S INSIDE WHILE WHICH RUNS UNTIL GET VALID INPUT
        private int getValidInput(int min, int max) {
        while (true) {
                int input = scan.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
        }
    }

        //HANDLE ELEVATOR CURRENT AND DEST POS AND MOVING FROM CURRENT TO DEST POSITION
        private void handleElevatorRequest() {
            System.out.println("Enter your current floor (1-10):");
            int currentFloor = getValidInput(1, 10);

            System.out.println("1. Go up");
            System.out.println("2. Go down");
            System.out.println("3. Exit");
            int direction = getValidInput(1, 3);

            if (direction == 3) {
                return;
            }

            if ((currentFloor == 1 && direction == 2) || (currentFloor == 10 && direction == 1)) {
                System.out.println("You are already on the " + (currentFloor == 1 ? "ground" : "top") + " floor. No elevator movement needed.");
                return;
            }

            int destinationFloor;

            // GO UP CONDITION
            if (direction == 1) {
                System.out.println("Select destination floor:");
                for (int i = currentFloor + 1; i <= 10; i++) {
                    System.out.println(i + ". Floor " + i);
                }
                destinationFloor = getValidInput(currentFloor + 1, 10);
            }

            // GO DOWN CONDITION
            else {
                System.out.println("Select destination floor:");
                for (int i = 1; i < currentFloor; i++) {
                    System.out.println(i + ". Floor " + i);
                }
                destinationFloor = getValidInput(1, currentFloor - 1);
            }

            Elevator assignedElevator = building.assignElevator(currentFloor, destinationFloor);
            System.out.println("Elevator " + assignedElevator.getId() + " is assigned to you.");

            if (assignedElevator.getCurrentFloor() != currentFloor) {
                System.out.println("Elevator " + assignedElevator.getId() + " is moving to your floor.");
                assignedElevator.move(currentFloor);
            }
            System.out.println("Elevator " + assignedElevator.getId() + " has arrived at floor " + currentFloor);

            assignedElevator.move(destinationFloor);
            System.out.println("Elevator " + assignedElevator.getId() + " has arrived at floor " + destinationFloor);
        }

        //MAIN METHOD
        public static void main(String[] args) {
            ElevatorSystem system = new ElevatorSystem();
            system.start();
        }
    }

