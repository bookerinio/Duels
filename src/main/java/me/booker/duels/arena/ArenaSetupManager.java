package me.booker.duels.arena;

import me.booker.duels.PlayerRollbackManager;
import me.booker.duels.utils.ItemBuilder;
import me.booker.duels.utils.StringUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArenaSetupManager implements Listener {

    private final static PlayerRollbackManager rollbackManager = new PlayerRollbackManager();

    private final static Map<UUID, Arena> setupArenaMap = new HashMap<>();

    private final static String SET_LOCATION_ONE_ITEM_NAME = StringUtils.color("&eSet Location One &8(&7Right click&8)");
    private final static String SET_LOCATION_TWO_ITEM_NAME = StringUtils.color("&eSet Location Two &8(&7Right click&8)");
    private final static String SAVE_ITEM_NAME = StringUtils.color("&aSave &8(&7Right click&8)");
    private final static String CANCEL_ITEM_NAME = StringUtils.color("&cCancel &8(&7Right click&8)");

    public static Map<UUID, Arena> getSetupArenaMap() {
        return setupArenaMap;
    }

    public static void addArenaForSetup(Player p, Arena a) {
        if(setupArenaMap.containsKey(p.getUniqueId())) return;

        rollbackManager.save(p);
        p.setGameMode(GameMode.CREATIVE);

        setupArenaMap.put(p.getUniqueId(), a);

        p.getInventory().addItem(
                new ItemBuilder(Material.STICK)
                    .setName(SET_LOCATION_ONE_ITEM_NAME)
                    .toItemStack()
        );
        p.getInventory().addItem(
                new ItemBuilder(Material.BLAZE_ROD)
                        .setName(SET_LOCATION_TWO_ITEM_NAME)
                        .toItemStack()
        );
        p.getInventory().addItem(
                new ItemBuilder(Material.EMERALD)
                        .setName(SAVE_ITEM_NAME)
                        .toItemStack()
        );
        p.getInventory().addItem(
                new ItemBuilder(Material.BARRIER)
                        .setName(CANCEL_ITEM_NAME)
                        .toItemStack()
        );
    }

    public static void removeFromSetup(Player p) {
        if(!setupArenaMap.containsKey(p.getUniqueId())) return;
        setupArenaMap.remove(p.getUniqueId());

        rollbackManager.restore(p, null);
    }

    public boolean inSetupMode(Player p) {
        return setupArenaMap.containsKey(p.getUniqueId());
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent e) {
        if(!inSetupMode(e.getPlayer())) return;
        if(!e.hasItem()) return;
        if(!e.getItem().hasItemMeta()) return;

        Player p = e.getPlayer();
        String itemName = e.getItem().getItemMeta().getDisplayName();

        if(itemName.equalsIgnoreCase(SET_LOCATION_ONE_ITEM_NAME)) {

        } else if(itemName.equalsIgnoreCase(SET_LOCATION_TWO_ITEM_NAME)) {

        } else if(itemName.equalsIgnoreCase(SAVE_ITEM_NAME)) {

        } else if(itemName.equalsIgnoreCase(CANCEL_ITEM_NAME)) {
            removeFromSetup(p);
        } else {
            return;
        }

        e.setCancelled(true);
    }
}
