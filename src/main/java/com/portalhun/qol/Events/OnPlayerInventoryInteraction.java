package com.portalhun.qol.Events;

import com.portalhun.qol.ChatInput.NewWarpNameInput;
import com.portalhun.qol.ChatInput.UpdateWarpNameInput;
import com.portalhun.qol.Items.CreateWarpItem;
import com.portalhun.qol.Items.TeleportCompassItem;
import com.portalhun.qol.Menus.TeleportMenu;
import com.portalhun.qol.Menus.UpdateWarpIconMenu;
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
      if(UpdateWarpNameInput.chatInput.containsKey(e.getView().getPlayer().getUniqueId())) {e.setCancelled(true); return;}
        Player p = (Player) e.getView().getPlayer();
        if(e.getView().getTitle().equals(TeleportMenu.Name)){
            e.setCancelled(true);
          ItemStack stack = e.getCurrentItem();
          if(stack != null){
            Location l = pl.getConfig().getLocation("spawn");
            if(l!=null){
              Warp spawn = new Warp("Spawn", l, Material.GRASS_BLOCK);
              if(stack.equals(spawn.createItem(p,pl,true))) {
                spawn.teleport(p,pl);
                return;
              }
            }
            ItemMeta meta = stack.getItemMeta();
            String s = ChatColor.stripColor(meta.getDisplayName());
            Warp w = WarpStorage.findWarpByName(s);
            if(stack.equals(CreateWarpItem.get())){
              //New Warp Item
              if(p.getLevel()-3>=0){
                NewWarpNameInput.AddToList(p);
                p.setLevel(p.getLevel()-3);
              }
            }else if(meta.hasEnchant(Enchantment.OXYGEN) && meta.hasEnchant(Enchantment.LUCK)){
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
             UpdateWarpNameInput.AddToList(p,w);
           }else if(stack.equals(UpdateWarpMenu.ChangeWarpIconItem(w))){
             UpdateWarpIconMenu.Open(p, w, pl);
           }
          }
        }else if(e.getView().getTitle().equals(UpdateWarpIconMenu.Name)){
          ItemStack stack = e.getCurrentItem();
          if(stack!=null){
            String s = ChatColor.stripColor(e.getView().getItem(4).getItemMeta().getDisplayName());
            Warp w = WarpStorage.findWarpByName(s);
            if(stack.getItemMeta().hasEnchant(Enchantment.OXYGEN) && stack.getItemMeta().hasEnchant(Enchantment.LUCK)){
              e.setCancelled(true);
              if(stack.equals(UpdateWarpIconMenu.makeGlassPane(true))){
                ItemStack a = e.getView().getItem(31);
                if(a!=null){
                  w.Item = a.getType();
                  try {
                    WarpStorage.updateWarp(w);
                    p.getInventory().addItem(a);
                  } catch (IOException ex) {
                    ex.printStackTrace();
                  }
                  p.closeInventory();
                }
              }
            }else if(stack.getItemMeta().equals(TeleportCompassItem.get(pl).getItemMeta())) e.setCancelled(true);
          }
        }
    }
}