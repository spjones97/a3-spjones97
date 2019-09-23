package a3;

import java.util.*;

/*
 * This dispatcher should choose the driver that is closest to the client's
 * position but without considering the last five drivers chosen (you can assume
 * that there will be more than five available drivers). In other words, if your
 * dispatcher first chooses DriverA, then the next four times that the
 * dispatcher is asked to choose a driver, DriverA should not be eligible for
 * being chosen.
 */

public class ShortButFairDispatcher implements Dispatcher {

    private Driver[] availableDrivers;
    private RideRequest rideRequest;

    public ShortButFairDispatcher() {

    }

    @Override
    public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
        // TODO Auto-generated method stub

        int[] distanceArr = new int[availableDrivers.length];
        int j = 0;

        // Create an array of distances
        for (Driver i : availableDrivers) {
            distanceArr[j] = i.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
            j++;
        }

        // Find driver with shortest distance and no wait time
        int minDistance = 10000;
        Driver minDriver = availableDrivers[0];
        for (int i = 0; i < availableDrivers.length; i++) {
            if (distanceArr[i] < minDistance && availableDrivers[i].getDriverWaitTime() <= 0) {
                minDriver = availableDrivers[i];
                minDistance = distanceArr[i];
            }
        }

        // Drop each driver wait time by 1
        for (int i = 0; i < availableDrivers.length; i++) {
            availableDrivers[i].round();
        }

        return minDriver;
    }
}