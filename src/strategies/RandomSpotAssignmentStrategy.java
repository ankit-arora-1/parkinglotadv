package strategies;

import models.Gate;
import models.ParkingLot;
import models.ParkingSpot;
import models.VehicleType;
import repositories.ParkingLotRepository;

public class RandomSpotAssignmentStrategy implements SpotAssignmnetStrategy {
    private ParkingLotRepository parkingLotRepository;
    @Override
    public ParkingSpot getSpot(Gate gate, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLotFromGate(gate);
        // complete the code
        return null;
    }
}
