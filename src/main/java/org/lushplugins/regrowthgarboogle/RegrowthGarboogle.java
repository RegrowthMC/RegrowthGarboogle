package org.lushplugins.regrowthgarboogle;

import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.regrowthgarboogle.listener.NPCListener;

public final class RegrowthGarboogle extends SpigotPlugin {
    private static RegrowthGarboogle plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        registerListener(new NPCListener());
    }

    public static RegrowthGarboogle getInstance() {
        return plugin;
    }
}
