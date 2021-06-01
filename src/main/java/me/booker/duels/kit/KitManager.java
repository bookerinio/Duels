package me.booker.duels.kit;

import me.booker.duels.data.FileManager;
import me.booker.duels.utils.BukkitSerialization;
import me.booker.duels.utils.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class KitManager {

    private static List<Kit> kits = new ArrayList<>();

    public static List<Kit> getKits() {
        return kits;
    }

    public static void addKit(Kit k) {
        if (kits.contains(k)) return;
        kits.add(k);
    }

    public static boolean isKitExist(String name) {
        for(Kit k : kits) {
            if(k.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public static void createKit(String name, PlayerInventory inv) {
        Kit kit = Kit.get(name);
        kit.setContents(inv.getContents());
        kit.setArmor(inv.getArmorContents());
        saveKits();
        loadKits();
    }

    public static boolean deleteKit(String name) {
        for(Kit k : kits) {
            if(k.getName().equalsIgnoreCase(name)) {
                File f = new File(FileManager.getKitsDir(), k.getName() + ".yml");
                if(f.delete()) {
                    kits.remove(k);
                    return true;
                }
            }
        }
        return false;
    }

    public static void loadKitForPlayer(Player p, String name) {
        PlayerInventory inv = p.getInventory();
        Kit k = getKitByName(name);
        if(k == null) return;
        inv.setContents(k.getContents());
        inv.setArmorContents(k.getArmor());
    }

    public static Kit getKitByName(String name) {
        for(Kit k : kits) {
            if(k.getName().equalsIgnoreCase(name)) {
                return k;
            }
        }
        return null;
    }

    public static void loadKits() {
        int counter = 0;
        for(File f : FileManager.getKitsDir().listFiles()) {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

            Kit k = Kit.get(yml.getString("name"));

            try {
                k.setContents(BukkitSerialization.itemStackArrayFromBase64(yml.getString("contents")));
                k.setArmor(BukkitSerialization.itemStackArrayFromBase64(yml.getString("armor")));
            } catch (IOException e) {
                Logger.log(Level.WARNING,"Error: Kit " + k.getName() + " hasn't been loaded properly");
                e.printStackTrace();
            }
            counter++;
        }
        Logger.log(Level.INFO,counter + " kits loaded");
    }

    public static void saveKits() {
        int counter = 0;
        for(Kit k : kits) {
            File f = new File(FileManager.getKitsDir(), k.getName() + ".yml");

            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    Logger.log(Level.WARNING, "Error: File " + f.getName() + " hasn't been created!");
                    e.printStackTrace();
                }
            }

            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
            yml.set("name", k.getName());
            yml.set("contents", BukkitSerialization.itemStackArrayToBase64(k.getContents()));
            yml.set("armor", BukkitSerialization.itemStackArrayToBase64(k.getArmor()));

            try {
                yml.save(f);
            } catch (IOException e) {
                Logger.log(Level.WARNING, "Error: File " + f.getName() + " hasn't been saved!");
                e.printStackTrace();
            }
            counter++;
        }
        Logger.log(Level.INFO,counter + " kits saved");
    }
}
