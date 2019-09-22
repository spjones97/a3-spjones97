package a3;

public class VehicleImpl implements Vehicle {

    private String make;
    private String model;
    private String plate;
    private Position position;
    private int mileage;

    public VehicleImpl(String make, String model, String plate, Position position) {
        if (make == null || model == null || plate == null || position == null) {
        	throw new RuntimeException("");
        }
    	this.make = make;
        this.model = model;
        this.plate = plate;
        this.position = position;
        this.mileage = 0;
    }

    @Override
    public String getMake() {
        if (this.make == null) {
            throw new RuntimeException("Need to specify the make");
        } else {
            return this.make;
        }
    }

    @Override
    public String getModel() {
        if (this.model == null) {
            throw new RuntimeException("Need to specify the model");
        } else {
            return this.model;
        }
    }

    @Override
    public String getPlate() {
        if (this.plate == null) {
            throw new RuntimeException("Need to specify plate");
        } else {
            return this.plate;
        }
    }

    @Override
    public int getMileage() {
        return this.mileage;
    }

    @Override
    public Position getPosition() {
        if (this.position == null) {
            throw new RuntimeException("Need to specify position");
        } else {
            return this.position;
        }
    }

    @Override
    public void moveToPosition(Position p) {
    	if (p == null) {
    		throw new RuntimeException("");
    	}
        int distance = this.position.getManhattanDistanceTo(p);
        this.mileage += distance;
        this.position = p;
    }
}
