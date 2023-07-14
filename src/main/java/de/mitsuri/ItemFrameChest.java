package de.mitsuri;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemFrameChest extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ItemFrameListener(this), this);
    }
}