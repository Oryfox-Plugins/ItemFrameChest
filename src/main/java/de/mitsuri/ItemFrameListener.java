package de.mitsuri;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemFrameListener implements Listener {

    private final JavaPlugin parent;

    public ItemFrameListener(JavaPlugin parent) {
        this.parent = parent;
    }

    @EventHandler
    public void itemFrameInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.ITEM_FRAME) {
            return;
        }
        var entity = (ItemFrame) event.getRightClicked();
        if (entity.getItem().getType().isAir()) {
            return;
        }
        var attachedTo = entity.getLocation().getBlock().getRelative(entity.getAttachedFace());
        if (attachedTo.getType() != Material.CHEST) {
            return;
        }
        if (event.getPlayer().isSneaking()) {
            return;
        }
        event.setCancelled(true);
        Bukkit.getScheduler().runTask(parent, () -> {
            event.getPlayer().openInventory(((Chest)attachedTo.getState()).getInventory());
        });
    }
}
