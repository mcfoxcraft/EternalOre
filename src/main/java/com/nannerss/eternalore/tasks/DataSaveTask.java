package com.nannerss.eternalore.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import com.nannerss.eternalore.lib.config.ConfigManager;
import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;

public class DataSaveTask extends BukkitRunnable {
    
    @Override
    public void run() {
        final ConfigManager cfg = EternalOre.getOres();
        
        for (Ore ore : EternalOre.getOresCache().asMap().values()) {
            ore.save();
        }
        
        cfg.saveConfig();
    }
    
}
