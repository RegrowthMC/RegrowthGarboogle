package org.lushplugins.regrowthgarboogle;

import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.regrowthgarboogle.command.DisposalCommand;
import org.lushplugins.regrowthgarboogle.config.ConfigManager;
import org.lushplugins.regrowthgarboogle.listener.InventoryListener;
import org.lushplugins.regrowthgarboogle.listener.NPCListener;
import org.lushplugins.regrowthgarboogle.packet.PacketHandler;
import revxrsal.commands.bukkit.BukkitLamp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class RegrowthGarboogle extends SpigotPlugin {
    private static RegrowthGarboogle plugin;

    private final Set<UUID> currentDisposers = new HashSet<>();
    private PacketHandler packetHandler;
    private ConfigManager configManager;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        ifPluginPresent("packetevents", () -> this.packetHandler = new PacketHandler());

        this.configManager = new ConfigManager();
        this.configManager.reload();

        registerListeners(
            new InventoryListener(),
            new NPCListener()
        );

        BukkitLamp.builder(this)
            .build()
            .register(new DisposalCommand());
    }

    public Set<UUID> getDisposers() {
        return currentDisposers;
    }

    public void addDisposer(UUID uuid) {
        currentDisposers.add(uuid);
    }

    public boolean removeDisposer(UUID uuid) {
        return currentDisposers.remove(uuid);
    }

    public Optional<PacketHandler> getPacketHandler() {
        return Optional.ofNullable(packetHandler);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static RegrowthGarboogle getInstance() {
        return plugin;
    }
}
