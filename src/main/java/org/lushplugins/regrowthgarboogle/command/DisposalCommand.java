package org.lushplugins.regrowthgarboogle.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.lushplugins.regrowthgarboogle.RegrowthGarboogle;
import org.lushplugins.regrowthgarboogle.util.InventoryHelper;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("disposal")
public class DisposalCommand {

    @Command("disposal")
    @CommandPermission("disposal.disposal")
    public void disposal(BukkitCommandActor actor) {
        actor.requirePlayer().openInventory(InventoryHelper.createDisposalInventory());
    }

    @Subcommand("reload")
    @CommandPermission("disposal.reload")
    public void reload(CommandSender sender) {
        RegrowthGarboogle.getInstance().getConfigManager().reload();

        sender.sendMessage(Component.text()
            .content("RegrowthGarboogle reloaded!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
