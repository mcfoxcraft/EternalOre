package com.nannerss.eternalore;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nannerss.bananalib.BananaLib;
import com.nannerss.bananalib.config.ConfigManager;
import com.nannerss.bananalib.messages.Console;
import com.nannerss.bananalib.utils.Registrar;
import com.nannerss.eternalore.commands.EternalOreCommand;
import com.nannerss.eternalore.listeners.JoinListener;
import com.nannerss.eternalore.utils.Metrics;
import com.nannerss.eternalore.data.Ores;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.listeners.OreMineListener;
import com.nannerss.eternalore.listeners.WandListener;
import com.nannerss.eternalore.tasks.OreRegenTask;
import com.nannerss.eternalore.utils.Updater;

import lombok.Getter;
import net.md_5.bungee.api.chat.TextComponent;

public class EternalOre extends JavaPlugin {
    
    @Getter
    private static EternalOre instance;
    
    @Getter
    private static ConfigManager settings;
    
    @Getter
    private static ConfigManager ores;
    
    @Getter
    private static final Cache<String, Ores> oresCache = CacheBuilder.newBuilder().maximumSize(10000).build();
    
    @Override
    public void onEnable() {
        instance = this;
        BananaLib.setInstance(this);
        
        settings = new ConfigManager("settings.yml", true);
        Settings.load();
        ores = new ConfigManager("ores.dat", false);
    
        Registrar.registerCommand(new EternalOreCommand());
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new OreMineListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    
        ConfigurationSection section = ores.getConfigurationSection("ores");
        if (section == null) {
            section = ores.createSection("ores");
            ores.saveConfig();
        }
        
        for (String key : section.getKeys(false)) {
            getCache(key);
        }
        
        new OreRegenTask().runTaskTimer(this, 200, 20);
    
        new Updater(68180) {
        
            @Override
            public void onUpdateAvailable() {
                Console.log(TextComponent.toPlainText(getUpdateMessage()));
            }
        
        }.runTaskAsynchronously(this);
    
        Metrics metrics = new Metrics(this);
    }
    
    @Override
    public void onDisable() {
        instance = null;
        
        for (final Ores cache : oresCache.asMap().values()) {
            cache.save();
        }
        
        oresCache.invalidateAll();
        
        getServer().getScheduler().cancelTasks(this);
    }
    
    public static Ores getCache(String id) {
        Ores cache = oresCache.getIfPresent(id);
        
        if (cache == null) {
            cache = new Ores(id);
            
            oresCache.put(id, cache);
        }
        
        return cache;
    }
    
}
