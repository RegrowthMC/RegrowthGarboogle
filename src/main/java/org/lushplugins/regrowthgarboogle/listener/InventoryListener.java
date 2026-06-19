package org.lushplugins.regrowthgarboogle.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.lushplugins.regrowthgarboogle.RegrowthGarboogle;

import java.util.Arrays;
import java.util.List;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClickItem(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) {
            return;
        }

        HumanEntity player = event.getWhoClicked();
        if (!RegrowthGarboogle.getInstance().getDisposers().contains(player.getUniqueId())) {
            return;
        }

        ItemStack item = inventory.getItem(event.getSlot());
        if (item != null && RegrowthGarboogle.getInstance().getConfigManager().getBlacklistedItems().contains(item.getType())) {
            event.setCancelled(true);
            player.sendMessage(Component.text("Garboogle doesn't accept this item")
                .color(TextColor.fromHexString("#ff6969")));
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();
        if (!RegrowthGarboogle.getInstance().removeDisposer(player.getUniqueId())) {
            return;
        }

        Inventory inventory = event.getInventory();
        int totalItems = Arrays.stream(inventory.getContents())
            .mapToInt(itemStack -> itemStack != null ? itemStack.getAmount() : 0)
            .sum();

        int rewards = totalItems / 64;
        if ((totalItems % 64) / 64D > Math.random()) {
            rewards++;
        }

        if (rewards == 0) {
            return;
        }

        List<String> commands = RegrowthGarboogle.getInstance().getConfigManager().getRewards().stream()
            .map(command -> command.replace("%player%", player.getName()))
            .toList();
        for (int i = 0; i < rewards; i++) {
            for (String command : commands) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
    }
}
