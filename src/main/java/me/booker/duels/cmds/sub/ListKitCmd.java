package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.kit.Kit;
import me.booker.duels.kit.KitManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ListKitCmd extends Cmd {


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            Player p = (Player) sender;
            List<Kit> kits = KitManager.getKits();
            if(kits.isEmpty()) {
                Message.msgPlayer(sender, "&7Kits: &eempty");
            } else {
                List<String> kitNameList = new ArrayList<>();
                for(Kit k : kits) {
                    kitNameList.add("&e" + k.getName());
                }
                String joined = String.join("&8, ", kitNameList);
                Message.msgPlayer(sender, "&7Kits: " + joined);
            }
        } else {
            Message.msgPlayer(sender, "&7Usage: &e/duels kit list");
        }
    }
}
