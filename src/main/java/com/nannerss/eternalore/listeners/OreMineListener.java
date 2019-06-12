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
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;

public class OreMineListener implements Listener {
    
    private static Random r = new Random();
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        
        if (p.getGameMode() == GameMode.SURVIVAL) {
            ConfigManager cfg = EternalOre.getOres();
            
            for (Ore cache : EternalOre.getOresCache().asMap().values()) {
                if (e.getBlock().getLocation().getWorld() == cache.getLocation().getWorld() && e.getBlock().getLocation().getBlockX() == cache.getLocation().getBlockX() && e.getBlock().getLocation().getBlockY() == cache.getLocation().getBlockY() && e.getBlock().getLocation().getBlockZ() == cache.getLocation().getBlockZ()) {
                    if (e.getBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                        e.setCancelled(true);
                        return;
                    }
    
                    Material drop;
    
                    if (e.getBlock().getType() == Material.STONE) {
                        drop = Settings.STONE_DROP;
                    } else if (e.getBlock().getType() == Material.COBBLESTONE) {
                        drop = Settings.COBBLESTONE_DROP;
                    } else if (e.getBlock().getType() == (Bukkit.getVersion().contains("1.8") ? Material.valueOf("QUARTZ_ORE") : Material.valueOf("NETHER_QUARTZ_ORE"))) {
                        drop = Settings.QUARTZ_DROP;
                    } else if (e.getBlock().getType() == Material.COAL_ORE) {
                        drop = Settings.COAL_DROP;
                    } else if (e.getBlock().getType() == Material.IRON_ORE) {
                        drop = Settings.IRON_DROP;
                    } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                        drop = Settings.GOLD_DROP;
                    } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                        drop = Settings.LAPIS_DROP;
                    } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                        drop = Settings.REDSTONE_DROP;
                    } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                        drop = Settings.DIAMOND_DROP;
                    } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                        drop = Settings.EMERALD_DROP;
                    } else {
                        drop = Material.AIR;
                    }
                    
                    e.getBlock().setType(Settings.PLACEHOLDER_BLOCK);
    
                    switch (cache.getType()) {
                        case "STONE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.STONE_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.STONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.STONE_DROP)).getAmount() != Settings.STONE_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.STONE_DROP, r.nextInt(Settings.STONE_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.STONE_DROP, r.nextInt(Settings.STONE_MAX_DROP_COUNT) + 1));
                
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
            
                                p.getInventory().addItem(new ItemStack(Settings.STONE_DROP, r.nextInt(Settings.STONE_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "COBBLESTONE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.COBBLESTONE_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.COBBLESTONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.COBBLESTONE_DROP)).getAmount() != Settings.COBBLESTONE_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.COBBLESTONE_DROP, r.nextInt(Settings.COBBLESTONE_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.COBBLESTONE_DROP, r.nextInt(Settings.COBBLESTONE_MAX_DROP_COUNT) + 1));
                
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
            
                                p.getInventory().addItem(new ItemStack(Settings.COBBLESTONE_DROP, r.nextInt(Settings.COBBLESTONE_MAX_DROP_COUNT) + 1));
                            }
                            break;
                        case "QUARTZ_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.QUARTZ_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.QUARTZ_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.QUARTZ_DROP)).getAmount() != Settings.QUARTZ_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.QUARTZ_DROP, r.nextInt(Settings.QUARTZ_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.QUARTZ_DROP, r.nextInt(Settings.QUARTZ_MAX_DROP_COUNT) + 1));
                
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
            
                                p.getInventory().addItem(new ItemStack(Settings.QUARTZ_DROP, r.nextInt(Settings.QUARTZ_MAX_DROP_COUNT) + 1));
                            }
                            break;
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
                        case "LAPIS_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.LAPIS_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.LAPIS_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.LAPIS_DROP)).getAmount() != Settings.LAPIS_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    if (Settings.LAPIS_DROP == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                        p.getInventory().addItem(new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1, (short) 4));
                                    } else {
                                        p.getInventory().addItem(new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1));
                                    }
                                } else {
                                    if (Settings.LAPIS_DROP == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                        p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1, (short) 4));
                                    } else {
                                        p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1));
                                    }
                
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
    
                                if (Settings.LAPIS_DROP == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                    p.getInventory().addItem(new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1, (short) 4));
                                } else {
                                    p.getInventory().addItem(new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1));
                                }
                            }
                            break;
                        case "REDSTONE_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.REDSTONE_RESPAWN_TIME * 60 * 1000);
                            cache.save();
        
                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.REDSTONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.REDSTONE_DROP)).getAmount() != Settings.REDSTONE_DROP.getMaxStackSize()) {
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1F, 1F);
                                    } else {
                                        p.playSound(p.getLocation(), Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP"), 1F, 1F);
                                    }
                
                                    p.getInventory().addItem(new ItemStack(Settings.REDSTONE_DROP, r.nextInt(Settings.REDSTONE_MAX_DROP_COUNT) + 1));
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Settings.REDSTONE_DROP, r.nextInt(Settings.REDSTONE_MAX_DROP_COUNT) + 1));
                
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
            
                                p.getInventory().addItem(new ItemStack(Settings.REDSTONE_DROP, r.nextInt(Settings.REDSTONE_MAX_DROP_COUNT) + 1));
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
    
                                    if (drop == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                        p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1, (short) 4));
                                    } else {
                                        p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                                    }
                                } else {
                                    if (drop == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                        p.getWorld().dropItem(p.getLocation(), new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1, (short) 4));
                                    } else {
                                        p.getWorld().dropItem(p.getLocation(), new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                                    }
                
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
    
                                if (drop == Material.valueOf("INK_SACK") && Bukkit.getVersion().contains("1.8")) {
                                    p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1, (short) 4));
                                } else {
                                    p.getInventory().addItem(new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1));
                                }
                            }
                            break;
                    }
    
                    e.setCancelled(true);
                }
            }
        }
        
    }
}
