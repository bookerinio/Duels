package me.booker.duels.phase;

import me.booker.duels.Main;
import me.booker.duels.phase.phases.InLobbyPhase;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public class PhaseManager {

    private Phase currentPhase;

    public PhaseManager() {
        setPhase(new InLobbyPhase());
    }

    public boolean isInPhase(Class<? extends Phase> phaseClass) {
        return currentPhase.getClass().equals(phaseClass);
    }

    public void nextPhase() {
        setPhase(currentPhase.getNextPhase());
    }

    private void setPhase(Phase phase) {
        if(currentPhase != null) currentPhase.onDisable();
        HandlerList.unregisterAll(currentPhase);

        currentPhase = phase;
        Bukkit.getPluginManager().registerEvents(currentPhase, Main.getInst());
        currentPhase.onEnable();
    }
}
