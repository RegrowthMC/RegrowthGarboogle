package org.lushplugins.regrowthgarboogle.config;

import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.configuration.file.FileConfiguration;
import org.lushplugins.lushlib.config.YamlUtils;
import org.lushplugins.lushlib.utils.registry.RegistryUtils;
import org.lushplugins.regrowthgarboogle.RegrowthGarboogle;

import java.util.List;

public class ConfigManager {
    private List<String> rewards;
    private List<Material> blacklistedItems;

    public ConfigManager() {
        RegrowthGarboogle.getInstance().saveDefaultConfig();
    }

    public void reload() {
        RegrowthGarboogle plugin = RegrowthGarboogle.getInstance();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        this.rewards = YamlUtils.getStringList(config, "rewards");
        this.blacklistedItems = RegistryUtils.fromStringList(config.getStringList("blacklisted-items"), Registry.MATERIAL);
    }

    public List<String> getRewards() {
        return rewards;
    }

    public List<Material> getBlacklistedItems() {
        return blacklistedItems;
    }
}
