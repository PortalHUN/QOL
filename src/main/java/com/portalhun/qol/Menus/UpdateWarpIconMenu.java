package com.portalhun.qol.Menus;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class UpdateWarpIconMenu {
  public static String Name = ChatColor.DARK_PURPLE+"Place an item in the middle";
  public static void Open(Player p, Warp w, QOL pl){
    Inventory inv = Bukkit.createInventory(p, 54, Name);
    inv.setItem(4, w.createItem(p, pl, false));
    inv.setItem(40, makeGlassPane(true));
    for (int i = 0; i<54;i++){
      if(inv.getItem(i)==null && i!=31) inv.setItem(i, makeGlassPane(false));
    }
    p.openInventory(inv);
  }

  public static ItemStack makeGlassPane(boolean r){
    ItemStack i = new ItemStack(!r?Material.GRAY_STAINED_GLASS_PANE:Material.GREEN_STAINED_GLASS_PANE);
    ItemMeta meta = i.getItemMeta();
    meta.setDisplayName(r?ChatColor.GREEN+""+ChatColor.BOLD+"Click":" ");
    meta.addEnchant(Enchantment.LUCK,1,false);
    meta.addEnchant(Enchantment.OXYGEN,1,false);
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    if(r){
      List<String> lore = new ArrayList<>();
      lore.add(ChatColor.GREEN+"Place an item in the middle");
      lore.add(ChatColor.GREEN+"to change the Warp Icon");
      meta.setLore(lore);
    }
    i.setItemMeta(meta);
    return i;
  }
}
