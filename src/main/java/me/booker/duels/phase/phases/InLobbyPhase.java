package me.booker.duels.phase.phases;

import me.booker.duels.phase.Phase;
import org.bukkit.event.Listener;

public class InLobbyPhase extends Phase implements Listener {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public Phase getNextPhase() {
        return new PregamePhase();
    }
}
