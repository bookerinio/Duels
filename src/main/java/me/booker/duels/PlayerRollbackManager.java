package me.booker.duels;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerRollbackManager {

    private final Map<UUID, Location> previousLocationMap = new HashMap<>();
    private final Map<UUID, GameMode> previousGameModeMap = new HashMap<>();
    private final Map<UUID, ItemStack[]> previousInventoryContents = new HashMap<>();
    private final Map<UUID, ItemStack[]> previousArmorContents = new HashMap<>();
    private final Map<UUID, Integer> previousHungerValue = new HashMap<>();
    private final Map<UUID, Integer> previousLevelMap = new HashMap<>();

    public void save(Player p) {
        previousLocationMap.put(p.getUniqueId(), p.getLocation());
        previousGameModeMap.put(p.getUniqueId(), p.getGameMode());
        previousInventoryContents.put(p.getUniqueId(), p.getInventory().getContents());
        previousArmorContents.put(p.getUniqueId(), p.getInventory().getArmorContents());
        previousHungerValue.put(p.getUniqueId(), p.getFoodLevel());
        previousLevelMap.put(p.getUniqueId(), p.getLevel());
        p.getInventory().clear();
    }

    public void restore(Player p, Plugin pl) {
        p.getInventory().clear();

        ItemStack[] invContents = previousInventoryContents.get(p.getUniqueId());
        if(invContents != null) {
            p.getInventory().setContents(invContents);
        }

        ItemStack[] armorContents = previousArmorContents.get(p.getUniqueId());
        if(armorContents != null) {
            p.getInventory().setArmorContents(armorContents);
        }

        GameMode previousGameMode = previousGameModeMap.get(p.getUniqueId());
        if(previousGameMode != null) {
            p.setGameMode(previousGameMode);
        }

        Location previousLoc = previousLocationMap.get(p.getUniqueId());
        if(previousLoc != null) {
            p.teleport(previousLoc);
        }

        p.setFoodLevel(previousHungerValue.getOrDefault(p.getUniqueId(), 20));

        p.setLevel(previousLevelMap.getOrDefault(p.getUniqueId(), 0));

        previousLocationMap.remove(p.getUniqueId());
        previousGameModeMap.remove(p.getUniqueId());
        previousInventoryContents.remove(p.getUniqueId());
        previousArmorContents.remove(p.getUniqueId());
        previousHungerValue.remove(p.getUniqueId());
        previousLevelMap.remove(p.getUniqueId());

        if(pl == null) return;
        Bukkit.getServer().getScheduler().runTaskLater(pl, bukkitTask -> p.setFireTicks(0), 2L);
    }
}
