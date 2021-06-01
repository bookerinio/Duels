package me.booker.duels.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {

    public static void log(Level level, String... msg) {
        Bukkit.getLogger().log(level, "-------------[ Duels ]-------------");
        for(String s : msg) Bukkit.getLogger().log(level, s);
        Bukkit.getLogger().log(level, "-----------------------------------");
    }
}
