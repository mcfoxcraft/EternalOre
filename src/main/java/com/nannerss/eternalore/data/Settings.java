package com.nannerss.eternalore.data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;

import com.nannerss.bananalib.config.ConfigManager;
import com.nannerss.eternalore.EternalOre;

public class Settings {
    
    private static ConfigManager cfg;
    
    public static Material WAND_MATERIAL;
    public static Material PLACEHOLDER_BLOCK;
    public static List<Material> ORES = Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE);
    
    public static Material COAL_ORE = Material.COAL_ORE;
    public static Material COAL_DROP;
    public static int COAL_MAX_DROP_COUNT;
    public static long COAL_RESPAWN_TIME;
    
    public static Material IRON_ORE = Material.IRON_ORE;
    public static Material IRON_DROP;
    public static int IRON_MAX_DROP_COUNT;
    public static long IRON_RESPAWN_TIME;
    
    public static Material GOLD_ORE = Material.GOLD_ORE;
    public static Material GOLD_DROP;
    public static int GOLD_MAX_DROP_COUNT;
    public static long GOLD_RESPAWN_TIME;
    
    public static Material DIAMOND_ORE = Material.DIAMOND_ORE;
    public static Material DIAMOND_DROP;
    public static int DIAMOND_MAX_DROP_COUNT;
    public static long DIAMOND_RESPAWN_TIME;
    
    public static Material EMERALD_ORE = Material.EMERALD_ORE;
    public static Material EMERALD_DROP;
    public static int EMERALD_MAX_DROP_COUNT;
    public static long EMERALD_RESPAWN_TIME;
    
    public static List<Material> RANDOM_DROP = Arrays.asList(COAL_DROP, IRON_DROP, GOLD_DROP, DIAMOND_DROP, EMERALD_DROP);
    public static int RANDOM_MAX_DROP_COUNT;
    public static long RANDOM_RESPAWN_TIME;
    
    public static void load() {
        cfg = EternalOre.getSettings();
    
        WAND_MATERIAL = getMaterial("wand-material");
        PLACEHOLDER_BLOCK = getMaterial("placeholder-block");
        
        COAL_DROP = getMaterial("ores.coal-ore.drop");
        COAL_MAX_DROP_COUNT = cfg.getInt("ores.coal-ore.max-drop-count", 1);
        COAL_RESPAWN_TIME = cfg.getInt("ores.coal-ore.respawn-time");
    
        IRON_DROP = getMaterial("ores.iron-ore.drop");
        IRON_MAX_DROP_COUNT = cfg.getInt("ores.iron-ore.max-drop-count", 1);
        IRON_RESPAWN_TIME = cfg.getInt("ores.iron-ore.respawn-time");
    
        GOLD_DROP = getMaterial("ores.gold-ore.drop");
        GOLD_MAX_DROP_COUNT = cfg.getInt("ores.gold-ore.max-drop-count", 1);
        GOLD_RESPAWN_TIME = cfg.getInt("ores.gold-ore.respawn-time");
    
        DIAMOND_DROP = getMaterial("ores.diamond-ore.drop");
        DIAMOND_MAX_DROP_COUNT = cfg.getInt("ores.diamond-ore.max-drop-count", 1);
        DIAMOND_RESPAWN_TIME = cfg.getInt("ores.diamond-ore.respawn-time");
    
        EMERALD_DROP = getMaterial("ores.emerald-ore.drop");
        EMERALD_MAX_DROP_COUNT = cfg.getInt("ores.emerald-ore.max-drop-count", 1);
        EMERALD_RESPAWN_TIME = cfg.getInt("ores.emerald-ore.respawn-time");
    
        RANDOM_MAX_DROP_COUNT = cfg.getInt("ores.random-ore.max-drop-count", 10);
        RANDOM_RESPAWN_TIME = cfg.getInt("ores.random-ore.respawn-time");
    }
    
    public static void save() {
        cfg = EternalOre.getSettings();
        
        cfg.saveConfig();
    }
    
    private static Material getMaterial(String path) {
        String fixedName = Objects.requireNonNull(cfg.getString(path)).toUpperCase().replace(" ", "_");
        
        try {
            return Material.valueOf(fixedName);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("The material value listed for an item is NOT a valid material!");
        }
    }
    
}
