package com.nannerss.eternalore.commands;

import java.util.Arrays;
import java.util.Random;

import com.nannerss.eternalore.lib.messages.Messages;
import com.nannerss.eternalore.lib.particles.Particles;
import com.nannerss.eternalore.lib.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.items.WandItem;

public class EternalOreCommand extends Command {
    
    private static Random r = new Random();
    
    public EternalOreCommand() {
        super("eternalore");
        
        setDescription("The base command for EternalOre!");
        setAliases(Arrays.asList("eo", "eternalo", "eternalores"));
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.colorize("&cYou must be a player to use this command!"));
            return false;
        }
        
        Player p = (Player) sender;
        
        if (args.length < 1) {
            Messages.sendMessage(p, "&dRunning &d&lEternalOre &dv" + EternalOre.getInstance().getDescription().getVersion() + ".");
            return false;
        }
        
        String parameter = args[0];
        
        if ("help".equalsIgnoreCase(parameter)) {
            if (!p.hasPermission("eternalore.help")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return false;
            }
            
            Messages.sendMessage(p, "",
                    "     &5&lShowing help for EternalOre...",
                    "",
                    "&d/eternalore &5help &8- &7Displays this help page.",
                    "&d/eternalore &5respawn [all] &8- &7Respawns all ores ore just the one you're looking at.",
                    "&d/eternalore &5reload &8- &7Reloads the settings config.",
                    "&d/eternalore &5wand &8- &7Gives you the Eternal Ore Wand.",
                    "");
        } else if ("respawn".equalsIgnoreCase(parameter)) {
            if (!p.hasPermission("eternalore.respawn")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return false;
            }
            
            if (args.length < 2) {
                Block block = p.getTargetBlock(null, 5);
                
                for (Ore cache : EternalOre.getOresCache().asMap().values()) {
                    if (block.getLocation().getWorld() == cache.getLocation().getWorld() && block.getLocation().getBlockX() == cache.getLocation().getBlockX() && block.getLocation().getBlockY() == cache.getLocation().getBlockY() && block.getLocation().getBlockZ() == cache.getLocation().getBlockZ()) {
                        if (cache.getLocation().getBlock().getType() == Settings.PLACEHOLDER_BLOCK || !Settings.ORES.contains(cache.getLocation().getBlock().getType())) {
                            switch (cache.getType()) {
                                case "STONE":
                                    cache.getLocation().getBlock().setType(Material.STONE);
                                    break;
                                case "COBBLESTONE":
                                    cache.getLocation().getBlock().setType(Material.COBBLESTONE);
                                    break;
                                case "QUARTZ_ORE":
                                    cache.getLocation().getBlock().setType(Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")? Material.valueOf("NETHER_QUARTZ_ORE") : Material.valueOf("QUARTZ_ORE"));
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
                                    Material ore = Settings.ORES.get(r.nextInt(5));
                                    
                                    cache.getLocation().getBlock().setType(ore);
                                    break;
                            }
                            
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                if (cache.getLocation().distance(onlinePlayer.getLocation()) <= 100) {
//                                    if (!Bukkit.getVersion().contains("1.13") && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
//                                        Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
//                                    } else {
//                                        Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getBlockData());
//                                    }
                                    p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().createBlockData());
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                                    } else {
                                        p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                                    }
                                }
                            }
                            
                            Messages.sendMessage(p, "&dRespawned ore at " + block.getLocation().getBlockX() + ", " + block.getLocation().getBlockY() + ", " + block.getLocation().getBlockZ() + "!");
                            return false;
                        } else {
                            Messages.sendMessage(p, "&cThat is already spawned in!");
                            return false;
                        }
                    }
                }
    
                Messages.sendMessage(p, "&cThe block you are looking at is not an ore!");
            } else {
                String respawnParameter = args[1];
                
                if ("all".equalsIgnoreCase(respawnParameter)) {
                    for (Ore cache : EternalOre.getOresCache().asMap().values()) {
                        if (cache.getLocation().getBlock().getType() == Settings.PLACEHOLDER_BLOCK || !Settings.ORES.contains(cache.getLocation().getBlock().getType())) {
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
                                    Material ore = Settings.ORES.get(r.nextInt(5));
                                    
                                    cache.getLocation().getBlock().setType(ore);
                                    break;
                            }
                            
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                if (cache.getLocation().distance(onlinePlayer.getLocation()) <= 100) {
//                                    if (!Bukkit.getVersion().contains("1.13") && !Bukkit.getVersion().contains("1.14") && !Bukkit.getVersion().contains("1.15")) {
//                                        Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getType().getId());
//                                    } else {
//                                        Particles.spawnParticle(p, "BLOCK_CRACK", cache.getLocation().getBlockX() + 0.5F, cache.getLocation().getBlockY() + 0.5F, cache.getLocation().getBlockZ() + 0.5F, 0.25F, 0.25F, 0.25F, 0, 100, cache.getLocation().getBlock().getBlockData());
//                                    }
                                    p.spawnParticle(Particle.BLOCK_CRACK, cache.getLocation().add(0.5, 0.5, 0.5), 100, cache.getLocation().getBlock().getType().createBlockData());
                                    if (Bukkit.getVersion().contains("1.8")) {
                                        p.playSound(cache.getLocation(), Sound.valueOf("DIG_STONE"), 1F, 1F);
                                    } else {
                                        p.playSound(cache.getLocation(), Sound.valueOf("BLOCK_STONE_BREAK"), 1F, 1F);
                                    }
                                }
                            }
                        }
                    }
                    
                    Messages.sendMessage(p, "&dRespawned all ores!");
                    return false;
                }
                
                Messages.sendMessage(p, "&cUsage: /eternalore respawn [all]");
            }
        } else if ("reload".equalsIgnoreCase(parameter)) {
            if (!p.hasPermission("eternalore.reload")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return false;
            }
    
            EternalOre.getSettings().loadConfig();
            Settings.load();
    
            WandItem.getItem().setType(Settings.WAND_MATERIAL);
    
            Messages.sendMessage(p, "&dSuccessfully reloaded the config!");
        } else if ("wand".equalsIgnoreCase(parameter)) {
            if (!p.hasPermission("eternalore.wand.give")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return false;
            }
            
            for (ItemStack item : p.getInventory().getContents()) {
                if (item == null) {
                    Messages.sendMessage(p, "&dHere's a wand!");
                    p.getInventory().addItem(WandItem.getItem());
                    return false;
                }
            }
            
            Messages.sendMessage(p, "&cYour inventory is full! Free up some space first!");
        }
        
        return false;
    }
    
}
