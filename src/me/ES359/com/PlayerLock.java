package me.ES359.com;

import Events.LockEvent;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 4/27/16.
 */
public class PlayerLock extends JavaPlugin {

    public PluginDescriptionFile pdfFile = this.getDescription();

    private ArrayList<UUID> restricted = new ArrayList<>();


    public void onEnable()
    {
        Debug.log(Debug.LOG + "&cPlugin enabled.");
        configuration();
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new LockEvent(this),  this);
        getCommand("playerlock").setExecutor(new PlayerLockCommand(this));
    }


    void configuration()
    {
        this.getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public ArrayList<UUID> getRestricted() {
        return restricted;
    }

    static public boolean DEBUG = false;
}
