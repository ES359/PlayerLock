package Utilities;

import me.ES359.com.PlayerLock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by ES359 on 4/27/16.
 */
public class PlayerLockUtils
{
    /**
     * Hard coded version ID. Because I forget.
     */
    private final String version_ID = "0.0.1";

    /**
     * Plugin prefix.
     *
     *  Custom prefix etc.
     */
    private String prefix = ChatColor.translateAlternateColorCodes('&',"&6Player&7Lock");

    /**
     * Constant permission error.
     *
     * Returns a hard coded permission error. //Don't really use this due to the fact that I use configs.
     */
    private String permission = getPrefix()+color("&cUnknown command. Type \"/help\" for help.");
    public String getPermission()
    {
        return this.permission;
    }

    /**
     * If you want to display a donation link, put it here.
     */
    private String donationURL = "DONATION_URL";
    /**
     * @return Donation URL
     */
    public String getDonationURL()
    {
        return donationURL;
    }

    /**
     * Author.
     * Checks to see if Author is using plugin.
     */
    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    boolean checkAuth(UUID user)
    {
        return user.toString().equals(author);
    }

    /**
     *  Informs author that plugin is being used by server.
     *
     * @param p
     */
    public void displayAuthInfo(Player p)
    {
        if(checkAuth(p.getUniqueId()))
        {
            p.sendMessage(color("&a&l&oHello, &7"+ p.getName() +"\n &aThis server is using ") + getPrefix());
        }
    }

    /**
     *  Returns this plugins version.
     */
    public String getPluginVersion(PlayerLock main, CommandSender sender)
    {
        return color("&fHello, &a&n"+sender.getName() +".&r\nYou are currently running version &b&n"+main.pdfFile.getVersion() + "&r of &e&n"+main.pdfFile.getName() +"&r\n \n&6Your server is running version &c&n"+ main.getServer().getBukkitVersion());
    }

    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }

    public void logToConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void desc(CommandSender sender, PlayerLock main) {
        sender.sendMessage(color("&2========== " + getPrefix().replace(":", "") + "&2=========="));
        sender.sendMessage(color("&7[&9" + main.pdfFile.getName() + "&7] &6Created by, &b&l" + main.pdfFile.getAuthors() + "&6."));
        sender.sendMessage(color("&2" + main.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + main.pdfFile.getWebsite()));
    }

            public void displayHelp(CommandSender sender, String title, String command, String info)
            {
                sender.sendMessage(color(title));
                sender.sendMessage("");
                sender.sendMessage(color(command));
                sender.sendMessage("");
                sender.sendMessage(color(info));
            }

        public void displayHelpMsg(Player player, String title, String body, String information)
        {
            player.sendMessage(color(title));
            player.sendMessage("");
            player.sendMessage(color(body));
            player.sendMessage(" ");
            player.sendMessage(color(information));
        }

        public String color(String message) {

        String msg =  message;
        msg = msg.replace("&", "§");
        msg = msg.replace("%prefix%",getPrefix());
        return msg;
    }

        /**
         *  A method of controlling exception messages in the console.
         *
         * WARNING: This requires you to have the class Debug.java within your project.
         *
         * @param e
         */
        public void exceptionDebug(Exception e)
        {
            if(PlayerLock.DEBUG)
            {
                logToConsole("&cERROR: &3" +e.getMessage());
                e.printStackTrace();
            }else
            {
                logToConsole("&cERROR: &3" +e.getMessage());
            }
        }
}