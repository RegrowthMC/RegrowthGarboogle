package org.lushplugins.regrowthgarboogle.listener;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

// TODO: Implement configurable item blacklist for disposal (to allow blacklisting tools, armour, shulkers, etc.)
public class NPCListener implements Listener {

    @EventHandler
    public void onPlayerInteractWithNPC(NpcInteractEvent event) {
        Npc npc = event.getNpc();
        if (!npc.getData().getName().equals("Garboogle")) {
            return;
        }

        Player player = event.getPlayer();
        player.openInventory(Bukkit.getServer().createInventory(null, 27, Component.text("Garboogle - ")
            .append(Component.text("Item Disposal")
            .color(TextColor.fromHexString("#d13636")))));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        int total = Arrays.stream(inventory.getContents())
            .mapToInt(itemStack -> itemStack != null ? itemStack.getAmount() : 0)
            .sum();

        // TODO: Give 1 reward for every 64 and then X/64 chance for remainder of total
        // eg. 2 stacks and 32 items would give 2 rewards and a 50% (32/64) chance of a 3rd reward
    }
}
