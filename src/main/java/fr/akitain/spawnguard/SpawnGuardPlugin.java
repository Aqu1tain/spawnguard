package fr.akitain.spawnguard;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import fr.akitain.spawnguard.commands.ExampleCommand;
import fr.akitain.spawnguard.events.ExampleEvent;

import javax.annotation.Nonnull;

public class SpawnGuardPlugin extends JavaPlugin {

    public SpawnGuardPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand("example", "An example command"));
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }
}