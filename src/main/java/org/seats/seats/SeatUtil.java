package org.seats.seats;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class SeatUtil {
    private static final HashSet<Location> seats = new HashSet<>();

    public static void createSeat(Player player, Block block) {
        if (block.getType().toString().endsWith("_STAIRS") || block.getType().toString().endsWith("_SLAB")) {
            Location blockLocation = block.getLocation();
            if (!seats.contains(blockLocation)) {
                seats.add(blockLocation);
                player.sendMessage(ChatColor.GREEN + "Seat created at your target.");
            } else {
                player.sendMessage(ChatColor.RED + "There is already a seat at your target.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can only create a seat on stairs or slabs.");
        }
    }

    public static boolean isDesignatedSeat(Location location) {
        return seats.contains(location);
    }

    public static boolean isSeatOccupied(Location location) {
        for (Entity entity : location.getWorld().getEntitiesByClass(ArmorStand.class)) {
            if (entity.getLocation().getBlock().equals(location.getBlock()) && isSeat(entity)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSeat(Entity entity) {
        return entity instanceof ArmorStand && "Seat".equals(entity.getCustomName());
    }

    public static void markSeatOccupied(ArmorStand seat, boolean occupied) {
        if (!occupied) {
            seat.remove();
        }
    }
}
