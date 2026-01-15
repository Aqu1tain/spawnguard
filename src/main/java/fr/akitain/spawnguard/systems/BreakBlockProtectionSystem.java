package fr.akitain.spawnguard.systems;

import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import fr.akitain.spawnguard.protection.SpawnRegionService;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

public class BreakBlockProtectionSystem {

    private static final Logger LOGGER = Logger.getLogger("SpawnGuard");
    private final SpawnRegionService spawnRegionService;

    public BreakBlockProtectionSystem(@Nonnull SpawnRegionService spawnRegionService) {
        this.spawnRegionService = spawnRegionService;
    }

    public void onBreakBlock(@Nonnull BreakBlockEvent event) {
        LOGGER.info("BreakBlockEvent received at: " + event.getTargetBlock());

        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            LOGGER.info("Block outside protected area, allowing");
            return;
        }

        LOGGER.info("Block in protected area, cancelling!");
        event.setCancelled(true);
    }
}
