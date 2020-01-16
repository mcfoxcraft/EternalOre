package com.nannerss.eternalore.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nannerss.eternalore.lib.utils.Utils;
import com.nannerss.eternalore.data.Settings;

import lombok.Getter;

public class WandItem {
    
    public static final Material MATERIAL = Settings.WAND_MATERIAL;
    
    @Getter
    private static final ItemStack item;
    
    static {
        final ItemStack wand = new ItemStack(MATERIAL);
        final ItemMeta meta = wand.getItemMeta();
        
        meta.setDisplayName(Utils.colorize("&D&lEternal Ore Wand"));
        meta.setLore(Arrays.asList(
                Utils.colorize(""),
                Utils.colorize("&6&lLeft Click &fto set an ore!"),
                Utils.colorize("&6&lShift Click &fto set a random ore!"),
                Utils.colorize("&6&lRight Click &fto remove an ore!")
        ));
        
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        
        wand.setItemMeta(meta);
        item = wand;
    }
    
}
