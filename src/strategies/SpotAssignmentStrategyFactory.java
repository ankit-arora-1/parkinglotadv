package strategies;

import models.SpotAssignmentStrategyType;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmnetStrategy getSpotForType(
            SpotAssignmentStrategyType spotAssignmentStrategyType) {
        return new RandomSpotAssignmentStrategy();
    }
}
