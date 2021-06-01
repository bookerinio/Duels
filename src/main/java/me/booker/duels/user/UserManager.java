package me.booker.duels.user;

import me.booker.duels.data.FileManager;
import me.booker.duels.utils.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserManager {

    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public static void addUser(User u) {
        if(users.contains(u)) return;
        users.add(u);
    }

    public static boolean isUser(String name) {
        for(User u : users) {
            if(u.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public static void loadUsers() {
        int counter = 0;
        for(File f : FileManager.getUsersDir().listFiles()) {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

            User u = User.get(yml.getString("name"));

            u.setUuid(yml.getString("uuid"));
            u.setTotalKills(yml.getInt("total-kills"));
            u.setTotalDeaths(yml.getInt("total-deaths"));
            u.setTotalWins(yml.getInt("total-wins"));
            counter++;
        }
        Logger.log(Level.INFO, counter + " users loaded");
    }

    public static void saveUsers() {
        int counter = 0;
        for(User u : users) {
            File f = new File(FileManager.getUsersDir(), u.getName() + ".yml");

            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

            yml.set("name", u.getName());
            yml.set("uuid", u.getUuid());
            yml.set("total-kills", u.getTotalKills());
            yml.set("total-deaths", u.getTotalDeaths());
            yml.set("total-wins", u.getTotalWins());

            try {
                yml.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        }
        Logger.log(Level.INFO, counter + " users saved");
    }
}
