class Building {
    private int numFloors;
    private Elevator[] elevators;

    //ASSIGN BUIDING FLOORS AND CREATE ELEVATORS ON EACH FLOOR
    public Building(int numFloors, int numElevators) {
        this.numFloors = numFloors;
        this.elevators = new Elevator[numElevators];
        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator(i + 1);
        }
    }

    // IT ASSIGN CORRECT ELEVATOR (i.e).,NEAREST FLOOR ELEVATOR
    public Elevator assignElevator(int currentFloor, int destinationFloor) {
        Elevator bestElevator = elevators[0];
        int minCost = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int cost = calculateNearestElevator(elevator, currentFloor, destinationFloor);
            if (cost < minCost) {
                bestElevator = elevator;
                minCost = cost;
            }
        }

        return bestElevator;
    }

    // CALCULATE COST OF NEAREST ELEVATOR AND RETURN nearestOne TO ABOVE METHOD TO GET nearestOne WHERE WE GET NEAREST FLOOR ELEVATOR
    private int calculateNearestElevator(Elevator elevator, int currentFloor, int destinationFloor) {
        int nearestOne = Math.abs(elevator.getCurrentFloor() - currentFloor);

        if (elevator.getState() != ElevatorState.IDLE) {
            nearestOne += 2;
        }

        if ((elevator.getState() == ElevatorState.MOVING_UP && destinationFloor > elevator.getCurrentFloor()) ||
                (elevator.getState() == ElevatorState.MOVING_DOWN && destinationFloor < elevator.getCurrentFloor())) {
            nearestOne -= 1;
        }

        return nearestOne;
    }

}
