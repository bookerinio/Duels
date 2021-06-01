package me.booker.duels.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArenaManager {

    private static List<Arena> arenas = new ArrayList<>();

    public static List<Arena> getArenaList() {
        return arenas;
    }

    public static Arena getArenaByName(String name) {
        for(Arena a : arenas) {
            if(a.getName().equalsIgnoreCase(name)) return a;
        }
        return null;
    }

    public static Arena getPlayersArena(UUID uuid) {
        for(Arena a: arenas) {
            if(a.getPlayers().contains(uuid)) return a;
        }
        return null;
    }

    public static void addArena(Arena a) {
        if(arenas.contains(a)) return;
        arenas.add(a);
    }

    public static void loadArenas() {
        //TODO loading arenas
    }

    public static void saveArenas() {
        //TODO saving arenas
    }
}
