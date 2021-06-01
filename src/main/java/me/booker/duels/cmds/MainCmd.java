package me.booker.duels.cmds;

import me.booker.duels.Message;
import me.booker.duels.Permissions;
import me.booker.duels.cmds.sub.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("duels")) {
            if(args.length < 1) {
                //TODO info
            } else {
                if(args[0].equalsIgnoreCase("info")) {
                    //TODO
                } else if(args[0].equalsIgnoreCase("stats")) {
                    if(args.length < 2) {
                        if (sender instanceof Player) {
                            if (sender.hasPermission(Permissions.STATS)) {
                                new StatsUserCmd().execute(sender, args);
                            } else {
                                Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                            }
                        } else {
                            Message.msgPlayer(sender, Message.MUST_BE_PLAYER_ERROR);
                        }
                    } else {
                        if (sender.hasPermission(Permissions.STATS_OTHERS)) {
                            new StatsOtherUserCmd().execute(sender, args);
                        } else {
                            Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                        }
                    }
                } else if(args[0].equalsIgnoreCase("kit")) {
                    if(args.length < 2) {
                        if (sender.hasPermission(Permissions.ADMIN)) {
                            new HelpKitCmd().execute(sender, args);
                        } else {
                            Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                        }
                    } else {
                        if (args[1].equalsIgnoreCase("create")) {
                            if (sender instanceof Player) {
                                if (sender.hasPermission(Permissions.ADMIN)) {
                                    new CreateKitCmd().execute(sender, args);
                                } else {
                                    Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                                }
                            } else {
                                Message.msgPlayer(sender, Message.MUST_BE_PLAYER_ERROR);
                            }
                        } else if (args[1].equalsIgnoreCase("delete")) {
                            if (sender instanceof Player) {
                                if (sender.hasPermission(Permissions.ADMIN)) {
                                    new DeleteKitCmd().execute(sender, args);
                                } else {
                                    Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                                }
                            } else {
                                Message.msgPlayer(sender, Message.MUST_BE_PLAYER_ERROR);
                            }
                        } else if (args[1].equalsIgnoreCase("load")) {
                            if (sender instanceof Player) {
                                if (sender.hasPermission(Permissions.ADMIN)) {
                                    new LoadKitCmd().execute(sender, args);
                                } else {
                                    Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                                }
                            } else {
                                Message.msgPlayer(sender, Message.MUST_BE_PLAYER_ERROR);
                            }
                        } else if (args[1].equalsIgnoreCase("info")) {
                            if (sender.hasPermission(Permissions.ADMIN)) {
                                new InfoKitCmd().execute(sender, args);
                            } else {
                                Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                            }
                        } else if (args[1].equalsIgnoreCase("list")) {
                            if (sender.hasPermission(Permissions.ADMIN)) {
                                new ListKitCmd().execute(sender, args);
                            } else {
                                Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                            }
                        } else if (args[1].equalsIgnoreCase("help")) {
                            if (sender.hasPermission(Permissions.ADMIN)) {
                                new HelpKitCmd().execute(sender, args);
                            } else {
                                Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                            }
                        } else {
                            if (sender.hasPermission(Permissions.ADMIN)) {
                                new HelpKitCmd().execute(sender, args);
                            } else {
                                Message.msgPlayer(sender, Message.NO_PERMISSION_ERROR);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
