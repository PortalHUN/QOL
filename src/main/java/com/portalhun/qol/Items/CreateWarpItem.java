package com.portalhun.qol.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreateWarpItem {
    public static ItemStack get(){
        ItemStack i = new ItemStack(Material.SEA_PICKLE);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"New Warp");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE+"Click here to add a new Warp");
        lore.add(ChatColor.RED+"Cost: 3 Levels");
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
