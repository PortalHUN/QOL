package com.portalhun.qol.Menus;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UpdateWarpMenu {
  public static String Name = ChatColor.DARK_PURPLE+"Update the warp";
  public static void Open(Player p, Warp w, QOL pl){
    Inventory inv = Bukkit.createInventory(p,45, Name);
    inv.setItem(13,w.createItem(p,pl, false));
    inv.setItem(33, DeleteWarpItem(w));
    inv.setItem(31, UpdateWarpNameItem(w));
    inv.setItem(29, ChangeWarpIconItem(w));
    p.openInventory(inv);
  }

  public static ItemStack DeleteWarpItem(Warp w){
    ItemStack item = new ItemStack(Material.BARRIER);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Delete "+w.Name);
    item.setItemMeta(meta);
    return item;
  }
  public static ItemStack UpdateWarpNameItem(Warp w){
    ItemStack item = new ItemStack(Material.OAK_SIGN);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Update "+w.Name);
    item.setItemMeta(meta);
    return item;
  }
  public static ItemStack ChangeWarpIconItem(Warp w){
    ItemStack item = new ItemStack(Material.LINGERING_POTION);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"Change "+w.Name+" Icon");
    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
    item.setItemMeta(meta);
    return item;
  }
}
