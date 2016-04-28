package Events;

import Utilities.Debug;
import Utilities.PlayerLockPermmissions;
import Utilities.PlayerLockUtils;
import me.ES359.com.PlayerLock;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

/**
 * Created by ES359 on 4/27/16.
 */
public class LockEvent extends PlayerLockUtils implements Listener
{

    PlayerLock main;

    public LockEvent(PlayerLock instance)
    {
        this.main = instance;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {

        Player player = event.getPlayer();

        if(main.restricted.contains(player.getUniqueId()))
        {
            Debug.log(Debug.LOG + "Player has been added...");
            event.setTo(event.getFrom());
            player.sendMessage(color(main.getConfig().getString("Inform-msg")));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {

        Player p = event.getPlayer();

        displayAuthInfo(p);

        if(main.getConfig().getBoolean("Lock-API"))
       {


           //TODO implement informer message.

           if(!p.hasPermission(PlayerLockPermmissions.RESTRICT_BYPASS_PERM))
           {

               if(!main.restricted.contains(p.getUniqueId()))
               {
                   Debug.log(Debug.LOG + "Player has been added. ");
                   main.restricted.add(p.getUniqueId());
                   p.sendMessage(color(main.getConfig().getString("Restrict-msg")));
               }
           }
       }else
       {
           main.restricted.remove(p.getUniqueId());
           Debug.log(Debug.ACTION + "This should be called if the lock API is false..");
       }
    }
}
