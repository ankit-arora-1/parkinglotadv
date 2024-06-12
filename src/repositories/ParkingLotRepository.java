package repositories;

import models.Gate;
import models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
    public ParkingLot getParkingLotFromGate(Gate gate) {
        for(ParkingLot parkingLot: parkingLotMap.values()) {
            if(parkingLot.getGates().contains(gate)) {
                return parkingLot;
            }
        }

        return null;
    }
}
