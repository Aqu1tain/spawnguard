package fr.akitain.spawnguard.config;

import javax.annotation.Nonnull;

public record SpawnGuardConfig(
    boolean enabled,
    int radius,
    int spawnX,
    int spawnZ,
    String bypassPermission,
    String denyMessage
) {

    public static final SpawnGuardConfig DEFAULT = new SpawnGuardConfig(
        true,
        100,
        -834,
        100,
        "spawnguard.bypass",
        "You cannot modify blocks in the spawn area!"
    );

    public SpawnGuardConfig {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        if (bypassPermission == null || bypassPermission.isBlank()) {
            throw new IllegalArgumentException("Bypass permission cannot be null or empty");
        }
        if (denyMessage == null || denyMessage.isBlank()) {
            throw new IllegalArgumentException("Deny message cannot be null or empty");
        }
    }

    @Nonnull
    public String getBypassPermission() {
        return bypassPermission;
    }

    @Nonnull
    public String getDenyMessage() {
        return denyMessage;
    }
}
