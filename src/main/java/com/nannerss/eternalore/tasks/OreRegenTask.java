package com.nannerss.eternalore.tasks;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.nannerss.bananalib.particles.Particles;
import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ores;
import com.nannerss.eternalore.data.Settings;

public class OreRegenTask extends BukkitRunnable {
    
    private static Random r = new Random();
    
    @Override
    public void run() {
        for (Ores cache : EternalOre.getOresCache().asMap().values()) {
            if (cache.getLocation().getBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                if (cache.getRespawnTime() <= System.currentTimeMillis()) {
                    switch (cache.getType()) {
                        case "COAL_ORE":
                            cache.getLocation().getBlock().setType(Material.COAL_ORE);
                            break;
                        case "IRON_ORE":
                            cache.getLocation().getBlock().setType(Material.IRON_ORE);
                            break;
                        case "GOLD_ORE":
                            cache.getLocation().getBlock().setType(Material.GOLD_ORE);
                            break;
                        case "DIAMOND_ORE":
                            cache.getLocation().getBlock().setType(Material.DIAMOND_ORE);
                            break;
                        case "EMERALD_ORE":
                            cache.getLocation().getBlock().setType(Material.EMERALD_ORE);
                            break;
                        case "RANDOM_ORE":
                            Material ore = Settings.ORES.get(r.nextInt(5));
            
                            cache.getLocation().getBlock().setType(ore);
                            break;
                    }
    
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (cache.getLocation().distance(p.getLocation()) <= 100) {
                            if (Bukkit.getVersion().contains("1.8")) {
                                p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                            } else {
                                p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                            }
            
                            Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
                        }
                    }
                }
            } else if (Settings.ORES.contains(cache.getLocation().getBlock().getType()) || cache.getLocation().getBlock().getType() == Material.REDSTONE_BLOCK) {
                if (cache.getType().equals("RANDOM_ORE")) {
                    Material ore = Settings.ORES.get(r.nextInt(5));
    
                    cache.getLocation().getBlock().setType(ore);
                    
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (cache.getLocation().distance(p.getLocation()) <= 50) {
                            if (Bukkit.getVersion().contains("1.8")) {
                                p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                            } else {
                                p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                            }
            
                            Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
                        }
                    }
                }
            }
        }
    }
    
}
