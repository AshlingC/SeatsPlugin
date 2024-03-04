package org.seats.seats;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeatCommand implements CommandExecutor {

    private final Seats plugin;

    public SeatCommand(Seats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("createseat")) {
            Block targetBlock = player.getTargetBlockExact(5);
            if (targetBlock == null) {
                player.sendMessage(ChatColor.RED + "No target block within range.");
                return true;
            }
            SeatUtil.createSeat(player, targetBlock);
            return true;
        }
        return false;
    }
}
