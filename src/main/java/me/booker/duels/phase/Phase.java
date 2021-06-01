package me.booker.duels.phase;

import org.bukkit.event.Listener;

public abstract class Phase implements Listener {

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract Phase getNextPhase();
}
