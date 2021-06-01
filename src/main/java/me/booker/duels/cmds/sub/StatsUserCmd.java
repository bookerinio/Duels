package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsUserCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User u = User.get(p.getName());
        Message.msgPlayer(p,
                "&7Name: &e" + p.getName(),
                "&7Total kills: &e" + u.getTotalKills(),
                "&7Total deaths: &e" + u.getTotalDeaths(),
                "&7Total wins: &e" + u.getTotalWins());
    }
}
