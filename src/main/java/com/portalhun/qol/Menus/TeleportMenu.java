package com.portalhun.qol.Menus;

import com.portalhun.qol.Items.CreateWarpItem;
import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TeleportMenu {
    public static String Name = ChatColor.DARK_PURPLE+"Teleporter";
    public static void Open(QOL pl, Player p){
        Inventory inv = Bukkit.createInventory(p, 54, Name);
        Location l = pl.getConfig().getLocation("spawn");
        if(l!=null){
          Warp spawn = new Warp("Spawn", l, Material.GRASS_BLOCK);
          inv.addItem(spawn.createItem(p,pl,true));
        }
        List<Warp> w = WarpStorage.getAllWarps();
        for (Warp warp : w) {
          inv.addItem(warp.createItem(p, pl, true));
        }
        inv.setItem(53, CreateWarpItem.get());
        p.openInventory(inv);
    }
}
