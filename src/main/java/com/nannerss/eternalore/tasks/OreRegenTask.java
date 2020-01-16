package com.nannerss.eternalore.tasks;

import java.util.Random;

import org.apache.commons.lang.enums.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.nannerss.eternalore.lib.particles.Particles;
import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;

public class OreRegenTask extends BukkitRunnable {
    
    private static Random r = new Random();
    
    @Override
    public void run() {
        for (Ore cache : EternalOre.getOresCache().asMap().values()) {
            if (cache.getLocation().getBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                if (cache.getRespawnTime() <= System.currentTimeMillis()) {
                    switch (cache.getType()) {
                        case "STONE":
                            cache.getLocation().getBlock().setType(Material.STONE);
                            break;
                        case "COBBLESTONE":
                            cache.getLocation().getBlock().setType(Material.COBBLESTONE);
                            break;
                        case "QUARTZ_ORE":
                            cache.getLocation().getBlock().setType(Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") ? Material.valueOf("NETHER_QUARTZ_ORE") : Material.valueOf("QUARTZ_ORE"));
                            break;
                        case "COAL_ORE":
                            cache.getLocation().getBlock().setType(Material.COAL_ORE);
                            break;
                        case "IRON_ORE":
                            cache.getLocation().getBlock().setType(Material.IRON_ORE);
                            break;
                        case "GOLD_ORE":
                            cache.getLocation().getBlock().setType(Material.GOLD_ORE);
                            break;
                        case "LAPIS_ORE":
                            cache.getLocation().getBlock().setType(Material.LAPIS_ORE);
                            break;
                        case "REDSTONE_ORE":
                            cache.getLocation().getBlock().setType(Material.REDSTONE_ORE);
                            break;
                        case "DIAMOND_ORE":
                            cache.getLocation().getBlock().setType(Material.DIAMOND_ORE);
                            break;
                        case "EMERALD_ORE":
                            cache.getLocation().getBlock().setType(Material.EMERALD_ORE);
                            break;
                        case "RANDOM_ORE":
                            Material ore = Settings.RANDOM_ORES.get(r.nextInt(Settings.RANDOM_ORES.size()));
            
                            cache.getLocation().getBlock().setType(ore);
                            break;
                    }
    
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.getWorld().equals(cache.getLocation().getWorld())) {
                            continue;
                        }
                        
                        if (cache.getLocation().distance(p.getLocation()) <= 100) {
//                            if (!Bukkit.getVersion().contains("1.13") && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
//                                Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
//                            } else {
//                                Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getBlockData());
//                            }
                            p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().getData());
                            if (Bukkit.getVersion().contains("1.8")) {
                                p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                            } else {
                                p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                            }
                        }
                    }
                }
            } else if (Settings.ORES.contains(cache.getLocation().getBlock().getType()) || cache.getLocation().getBlock().getType() == Material.REDSTONE_BLOCK) {
                if (cache.getType().equals("RANDOM_ORE")) {
                    Material ore = Settings.RANDOM_ORES.get(r.nextInt(Settings.RANDOM_ORES.size()));
    
                    cache.getLocation().getBlock().setType(ore);
                    
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.getWorld().equals(cache.getLocation().getWorld())) {
                            continue;
                        }
                        
                        if (cache.getLocation().distance(p.getLocation()) <= 50) {
//                            if (!Bukkit.getVersion().contains("1.13") && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
//                                p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().getData());
////                                Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
//                            } else {
//                                p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().getData());
////                                Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getBlockData());
//                            }
//
                            p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().getData());

                            if (Bukkit.getVersion().contains("1.8")) {
                                p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                            } else {
                                p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                            }
                        }
                    }
                }
            }
        }
    }
    
}
