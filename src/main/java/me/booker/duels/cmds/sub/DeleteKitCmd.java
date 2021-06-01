package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.kit.KitManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteKitCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 3) {
            Player p = (Player) sender;
            String name = args[2];
            if(!KitManager.isKitExist(name)) {
                Message.msgPlayer(p, "&7Kit doesn't exist!");
                return;
            }
            if(KitManager.deleteKit(name)) {
                Message.msgPlayer(p, "&7Kit deleted successfully!");
            } else {
                Message.msgPlayer(p, "&7Kit hasn't been deleted successfully!");
            }
        } else {
            Message.msgPlayer(sender, "&7Usage: &e/duels kit delete <name>");
        }
    }
}
