package com.portalhun.qol.Events;

import com.portalhun.qol.Items.CreateWarpItem;
import com.portalhun.qol.Menus.TeleportMenu;
import com.portalhun.qol.Menus.UpdateWarpMenu;
import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.*;
import org.bukkit.block.data.type.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;

public class OnPlayerInventoryInteraction implements Listener {
    private final QOL pl;
    public OnPlayerInventoryInteraction(QOL pl){
        this.pl = pl;
    }
    @EventHandler
    public void onPlayerInventoryInteraction(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();
        if(e.getView().getTitle().equals(TeleportMenu.Name)){
            e.setCancelled(true);
          ItemStack stack = e.getCurrentItem();
          if(stack != null){
            ItemMeta meta = stack.getItemMeta();
            String s = ChatColor.stripColor(meta.getDisplayName());
            Warp w = WarpStorage.findWarpByName(s);
            if(stack.equals(CreateWarpItem.get())){
              //New Warp Item
            }else{
              if(e.getAction() == InventoryAction.PICKUP_ALL){
                w.teleport(p,pl);
              }else if(e.getAction() == InventoryAction.PICKUP_HALF){
                UpdateWarpMenu.Open(p, w, pl);
              }
            }

          }
        }else if(e.getView().getTitle().equals(UpdateWarpMenu.Name)){
          e.setCancelled(true);
          ItemStack stack = e.getCurrentItem();
          if(stack !=null){
           String s = ChatColor.stripColor(e.getView().getItem(13).getItemMeta().getDisplayName());
           Warp w = WarpStorage.findWarpByName(s);
           if(stack.equals(UpdateWarpMenu.DeleteWarpItem(w))){
             try {
               Warp ad =WarpStorage.deleteWarp(w.Name);
               p.closeInventory();
             } catch (IOException ex) {
               ex.printStackTrace();
             }
           }else if(stack.equals(UpdateWarpMenu.UpdateWarpNameItem(w))){

           }else if(stack.equals(UpdateWarpMenu.ChangeWarpIconItem(w))){

           }
          }
        }
    }
}

/*          if(stack.equals(CreateWarpItem.get())){
              //New Warp Item
            }else{
              NamespacedKey key = new NamespacedKey(pl, "loc");
              String l = stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
              System.out.println(l);
            }*/