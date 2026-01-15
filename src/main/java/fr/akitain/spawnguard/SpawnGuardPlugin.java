package fr.akitain.spawnguard;

import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import fr.akitain.spawnguard.config.SpawnGuardConfig;
import fr.akitain.spawnguard.protection.SpawnRegionService;
import fr.akitain.spawnguard.systems.BreakBlockProtectionSystem;
import fr.akitain.spawnguard.systems.PlaceBlockProtectionSystem;

import javax.annotation.Nonnull;

public class SpawnGuardPlugin extends JavaPlugin {

    private SpawnGuardConfig config;
    private SpawnRegionService spawnRegionService;

    public SpawnGuardPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.config = SpawnGuardConfig.DEFAULT;
        this.spawnRegionService = new SpawnRegionService(config, Vector3i.ZERO);

        BreakBlockProtectionSystem breakBlockSystem = new BreakBlockProtectionSystem(spawnRegionService);
        PlaceBlockProtectionSystem placeBlockSystem = new PlaceBlockProtectionSystem(spawnRegionService);

        this.getEventRegistry().register(BreakBlockEvent.class, breakBlockSystem::onBreakBlock);
        this.getEventRegistry().register(PlaceBlockEvent.class, placeBlockSystem::onPlaceBlock);

        this.getLogger().at(java.util.logging.Level.INFO).log("SpawnGuard initialized - Protection radius: %d blocks", config.radius());
    }

    @Nonnull
    public SpawnGuardConfig getConfig() {
        return config;
    }

    @Nonnull
    public SpawnRegionService getSpawnRegionService() {
        return spawnRegionService;
    }
}