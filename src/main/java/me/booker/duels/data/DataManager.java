package me.booker.duels.data;

import me.booker.duels.arena.ArenaManager;
import me.booker.duels.kit.KitManager;
import me.booker.duels.user.UserManager;

public class DataManager {

    public static void load() {
        UserManager.loadUsers();
        KitManager.loadKits();
        ArenaManager.loadArenas();
    }

    public static void save() {
        UserManager.saveUsers();
        KitManager.saveKits();
        ArenaManager.saveArenas();
    }
}
