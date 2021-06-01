package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.kit.Kit;
import me.booker.duels.kit.KitManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InfoKitCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 3) {
            Player p = (Player) sender;
            String name = args[2];
            if(!KitManager.isKitExist(name)) {
                Message.msgPlayer(p, "&7Kit doesn't exist!");
                return;
            }
            Kit k = KitManager.getKitByName(name);

            String contents = getStringFromInv(k.getContents());
            String armor = getStringFromInv(k.getArmor());

            Message.msgPlayer(sender, "&7Contents: " + contents);
            Message.msgPlayer(sender, "&7Armor: " + armor);
        } else {
            Message.msgPlayer(sender, "&7Usage: &e/duels kit info <name>");
        }
    }

    private String getStringFromInv(ItemStack[] itemList) {
        String string = "";
        List<String> listString = new ArrayList<>();

        for (ItemStack c : itemList) {
            if(c != null && c.getType() != Material.AIR) {
                listString.add("&e" + c.getType().toString().toLowerCase().replace("_", " ") + " &8x &6" + c.getAmount());
                string = String.join("&8, ", listString);
            }
        }
        if(string.equals("")) string = "&eempty";
        return string;
    }
}
