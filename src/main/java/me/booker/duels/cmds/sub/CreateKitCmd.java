package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.kit.KitManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateKitCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 3) {
            Player p = (Player) sender;
            String name = args[2];
            if(KitManager.isKitExist(name)) {
                Message.msgPlayer(p, "&7Kit already exists!");
                return;
            }
            KitManager.createKit(name, p.getInventory());
            Message.msgPlayer(p, "&7Kit created successfully!");
        } else {
            Message.msgPlayer(sender, "&7Usage: &e/duels kit create <name>");
        }
    }
}
