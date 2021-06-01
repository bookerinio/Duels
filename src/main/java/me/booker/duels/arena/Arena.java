package me.booker.duels.arena;

import me.booker.duels.PlayerRollbackManager;
import me.booker.duels.kit.Kit;
import me.booker.duels.phase.PhaseManager;
import me.booker.duels.phase.phases.InLobbyPhase;
import me.booker.duels.timer.Timer;
import me.booker.duels.utils.Logger;
import me.booker.duels.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Arena {

    private PhaseManager phaseManager;
    private Timer timer;
    private Kit kit;
    private PlayerRollbackManager playerRollbackManager;

    private String name;
    private String winner;

    private Location spawnLocOne;
    private Location spawnLocTwo;

    private HashMap<Location, Material> destroyedBlocks;
    private HashMap<Location, Material> placedBlocks;

    private List<UUID> players;

    private int maxPlayers;

    private int matchTime;

    public Arena(String name, Location spawnLocOne, Location spawnLocTwo, int matchTime) {
        this.name = name;
        this.spawnLocOne = spawnLocOne;
        this.spawnLocTwo = spawnLocTwo;
        this.matchTime = matchTime;

        maxPlayers = 2;

        phaseManager = new PhaseManager();
        timer = new Timer(this);
        timer.start();
        kit = null;
        playerRollbackManager = new PlayerRollbackManager();

        destroyedBlocks = new HashMap<>();
        placedBlocks = new HashMap<>();

        players = new ArrayList<>();

        winner = null;

        ArenaManager.addArena(this);
    }

    public void broadcastMessage(String... msg) {
        for(int i=0;i<players.size();i++) {
            for(String s : msg) {
                Player p = Bukkit.getPlayer(players.get(i));
                if(p == null) {
                    Logger.log(Level.WARNING, "Error: Player not found in arena " + name);
                } else {
                    p.sendMessage(StringUtils.color(s));
                }
            }
        }
    }

    public void playerJoin(Player p) {
        if(!players.contains(p.getUniqueId())) {
            if(spawnLocOne != null && spawnLocTwo != null) {
                if(players.size() <= maxPlayers) {
                    if(phaseManager.isInPhase(InLobbyPhase.class)) {
                        players.add(p.getUniqueId());

                        broadcastMessage("&7Player &e" + p.getName() + " &7has joined the game! " +
                                "&8(&e" + players.size() + "&7/&6" + maxPlayers + "&8");



                        if(players.size() == 2) {
                            phaseManager.nextPhase();
                        }
                    }
                }
            }
        }
    }

    public void playerLeave(Player p) {
        //TODO player leave
    }

    public void reset() {
        //TODO remove placed blocks and regen destroyed blocks
        destroyedBlocks.clear();
        placedBlocks.clear();
        players.clear();
        timer.reset();
    }

    public void teleportToArena() {
        Bukkit.getPlayer(players.get(0)).teleport(spawnLocOne);
        Bukkit.getPlayer(players.get(1)).teleport(spawnLocTwo);
    }

    //GETTERS
    public PhaseManager getPhaseManager() {
        return phaseManager;
    }

    public Timer getTimer() {
        return timer;
    }

    public Kit getKit() {
        return kit;
    }

    public PlayerRollbackManager getPlayerRollbackManager() {
        return playerRollbackManager;
    }

    public String getName() {
        return name;
    }

    public String getWinner() {
        return winner;
    }

    public Location getSpawnLocOne() {
        return spawnLocOne;
    }

    public Location getSpawnLocTwo() {
        return spawnLocTwo;
    }

    public HashMap<Location, Material> getDestroyedBlocks() {
        return destroyedBlocks;
    }

    public HashMap<Location, Material> getPlacedBlocks() {
        return placedBlocks;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMatchTime() {
        return matchTime;
    }

    //SETTERS
    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setSpawnLocOne(Location spawnLocOne) {
        this.spawnLocOne = spawnLocOne;
    }

    public void setSpawnLocTwo(Location spawnLocTwo) {
        this.spawnLocTwo = spawnLocTwo;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }
}
