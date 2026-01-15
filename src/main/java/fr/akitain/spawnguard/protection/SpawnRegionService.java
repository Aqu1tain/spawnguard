package fr.akitain.spawnguard.protection;

import com.hypixel.hytale.math.vector.Vector3i;
import fr.akitain.spawnguard.config.SpawnGuardConfig;

import javax.annotation.Nonnull;

public class SpawnRegionService {

    private final SpawnGuardConfig config;
    private final Vector3i spawnPosition;

    public SpawnRegionService(@Nonnull SpawnGuardConfig config, @Nonnull Vector3i spawnPosition) {
        this.config = config;
        this.spawnPosition = spawnPosition;
    }

    public boolean shouldBlockModification(@Nonnull Vector3i blockPos) {
        if (!config.enabled()) {
            return false;
        }

        return isInProtectedArea(blockPos);
    }

    @Nonnull
    public String getDenyMessage() {
        return config.getDenyMessage();
    }

    private boolean isInProtectedArea(@Nonnull Vector3i blockPos) {
        int radiusSquared = config.radius() * config.radius();
        int distanceSquared = calculateDistanceSquared(spawnPosition, blockPos);
        return distanceSquared <= radiusSquared;
    }

    private int calculateDistanceSquared(@Nonnull Vector3i pos1, @Nonnull Vector3i pos2) {
        int dx = pos1.getX() - pos2.getX();
        int dz = pos1.getZ() - pos2.getZ();
        return dx * dx + dz * dz;
    }
}
