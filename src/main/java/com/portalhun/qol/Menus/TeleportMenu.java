package com.portalhun.qol.Menus;

import com.portalhun.qol.Items.CreateWarpItem;
import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TeleportMenu {
    public static String Name = ChatColor.LIGHT_PURPLE+"Teleporter";
    public static void Open(Player p){
        Inventory inv = Bukkit.createInventory(p, 54, Name);
        List<Warp> w = WarpStorage.getAllWarps();
        for (Warp warp : w) {
            Location l = warp.getLocation();
            ItemStack item = new ItemStack(warp.Item,1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+warp.Name);
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.RED+"Coordinates:");
            lore.add(ChatColor.RED+"X: "+(int)Math.floor(l.getX())+" Y: "+(int)Math.floor(l.getY())+ " Z: "+(int)Math.floor(l.getZ()));
            lore.add(ChatColor.RED+l.getWorld().getName());
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.addItem(item);
        }
        inv.setItem(53, CreateWarpItem.get());
        p.openInventory(inv);
    }
}
