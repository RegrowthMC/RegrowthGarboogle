package org.lushplugins.regrowthgarboogle.listener;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.lushplugins.regrowthgarboogle.RegrowthGarboogle;
import org.lushplugins.regrowthgarboogle.util.InventoryHelper;

public class NPCListener implements Listener {

    @EventHandler
    public void onPlayerInteractWithNPC(NpcInteractEvent event) {
        Npc npc = event.getNpc();
        if (!npc.getData().getName().equals("Garboogle")) {
            return;
        }

        Player player = event.getPlayer();
        player.openInventory(InventoryHelper.createDisposalInventory());
        RegrowthGarboogle.getInstance().addDisposer(player.getUniqueId());
    }
}
