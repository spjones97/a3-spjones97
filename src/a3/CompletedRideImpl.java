package a3;

public class CompletedRideImpl implements CompletedRide {

    private RideRequest request;
    private Driver driver;
    private int waitTime;
    private int totalTime;
    private double cost;
    private double price;
    private double profit;

    public CompletedRideImpl(RideRequest request, Driver driver) {
        if (request == null || driver == null) {
      
        	throw new RuntimeException("Completed Ride FUCK YOU");
        }
    	this.request = request;
        this.driver = driver;
    }

    @Override
    public RideRequest getRequest() {
        if (this.request == null) {
            throw new RuntimeException("Need to specify ride request");
        } else {
            return this.request;
        }
    }

    @Override
    public Driver getDriver() {
        if (this.driver == null) {
            throw new RuntimeException("Need to specify driver");
        } else {
            return this.driver;
        }
    }

    @Override
    public int getWaitTime() {
        Position driverPosition = this.driver.getVehicle().getPosition();
        Position clientPosition = this.request.getClientPosition();
        // distance = time?
        return driverPosition.getManhattanDistanceTo(clientPosition);
    }

    @Override
    public int getTotalTime() {
        // distance = time?
        return this.request.getRideTime() + this.getWaitTime();
    }

    @Override
    public double getCost() {
        double cost = ((0.5) * this.request.getRideTime()) + ((0.1) * this.getWaitTime());
        return cost;
    }

    @Override
    public double getPrice() {
        if (this.getWaitTime() < 25) {
            return ((2.5) * (this.request.getRideTime()) );
        } else if (this.getWaitTime() >= 25 && this.getWaitTime() <= 49) {
            return ((2.0) * this.request.getRideTime());
        } else if (this.getWaitTime() >= 50 && this.getWaitTime() <= 99) {
            return ((1.0) * this.request.getRideTime());
        } else if (this.getWaitTime() >= 100) {
            return ((0.5) * this.request.getRideTime());
        }
        return 0;
    }

    @Override
    public double getProfit() {
        return this.getPrice() - this.getCost();
    }
}
