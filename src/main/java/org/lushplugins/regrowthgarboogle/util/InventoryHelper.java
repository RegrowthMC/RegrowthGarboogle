package org.lushplugins.regrowthgarboogle.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryHelper {

    public static Inventory createDisposalInventory() {
        return Bukkit.getServer().createInventory(null, 27, Component.text("Garboogle - ")
            .append(Component.text("Item Disposal")
                .color(TextColor.fromHexString("#d13636"))));
    }
}
