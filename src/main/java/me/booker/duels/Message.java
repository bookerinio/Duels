package me.booker.duels;

import me.booker.duels.utils.StringUtils;
import org.bukkit.command.CommandSender;

public class Message {

    public static String MUST_BE_PLAYER_ERROR = "&cYou must be a player to use this command!";
    public static String NO_PERMISSION_ERROR = "&cYou don't have permission to use this command!";

    public static void msgPlayer(CommandSender player, String... msg) {
        for(String s : msg) {
            player.sendMessage(StringUtils.color(s));
        }
    }
}
