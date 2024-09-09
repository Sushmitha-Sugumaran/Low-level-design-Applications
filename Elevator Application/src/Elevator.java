class Elevator {
    private int id;
    private int currentFloor;
    private ElevatorState state;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.state = ElevatorState.IDLE;
    }

    //HELP TO MOVE FROM CURRENT POS TO DEST
    public void move(int destinationFloor) {
        if (destinationFloor == currentFloor) {
            System.out.println("Elevator " + id + " is already at floor " + currentFloor);
            return;
        }

        setState(destinationFloor > currentFloor ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN);

        while (currentFloor != destinationFloor) {
            currentFloor += (destinationFloor > currentFloor) ? 1 : -1;
            System.out.println("Elevator " + id + " is at floor " + currentFloor);
        }

        setState(ElevatorState.IDLE);
    }

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }
}
