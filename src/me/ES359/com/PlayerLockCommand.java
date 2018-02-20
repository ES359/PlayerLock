package me.ES359.com;

import Utilities.Debug;
import Utilities.PlayerLockPermmissions;
import Utilities.PlayerLockUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 4/27/16.
 */
public class PlayerLockCommand extends PlayerLockUtils implements CommandExecutor
{

    PlayerLock main;

    public PlayerLockCommand(PlayerLock instance)
    {
        this.main = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("playerlock"))
        {

            if(!sender.hasPermission(PlayerLockPermmissions.PLAYERLOCK_COMMAND_PERM))
            {
                sender.sendMessage(color(main.getConfig().getString("no-Permission")));
            }else
            {
                if(args.length < 1)
                {

                    displayHelp(sender,"&7--------------- %prefix% &7---------------","&a&l! >> &7Command usage: \n&7/playerlock <&aplayername&7>" +
                            "\n&7/playerlock <&aon&7> || <&coff&7>\n&7/playerlock version","&7-----------------------------------");
                }else if(args.length > 0)
                {
                    if(args[0].equalsIgnoreCase("off"))
                    {
                        main.getConfig().set("Lock-API", false);
                        main.saveConfig();
                        sender.sendMessage(color(main.getConfig().getString("Lock-API-off")));
                    }else if(args[0].equalsIgnoreCase("on"))
                    {
                        main.getConfig().set("Lock-API",true);
                        main.saveConfig();
                        sender.sendMessage(color(main.getConfig().getString("Lock-API-on")));
                    }else if(args[0].equalsIgnoreCase("version"))
                    {
                        sender.sendMessage(getPluginVersion(main,sender));
                    }else if(args[0].equalsIgnoreCase("about"))
                    {
                        desc(sender,main);
                    }
                    else
                    {
                        Player target = Bukkit.getServer().getPlayer(args[0]);

                        if(target == null)
                        {
                            sender.sendMessage(color("&b&l! >> &7Error, cannot find that player: &b" + args[0]));
                        }else
                        {
                            if(!main.getRestricted().contains(target.getUniqueId()))
                            {
                                String conf = this.main.getConfig().getString("Admin-lock-msg");

                                conf = conf.replace("%user%",target.getName());

                                main.getRestricted().add(target.getUniqueId());

                                sender.sendMessage(color(conf));
                                target.sendMessage(color(main.getConfig().getString("User-lock-msg")));
                            }else
                            {
                                String conf = this.main.getConfig().getString("Admin-unlock-msg");

                                conf = conf.replace("%user%",target.getName());

                                main.getRestricted().remove(target.getUniqueId());
                                sender.sendMessage(color(conf));
                                target.sendMessage(color(main.getConfig().getString("User-unlock-msg")));
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
