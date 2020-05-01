package com.nannerss.eternalore.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.nannerss.eternalore.lib.config.ConfigManager;
import com.nannerss.eternalore.EternalOre;

public class  Settings {
    
    private static ConfigManager cfg;
    
    public static Material WAND_MATERIAL;
    public static Material PLACEHOLDER_BLOCK;
    public static List<Material> ORES = Arrays.asList(Material.STONE, Material.COBBLESTONE, Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") ? Material.valueOf("NETHER_QUARTZ_ORE") : Material.valueOf("QUARTZ_ORE"), Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE);
    
    public static Material STONE_DROP;
    public static int STONE_MAX_DROP_COUNT;
    public static long STONE_RESPAWN_TIME;
    
    public static Material COBBLESTONE_DROP;
    public static int COBBLESTONE_MAX_DROP_COUNT;
    public static long COBBLESTONE_RESPAWN_TIME;
    
    public static Material QUARTZ_DROP;
    public static int QUARTZ_MAX_DROP_COUNT;
    public static long QUARTZ_RESPAWN_TIME;
    
    public static Material COAL_DROP;
    public static int COAL_MAX_DROP_COUNT;
    public static long COAL_RESPAWN_TIME;
    
    public static Material IRON_DROP;
    public static int IRON_MAX_DROP_COUNT;
    public static long IRON_RESPAWN_TIME;
    
    public static Material GOLD_DROP;
    public static int GOLD_MAX_DROP_COUNT;
    public static long GOLD_RESPAWN_TIME;
    
    public static Material LAPIS_DROP;
    public static int LAPIS_MAX_DROP_COUNT;
    public static long LAPIS_RESPAWN_TIME;
    
    public static Material REDSTONE_DROP;
    public static int REDSTONE_MAX_DROP_COUNT;
    public static long REDSTONE_RESPAWN_TIME;
    
    public static Material DIAMOND_DROP;
    public static int DIAMOND_MAX_DROP_COUNT;
    public static long DIAMOND_RESPAWN_TIME;
    
    public static Material EMERALD_DROP;
    public static int EMERALD_MAX_DROP_COUNT;
    public static long EMERALD_RESPAWN_TIME;
    
    public static List<Material> RANDOM_ORES;
    public static int RANDOM_MAX_DROP_COUNT;
    public static long RANDOM_RESPAWN_TIME;
    
    public static void load() {
        cfg = EternalOre.getSettings();
        
        updateConfig(cfg);
        
        WAND_MATERIAL = getMaterial("wand-material");
        PLACEHOLDER_BLOCK = getMaterial("placeholder-block");
        
        STONE_DROP = getMaterial("ores.stone.drop");
        STONE_MAX_DROP_COUNT = cfg.getInt("ores.stone.max-drop-count");
        STONE_RESPAWN_TIME = cfg.getInt("ores.stone.respawn-time");
        
        COBBLESTONE_DROP = getMaterial("ores.cobblestone.drop");
        COBBLESTONE_MAX_DROP_COUNT = cfg.getInt("ores.cobblestone.max-drop-count");
        COBBLESTONE_RESPAWN_TIME = cfg.getInt("ores.cobblestone.respawn-time");
        
        QUARTZ_DROP = getMaterial("ores.quartz-ore.drop");
        QUARTZ_MAX_DROP_COUNT = cfg.getInt("ores.quartz-ore.max-drop-count");
        QUARTZ_RESPAWN_TIME = cfg.getInt("ores.quartz-ore.respawn-time");
        
        COAL_DROP = getMaterial("ores.coal-ore.drop");
        COAL_MAX_DROP_COUNT = cfg.getInt("ores.coal-ore.max-drop-count");
        COAL_RESPAWN_TIME = cfg.getInt("ores.coal-ore.respawn-time");
        
        IRON_DROP = getMaterial("ores.iron-ore.drop");
        IRON_MAX_DROP_COUNT = cfg.getInt("ores.iron-ore.max-drop-count");
        IRON_RESPAWN_TIME = cfg.getInt("ores.iron-ore.respawn-time");
        
        GOLD_DROP = getMaterial("ores.gold-ore.drop");
        GOLD_MAX_DROP_COUNT = cfg.getInt("ores.gold-ore.max-drop-count");
        GOLD_RESPAWN_TIME = cfg.getInt("ores.gold-ore.respawn-time");
        
        String LAPIS_DROP_NAME = cfg.getString("ores.lapis-ore.drop");
        if ("LAPIS_LAZULI".equals(LAPIS_DROP_NAME.toUpperCase())) {
            if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
                LAPIS_DROP = Material.valueOf("LAPIS_LAZULI");
            } else {
                LAPIS_DROP = Material.valueOf("INK_SACK");
            }
        } else {
            LAPIS_DROP = Material.valueOf(LAPIS_DROP_NAME);
        }
        LAPIS_MAX_DROP_COUNT = cfg.getInt("ores.lapis-ore.max-drop-count");
        LAPIS_RESPAWN_TIME = cfg.getInt("ores.lapis-ore.respawn-time");
        
        REDSTONE_DROP = getMaterial("ores.redstone-ore.drop");
        REDSTONE_MAX_DROP_COUNT = cfg.getInt("ores.redstone-ore.max-drop-count");
        REDSTONE_RESPAWN_TIME = cfg.getInt("ores.redstone-ore.respawn-time");
        
        DIAMOND_DROP = getMaterial("ores.diamond-ore.drop");
        DIAMOND_MAX_DROP_COUNT = cfg.getInt("ores.diamond-ore.max-drop-count");
        DIAMOND_RESPAWN_TIME = cfg.getInt("ores.diamond-ore.respawn-time");
        
        EMERALD_DROP = getMaterial("ores.emerald-ore.drop");
        EMERALD_MAX_DROP_COUNT = cfg.getInt("ores.emerald-ore.max-drop-count");
        EMERALD_RESPAWN_TIME = cfg.getInt("ores.emerald-ore.respawn-time");
        
        RANDOM_ORES = getMaterialList("ores.random-ore.ores");
        RANDOM_MAX_DROP_COUNT = cfg.getInt("ores.random-ore.max-drop-count");
        RANDOM_RESPAWN_TIME = cfg.getInt("ores.random-ore.respawn-time");
    }
    
