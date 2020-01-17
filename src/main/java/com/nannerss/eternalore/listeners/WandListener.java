package com.nannerss.eternalore.listeners;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nannerss.eternalore.lib.config.ConfigManager;
import com.nannerss.eternalore.lib.messages.Messages;
import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.items.WandItem;

public class WandListener implements Listener {
    
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        
        if (!p.getItemInHand().hasItemMeta()) {
            return;
        }
        
        if (p.getItemInHand().getType().equals(WandItem.getItem().getType()) && p.getItemInHand().getItemMeta().getDisplayName().equals(WandItem.getItem().getItemMeta().getDisplayName())) {
            if (!p.hasPermission("eternalore.wand.use")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return;
            }
            
            if (e.getClickedBlock() == null) {
                return;
            }
            
            Location loc = e.getClickedBlock().getLocation();
            String id = loc.getWorld().getName() + "!" + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ();
            
            if (a == Action.LEFT_CLICK_BLOCK) {
                if (p.isSneaking()) {
                    if (e.getClickedBlock().getType() == Material.REDSTONE_BLOCK || Settings.ORES.contains(e.getClickedBlock().getType())) {
                        Ore cache = EternalOre.getCache(id);
    
                        cache.setType("RANDOM_ORE");
                        cache.setLocation(loc);
                        cache.setRespawnTime(1);
    
                        Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a random ore!");
                        
                        return;
                    }
    
                    Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
                } else {
                    if (Settings.ORES.contains(e.getClickedBlock().getType())) {
                        Ore cache = EternalOre.getCache(id);
                        
                        if (e.getClickedBlock().getType() == (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") ? Material.valueOf("NETHER_QUARTZ_ORE") : Material.valueOf("QUARTZ_ORE"))) {
                            cache.setType("QUARTZ_ORE");
                        } else {
                            cache.setType(e.getClickedBlock().getType().toString());
                        }
                        
                        cache.setLocation(loc);
                        cache.setRespawnTime(0);
                        
                        Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a respawnable ore!");
                        
                        return;
                    }
                    
                    Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
                }
            } else if (a == Action.RIGHT_CLICK_BLOCK) {
                if (Settings.ORES.contains(e.getClickedBlock().getType()) || e.getClickedBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                    Ore cache = EternalOre.getCache(id);
                    
                    ConfigManager cfg = EternalOre.getOres();
                    cfg.set("ores." + cache.getId(), null);
                    
                    EternalOre.getOresCache().invalidate(cache.getId());
                    
                    Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a normal ore!");
                    
                    return;
                }
                
                Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
            }
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        
        if (!p.getItemInHand().hasItemMeta()) {
            return;
        }
        
        if (p.getItemInHand().getType().equals(WandItem.getItem().getType()) && p.getItemInHand().getItemMeta().getDisplayName().equals(WandItem.getItem().getItemMeta().getDisplayName())) {
            e.setCancelled(true);
        }
    }
    
}
