package org.seats.seats;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SeatListener implements Listener {

    private final Seats plugin;

    public SeatListener(Seats plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();

            if (block != null && (block.getType().toString().endsWith("_STAIRS") || block.getType().toString().endsWith("_SLAB"))) {
                event.setCancelled(true); // Prevent default block interaction

                // Calculate correct seating Y offset based on block type
                double yOffset = block.getType().toString().endsWith("_STAIRS") ? 0.3 : -0.5; // Adjust yOffset for slab and stairs

                // Correct sitting location, considering the block type and ensuring it's centered
                Location sitLocation = block.getLocation().clone().add(0.5, yOffset, 0.5);

                if (!SeatUtil.isSeatOccupied(sitLocation)) {
                    // Create an invisible armor stand at the calculated position
                    ArmorStand seat = player.getWorld().spawn(sitLocation, ArmorStand.class);
                    seat.setVisible(false);
                    seat.setGravity(false);
                    seat.setCustomName("Seat");
                    seat.setCustomNameVisible(false);
                    seat.setMarker(true);

                    // Make the player sit by adding them as a passenger of the armor stand
                    seat.addPassenger(player);
                }
            }
        }
    }
}