    public void save() {
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
    
    private static List<Material> getMaterialList(String path) {
        final List<Material> list = new ArrayList<>();
        
        for (final String raw : cfg.getStringList(path)) {
            final String fixedName = raw.toUpperCase().replace(" ", "_");
            
            try {
                final Material mat;
    
                if ("NETHER_QUARTZ_ORE".equals(fixedName) || "QUARTZ_ORE".equals(fixedName)) {
                    if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
                        mat = Material.valueOf("NETHER_QUARTZ_ORE");
                    } else {
                        mat = Material.valueOf("QUARTZ_ORE");
                    }
                } else {
                    mat = Material.valueOf(fixedName);
                }
                
                if (mat != null) {
                    list.add(mat);
                }
            } catch (final IllegalArgumentException e) {
                throw new RuntimeException("Item " + raw + " is NOT a valid material!");
            }
        }
        
        return list;
    }
    
    private static void updateConfig(ConfigManager cfg) {
        if (cfg.getString("wand-material") == null) {
            cfg.set("wand-material", "BLAZE_ROD");
        }
    
        if (cfg.getString("placeholder-block") == null) {
            cfg.set("placeholder-block", "BEDROCK");
        }
    
        if (cfg.getConfigurationSection("ores.stone") == null) {
            cfg.set("ores.stone.drop", "STONE");
            cfg.set("ores.stone.max-drop-count", 1);
            cfg.set("ores.stone.respawn-time", 1);
        }
    
        if (cfg.getConfigurationSection("ores.cobblestone") == null) {
            cfg.set("ores.cobblestone.drop", "COBBLESTONE");
            cfg.set("ores.cobblestone.max-drop-count", 1);
            cfg.set("ores.cobblestone.respawn-time", 1);
        }
    
        if (cfg.getConfigurationSection("ores.quartz-ore") == null) {
            cfg.set("ores.quartz-ore.drop", "QUARTZ");
            cfg.set("ores.quartz-ore.max-drop-count", 1);
            cfg.set("ores.quartz-ore.respawn-time", 1);
        }
    
        if (cfg.getConfigurationSection("ores.coal-ore") == null) {
            cfg.set("ores.coal-ore.drop", "COAL");
            cfg.set("ores.coal-ore.max-drop-count", 1);
            cfg.set("ores.coal-ore.respawn-time", 1);
        }
    
        if (cfg.getConfigurationSection("ores.iron-ore") == null) {
            cfg.set("ores.iron-ore.drop", "IRON_INGOT");
            cfg.set("ores.iron-ore.max-drop-count", 1);
            cfg.set("ores.iron-ore.respawn-time", 2);
        }
    
        if (cfg.getConfigurationSection("ores.gold-ore") == null) {
            cfg.set("ores.gold-ore.drop", "GOLD_INGOT");
            cfg.set("ores.gold-ore.max-drop-count", 1);
            cfg.set("ores.gold-ore.respawn-time", 5);
        }
    
        if (cfg.getConfigurationSection("ores.lapis-ore") == null) {
            cfg.set("ores.lapis-ore.drop", "LAPIS_LAZULI");
            cfg.set("ores.lapis-ore.max-drop-count", 1);
            cfg.set("ores.lapis-ore.respawn-time", 5);
        }
    
        if (cfg.getConfigurationSection("ores.redstone-ore") == null) {
            cfg.set("ores.redstone-ore.drop", "REDSTONE");
            cfg.set("ores.redstone-ore.max-drop-count", 1);
            cfg.set("ores.redstone-ore.respawn-time", 5);
        }
    
        if (cfg.getConfigurationSection("ores.diamond-ore") == null) {
            cfg.set("ores.diamond-ore.drop", "DIAMOND");
            cfg.set("ores.diamond-ore.max-drop-count", 1);
            cfg.set("ores.diamond-ore.respawn-time", 7);
        }
    
        if (cfg.getConfigurationSection("ores.emerald-ore") == null) {
            cfg.set("ores.emerald-ore.drop", "EMERALD");
            cfg.set("ores.emerald-ore.max-drop-count", 1);
            cfg.set("ores.emerald-ore.respawn-time", 10);
        }
    
        if (cfg.getConfigurationSection("ores.random-ore") == null) {
            cfg.set("ores.random-ore.ores", Arrays.asList("COAL_ORE", "IRON_ORE", "GOLD_ORE", "DIAMOND_ORE", "EMERALD_ORE", "STONE", "COBBLESTONE", "NETHER_QUARTZ_ORE", "LAPIS_ORE", "REDSTONE_ORE"));
            cfg.set("ores.random-ore.max-drop-count", 10);
            cfg.set("ores.random-ore.respawn-time", 15);
        }
        
        cfg.saveConfig();
    }
    
}
