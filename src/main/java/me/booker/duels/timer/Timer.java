package me.booker.duels.timer;

import me.booker.duels.Main;
import me.booker.duels.arena.Arena;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private Arena arena;
    private int startingTime;
    private int matchTime;
    private int endingTime;

    public Timer(Arena arena) {
        this.arena = arena;
        this.startingTime = 10;
        this.matchTime = arena.getMatchTime();
        this.endingTime = 5;
    }

    public void start() {
        this.runTaskTimer(Main.getInst(), 0, 20);
    }

    public void reset() {
        this.startingTime = 10;
        this.matchTime = arena.getMatchTime();
        this.endingTime = 5;
    }

    @Override
    public void run() {
        //TODO timer
    }
}
