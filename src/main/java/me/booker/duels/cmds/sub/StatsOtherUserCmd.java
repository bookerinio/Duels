package me.booker.duels.cmds.sub;

import me.booker.duels.Message;
import me.booker.duels.cmds.Cmd;
import me.booker.duels.user.User;
import me.booker.duels.user.UserManager;
import org.bukkit.command.CommandSender;

public class StatsOtherUserCmd extends Cmd {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 2) {
            String name = args[1];
            if(UserManager.isUser(name)) {
                User u = User.get(name);
                Message.msgPlayer(sender,
                        "&7Name: &e" + u.getName(),
                        "&7Total kills: &e" + u.getTotalKills(),
                        "&7Total deaths: &e" + u.getTotalDeaths(),
                        "&7Total wins: &e" + u.getTotalWins());
            } else {
                Message.msgPlayer(sender, "&7Player doesn't exist!");
            }
        } else {
            Message.msgPlayer(sender, "&7Usage: &e/duels stats [name]");
        }
    }
}
