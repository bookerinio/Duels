package me.booker.duels.user;

import me.booker.duels.data.FileManager;
import org.bukkit.entity.Player;

import java.io.File;

public class User {

    private String name;
    private String uuid;
    private int totalKills;
    private int totalDeaths;
    private int totalWins;

    private User(Player p) {
        this.name = p.getName();
        this.uuid = p.getUniqueId().toString();
        UserManager.addUser(this);
    }

    private User(String name) {
        this.name = name;
        UserManager.addUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public void addTotalKills() {
        this.totalKills++;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void addTotalDeaths() {
        this.totalDeaths++;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public void addTotalWins() {
        this.totalWins++;
    }

    public static User get(String name) {
        for(User u : UserManager.getUsers()) {
            if(u.getName().equalsIgnoreCase(name)) return u;
        }
        return new User(name);
    }

    public static User get(Player p) {
        for (User u : UserManager.getUsers()) {
            if (u.getName().equalsIgnoreCase(p.getName())) return u;
            if (u.getUuid().equals(p.getUniqueId().toString())) {
                if (!u.getName().equalsIgnoreCase(p.getName())) {
                    if (new File(FileManager.getUsersDir(), u.getName() + ".yml").exists()) {
                        new File(FileManager.getUsersDir(), u.getName() + ".yml").delete();
                    }
                    u.setName(p.getName());
                }
                return u;
            }
        }
        return new User(p);
    }
}
