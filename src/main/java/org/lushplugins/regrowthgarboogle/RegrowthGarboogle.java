package org.lushplugins.regrowthgarboogle;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthGarboogle extends JavaPlugin {
    private static RegrowthGarboogle plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthGarboogle getInstance() {
        return plugin;
    }
}
