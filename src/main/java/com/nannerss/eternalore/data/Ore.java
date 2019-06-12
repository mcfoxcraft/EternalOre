package com.nannerss.eternalore.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.nannerss.bananalib.config.ConfigManager;
import com.nannerss.eternalore.EternalOre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ore {
    
    private ConfigManager cfg;
    
    private String id;
    private String type;
    private Location location;
    private long respawnTime;
    
    public Ore(String id) {
        this.id = id;
        
        load();
    }
    
    private void load() {
        cfg = EternalOre.getOres();
        
        type = cfg.getString("ores." + id + ".type", "");
        
        String[] locString = cfg.getString("ores." + id + ".location", "").split("!");
        if (locString.length == 4) {
            location = new Location(Bukkit.getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
        } else {
            location = null;
        }
        
        respawnTime = cfg.getLong("ores." + id + ".respawn-time", 0);
    }
    
    public void save() {
        cfg = EternalOre.getOres();
        
        cfg.set("ores." + id + ".type", type);
    
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        String locString = world + "!" + x + "!" + y + "!" + z;
        
        cfg.set("ores." + id + ".location", locString);
        
        cfg.set("ores." + id + ".respawn-time", respawnTime);
        
        cfg.saveConfig();
    }
    
}
