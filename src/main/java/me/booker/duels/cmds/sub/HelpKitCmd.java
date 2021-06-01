package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import org.bukkit.command.CommandSender;

public class HelpKitCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Message.msgPlayer(sender, "&e/duels kit &7[&6create&7|&6delete&7|&6help&7|&6info&7|&6list&7|&6load&7]");
    }
}
