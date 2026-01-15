package fr.akitain.spawnguard.systems;

import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import fr.akitain.spawnguard.protection.SpawnRegionService;

import javax.annotation.Nonnull;

public class BreakBlockProtectionSystem {

    private final SpawnRegionService spawnRegionService;

    public BreakBlockProtectionSystem(@Nonnull SpawnRegionService spawnRegionService) {
        this.spawnRegionService = spawnRegionService;
    }

    public void onBreakBlock(@Nonnull BreakBlockEvent event) {
        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            return;
        }

        event.setCancelled(true);
    }
}
