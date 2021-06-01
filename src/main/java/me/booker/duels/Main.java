package me.booker.duels;

import me.booker.duels.cmds.MainCmd;
import me.booker.duels.data.DataManager;
import me.booker.duels.data.FileManager;
import me.booker.duels.events.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;

    @Override
    public void onEnable() {
        inst = this;

        FileManager.check();
        DataManager.load();

        getCommand("duels").setExecutor(new MainCmd());

        registerEvents();
    }

    @Override
    public void onDisable() {
        DataManager.save();
    }

    public static Main getInst() {
        if(inst == null) return new Main();
        return inst;
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinEvent(), this);
    }
}
