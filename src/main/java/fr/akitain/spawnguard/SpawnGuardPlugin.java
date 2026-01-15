package fr.akitain.spawnguard;

import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.DamageBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import fr.akitain.spawnguard.config.SpawnGuardConfig;
import fr.akitain.spawnguard.protection.SpawnRegionService;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class SpawnGuardPlugin extends JavaPlugin {

    private SpawnGuardConfig config;
    private SpawnRegionService spawnRegionService;

    public SpawnGuardPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.config = SpawnGuardConfig.DEFAULT;

        Vector3i spawnPosition = new Vector3i(config.spawnX(), 0, config.spawnZ());
        this.spawnRegionService = new SpawnRegionService(config, spawnPosition);

        this.getEventRegistry().register(BreakBlockEvent.class, this::onBreakBlock);
        this.getEventRegistry().register(PlaceBlockEvent.class, this::onPlaceBlock);
        this.getEventRegistry().register(DamageBlockEvent.class, this::onDamageBlock);

        this.getLogger().at(Level.INFO).log("SpawnGuard initialized - Protection radius: %d blocks around spawn (%d, %d)",
            config.radius(), config.spawnX(), config.spawnZ());
    }

    private void onBreakBlock(@Nonnull BreakBlockEvent event) {
        this.getLogger().at(Level.INFO).log("BreakBlockEvent at: %s", event.getTargetBlock());

        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            return;
        }

        this.getLogger().at(Level.WARNING).log("Blocking break at: %s", event.getTargetBlock());
        event.setCancelled(true);
    }

    private void onPlaceBlock(@Nonnull PlaceBlockEvent event) {
        this.getLogger().at(Level.INFO).log("PlaceBlockEvent at: %s", event.getTargetBlock());

        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            return;
        }

        this.getLogger().at(Level.WARNING).log("Blocking place at: %s", event.getTargetBlock());
        event.setCancelled(true);
    }

    private void onDamageBlock(@Nonnull DamageBlockEvent event) {
        this.getLogger().at(Level.INFO).log("DamageBlockEvent at: %s", event.getTargetBlock());

        if (!spawnRegionService.shouldBlockModification(event.getTargetBlock())) {
            return;
        }

        this.getLogger().at(Level.WARNING).log("Blocking damage at: %s", event.getTargetBlock());
        event.setCancelled(true);
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