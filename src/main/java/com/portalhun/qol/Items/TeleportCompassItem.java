package com.portalhun.qol.Items;

import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TeleportCompassItem {

    public static ItemStack get(QOL pl){
        ItemStack a = new ItemStack(Material.COMPASS);
        ItemMeta meta = a.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Teleporter");
        meta.addEnchant(Enchantment.CHANNELING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "Teleports you across worlds.");
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(pl, "teleporter");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        a.setItemMeta(meta);
        return a;
    }
}
