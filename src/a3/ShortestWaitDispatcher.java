package a3;

import java.util.Arrays;

/*
 * This dispatcher should choose the driver that is closest to the client's
 * position (i.e. will incur the shortest wait time.
 */

public class ShortestWaitDispatcher implements Dispatcher {

    private Driver[] availableDrivers;
    private RideRequest rideRequest;

    public ShortestWaitDispatcher() {

    }

    @Override
    public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
        // TODO Auto-generated method stub
        int[] distanceArr = new int[availableDrivers.length];
        int j = 0;

        // Create array of distances
        for (Driver i : availableDrivers) {
            distanceArr[j] = i.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
            j++;
        }

        // Find driver with minimum distance
        int min = 10000;
        Driver minDriver = availableDrivers[0];
        for (int i = 0; i < availableDrivers.length; i++) {
            if (distanceArr[i] < min) {
                min = distanceArr[i];
                minDriver = availableDrivers[i];
            }
        }

        return minDriver;
    }
}