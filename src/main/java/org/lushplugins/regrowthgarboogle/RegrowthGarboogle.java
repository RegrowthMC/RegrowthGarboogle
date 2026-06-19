package org.lushplugins.regrowthgarboogle;

import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.regrowthgarboogle.command.DisposalCommand;
import org.lushplugins.regrowthgarboogle.config.ConfigManager;
import org.lushplugins.regrowthgarboogle.listener.InventoryListener;
import org.lushplugins.regrowthgarboogle.listener.NPCListener;
import revxrsal.commands.bukkit.BukkitLamp;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class RegrowthGarboogle extends SpigotPlugin {
    private static RegrowthGarboogle plugin;

    private final Set<UUID> uuids = new HashSet<>();
    private ConfigManager configManager;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
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
        return uuids;
    }

    public void addDisposer(UUID uuid) {
        uuids.add(uuid);
    }

    public boolean removeDisposer(UUID uuid) {
        return uuids.remove(uuid);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static RegrowthGarboogle getInstance() {
        return plugin;
    }
}
