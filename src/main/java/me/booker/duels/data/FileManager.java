package me.booker.duels.data;

import me.booker.duels.Main;

import java.io.File;

public class FileManager {

    private static File mainDir = Main.getInst().getDataFolder();
    private static File users = new File(mainDir, "users");
    private static File arenas = new File(mainDir, "arenas");
    private static File kits = new File(mainDir, "kits");
    private static File defaultKit = new File(kits, "default.yml");

    public static void check() {
        if(!mainDir.exists()) mainDir.mkdir();
        if(!users.exists()) users.mkdir();
        if(!arenas.exists()) arenas.mkdir();
        if(!kits.exists()) kits.mkdir();
        if(!defaultKit.exists()) Main.getInst().saveResource("kits/default.yml", true);
    }

    public static File getUsersDir () {
        return users;
    }

    public static File getArenasDir () {
        return arenas;
    }

    public static File getKitsDir() {
        return kits;
    }
}
