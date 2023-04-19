package com.portalhun.qol.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TeleportCompassItem {
    public static ItemStack getTeleportCompass(){
        ItemStack a = new ItemStack(Material.COMPASS);
        ItemMeta meta = a.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Teleporter");
        meta.addEnchant(Enchantment.CHANNELING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "Teleports you across worlds.");
        meta.setLore(lore);
        a.setItemMeta(meta);

        return a;
    }
}
