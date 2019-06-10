package com.nannerss.eternalore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.scheduler.BukkitRunnable;

import com.nannerss.bananalib.messages.Component;
import com.nannerss.bananalib.utils.Utils;
import com.nannerss.eternalore.EternalOre;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public abstract class Updater extends BukkitRunnable {
    
    private static int projectId;
    private static String latest = "";
    
    public Updater(int projectId) {
        Updater.projectId = projectId;
    }
    
    public static boolean updateAvailable() {
        return !latest.equals(EternalOre.getInstance().getDescription().getVersion());
    }
    
    public static BaseComponent[] getPlayerUpdateMessage() {
        final EternalOre instance = EternalOre.getInstance();
        
        return Component.builder("")
                .append("\n")
                .append("\n")
                .append("               &5&l" + instance.getDescription().getName() + "\n")
                .append("\n")
                .append("&dA new version of " + instance.getDescription().getName() + " is available!\n")
                .append("&dYou are on &fv" + instance.getDescription().getVersion() + "&d, update to &fv" + getLatest() + " &dhere:\n")
                .append("\n")
                .append("               &5&l[DOWNLOAD]\n").onHover("&6&lClick &r&fto open download page!").onClick(Action.OPEN_URL, "https://spigotmc.org/resources/" + getProjectId())
                .append("\n")
                .append("").create();
    }
    
    public static String[] getConsoleUpdateMessage() {
        final EternalOre instance = EternalOre.getInstance();
        
        return new String[] {
                "",
                "",
                "               &5" + instance.getDescription().getName(),
                "",
                " &dA new version of " + instance.getDescription().getName() + " is available!",
                " &dYou are on &fv" + instance.getDescription().getVersion() + "&d, update to &fv" + getLatest() + " &dhere:",
                "",
                "   https://spigotmc.org/resources/" + getProjectId(),
                "",
                ""
        };
    }
    
    public static int getProjectId() {
        return projectId;
    }
    
    public static String getLatest() {
        return latest;
    }
    
    @Override
    public void run() {
        try {
            final URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectId);
            final URLConnection connection = url.openConnection();
            
            try (BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                latest = r.readLine();
            }
            
            if (updateAvailable()) {
                onUpdateAvailable();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public abstract void onUpdateAvailable();
    
}
