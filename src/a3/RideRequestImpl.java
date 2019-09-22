package a3;

public class RideRequestImpl implements RideRequest {

    private Position clientPosition;
    private Position destination;
    private boolean isComplete;
    private int rideTime;

    public RideRequestImpl(Position clientPosition, Position destination) {
        if (clientPosition == null || destination == null) {
            throw new RuntimeException("Need to specify client position and/or destination");
        }
        this.clientPosition = clientPosition;
        this.destination = destination;
        this.rideTime = clientPosition.getManhattanDistanceTo(destination);
        this.isComplete = false;
    }

    @Override
    public Position getClientPosition() {
        if (this.clientPosition == null) {
            throw new RuntimeException("Need to specify client position");
        } else {
            return this.clientPosition;
        }
    }

    @Override
    public Position getDestination() {
        if (this.destination == null) {
            throw new RuntimeException("Need to specify destination");
        } else {
            return this.destination;
        }
    }

    @Override
    public boolean getIsComplete() {
        return this.isComplete;

    }

    @Override
    public CompletedRide complete(Driver driver) {
        if (driver == null) {
            throw new RuntimeException("Need to specify driver");
        }
        if (!this.getIsComplete()) {
            // Sets ride to complete
            this.isComplete = true;

            // Create a new CompletedRide Object
            CompletedRide rideObject = new CompletedRideImpl(this, driver);

            // Move the vehicle of the driver to the client
            driver.getVehicle().moveToPosition(this.getClientPosition());

            // Move the client to dest
            // this.clientPosition = this.getDestination();

            // Move the driver to dest
            driver.getVehicle().moveToPosition(this.getDestination());

            return rideObject;
        } else {
            // Return Ride Object
            CompletedRide rideObject = new CompletedRideImpl(this, driver);
            return rideObject;
        }

    }

    @Override
    public int getRideTime() {
        return this.rideTime;
    }
}
