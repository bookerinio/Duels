package me.booker.duels.utils;

import org.bukkit.ChatColor;

public class StringUtils {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String stripColor(String msg) {
        return ChatColor.stripColor(msg);
    }
}
