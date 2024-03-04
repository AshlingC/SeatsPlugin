package org.seats.seats;

import org.bukkit.plugin.java.JavaPlugin;

public class Seats extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("createseat").setExecutor(new SeatCommand(this));
        getCommand("deleteseat").setExecutor(new SeatCommand(this));
        getCommand("deleteallseats").setExecutor(new SeatCommand(this));
        getServer().getPluginManager().registerEvents(new SeatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
