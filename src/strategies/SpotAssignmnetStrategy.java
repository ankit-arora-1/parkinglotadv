package strategies;

import models.Gate;
import models.ParkingSpot;
import models.VehicleType;

public interface SpotAssignmnetStrategy {
    public ParkingSpot getSpot(Gate gate, VehicleType vehicleType);
}
