package fr.akitain.spawnguard.systems;

import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import fr.akitain.spawnguard.protection.SpawnRegionService;

import javax.annotation.Nonnull;

public class PlaceBlockProtectionSystem {

    private final SpawnRegionService spawnRegionService;

    public PlaceBlockProtectionSystem(@Nonnull SpawnRegionService spawnRegionService) {
        this.spawnRegionService = spawnRegionService;
    }

    public void onPlaceBlock(@Nonnull PlaceBlockEvent event) {
        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            return;
        }

        event.setCancelled(true);
    }
}
