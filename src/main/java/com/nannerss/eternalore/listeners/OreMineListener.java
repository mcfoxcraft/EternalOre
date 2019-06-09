package com.nannerss.eternalore.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.nannerss.bananalib.config.ConfigManager;
import com.nannerss.bananalib.messages.Messages;
import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ores;
import com.nannerss.eternalore.data.Settings;

public class OreMineListener implements Listener {
    
    private static Random r = new Random();
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        
        if (p.getGameMode() == GameMode.SURVIVAL) {
            ConfigManager cfg = EternalOre.getOres();
            
            for (Ores cache : EternalOre.getOresCache().asMap().values()) {
                if (e.getBlock().getLocation().getWorld() == cache.getLocation().getWorld() && e.getBlock().getLocation().getBlockX() == cache.getLocation().getBlockX() && e.getBlock().getLocation().getBlockY() == cache.getLocation().getBlockY() && e.getBlock().getLocation().getBlockZ() == cache.getLocation().getBlockZ()) {
                    if (e.getBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                        e.setCancelled(true);
                        return;
                    }
    
                    Material drop;
    
                    if (e.getBlock().getType() == Material.COAL_ORE) {
                        drop = Settings.COAL_DROP;
                    } else if (e.getBlock().getType() == Material.IRON_ORE) {
                        drop = Settings.IRON_DROP;
                    } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                        drop = Settings.GOLD_DROP;
                    } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                        drop = Settings.DIAMOND_DROP;
                    } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                        drop = Settings.EMERALD_DROP;
                    } else {
                        drop = Material.AIR;
                    }
                    
                    e.getBlock().setType(Settings.PLACEHOLDER_BLOCK);
    
                    switch (cache.getType()) {
                        case "COAL_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.COAL_RESPAWN_TIME * 60 * 1000);
                            cache.save();
            
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.COAL_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.COAL_DROP)).getAmount() != Settings.COAL_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                    
                                    p.getInventory().addItem(new ItemStack(Settings.COAL_DROP, r.nextInt(Settings.COAL_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.COAL_DROP, r.nextInt(Settings.COAL_MAX_DROP_COUNT) + 1));
                    
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                    
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
                
                                p.getInventory().addItem(new ItemStack(Settings.COAL_DROP, r.nextInt(Settings.COAL_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "IRON_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.IRON_RESPAWN_TIME * 60 * 1000);
                            cache.save();
            
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.IRON_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.IRON_DROP)).getAmount() != Settings.IRON_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                    
                                    p.getInventory().addItem(new ItemStack(Settings.IRON_DROP, r.nextInt(Settings.IRON_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.IRON_DROP, r.nextInt(Settings.IRON_MAX_DROP_COUNT) + 1));
                    
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                    
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
                
                                p.getInventory().addItem(new ItemStack(Settings.IRON_DROP, r.nextInt(Settings.IRON_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "GOLD_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.GOLD_RESPAWN_TIME * 60 * 1000);
                            cache.save();
            
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.GOLD_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.GOLD_DROP)).getAmount() != Settings.GOLD_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                    
                                    p.getInventory().addItem(new ItemStack(Settings.GOLD_DROP, r.nextInt(Settings.GOLD_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.GOLD_DROP, r.nextInt(Settings.GOLD_MAX_DROP_COUNT) + 1));
                    
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                    
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
                
                                p.getInventory().addItem(new ItemStack(Settings.GOLD_DROP, r.nextInt(Settings.GOLD_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "DIAMOND_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.DIAMOND_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.DIAMOND_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.DIAMOND_DROP)).getAmount() != Settings.DIAMOND_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.DIAMOND_DROP, r.nextInt(Settings.DIAMOND_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.DIAMOND_DROP, r.nextInt(Settings.DIAMOND_MAX_DROP_COUNT) + 1));
                
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
            
                                p.getInventory().addItem(new ItemStack(Settings.DIAMOND_DROP, r.nextInt(Settings.DIAMOND_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "EMERALD_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.EMERALD_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.EMERALD_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.EMERALD_DROP)).getAmount() != Settings.EMERALD_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.EMERALD_DROP, r.nextInt(Settings.EMERALD_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.EMERALD_DROP, r.nextInt(Settings.EMERALD_MAX_DROP_COUNT) + 1));
                
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
            
                                p.getInventory().addItem(new ItemStack(Settings.EMERALD_DROP, r.nextInt(Settings.EMERALD_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "RANDOM_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.RANDOM_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(drop) != -1 && p.getInventory().getItem(p.getInventory().first(drop)).getAmount() != drop.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("NOTE_BASS"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_BASS"), 1F, 1F);
                                    }
                
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                if (Bukkit.getVersion().contains("1.8")) {
                                    p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                } else {
                                    p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                }
            
                                p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                            }
                            break;
                    }
    
                    e.setCancelled(true);
                }
            }
        }
        
    }
}
