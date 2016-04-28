package Utilities;

import me.ES359.com.PlayerLock;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug
{

   static PlayerLockUtils util = new PlayerLockUtils();


        static public String FAILED_ACTION = "[FAILED ACTION] ";
        static public String ACTION = "[ACTION] ";
        static public String LOG = "[LOG] ";
        static public String SEVERE = "[SEVERE] &c";


        static public void log(String message)
        {
            if(PlayerLock.DEBUG)
            {
                Bukkit.getServer().getConsoleSender().sendMessage(util.color(message));
            }
        }

        static public void log(Player p, String message)
        {
            if(PlayerLock.DEBUG)
            {
                p.sendMessage(util.color(message));
            }
        }

        /**
     * Simple debug flag enabler.
     * @param sender
     * @param args
     */
    public void debugToggle(CommandSender sender, String[] args)
    {
        if(args.length > 1 && args[0].equalsIgnoreCase("debug"))
        {

            /**
             * REPLACE MAINCLASS WITH YOUR MAINCLASS.
             *
             * EXAMPLE: <MAINCLASS>.DEBUG.
             *
             * Make sure to add:
             * static public DEBUG = false; // To your main class.
             *
             *
             */

            PlayerLock.DEBUG = Boolean.parseBoolean(args[1]);
            sender.sendMessage(util.color("[&4DEBUG&f] &c--> &7You have set Debug status to &4&l: " + PlayerLock.DEBUG));
        }
    }
}