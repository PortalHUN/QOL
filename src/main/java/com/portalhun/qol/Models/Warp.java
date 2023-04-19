package com.portalhun.qol.Models;

import com.portalhun.qol.QOL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warp {
  public String ID;
  public String Name;
  public LocationModel Loc;
  public Material Item;

  public Warp(String name, Location loc, Material item){
    Name = name;
    Loc = new LocationModel(loc);
    Item = item;
    ID = UUID.randomUUID().toString();
  }

  public Warp(String name, Location loc){
    Name = name;
    Loc = new LocationModel(loc);
    Item = Material.POINTED_DRIPSTONE;
  }

  public void teleport(Player p, QOL pl){
    int c = cost(p, pl);
    if(c != -1){
      if(p.getTotalExperience()-c>=0){
        p.giveExp(-c);
        p.teleport(getLocation());
        p.sendRawMessage(ChatColor.BLUE+"You just teleported to "+Name+".");
      }else{
        p.sendRawMessage(ChatColor.RED+"You don't have enough XP! Cost: "+c+" XP");
      }
    }else{
      //5 level cost
      if(p.getLevel()-5>=0){
        p.setLevel(p.getLevel()-5);
        p.teleport(getLocation());
        p.sendRawMessage(ChatColor.BLUE+"You just teleported to "+Name+".");
      }else{
        p.sendRawMessage(ChatColor.RED+"You don't have enough XP! Cost: 5 Levels");
      }
    }
  }

  public int cost(Player p, QOL pl){
    if(p.getWorld().getName() == getLocation().getWorld().getName()){
      return (int) Math.floor(pl.getConfig().getDouble("xp-cost")*p.getLocation().distance(getLocation()));
    }else{
      return -1;
    }
  }

  public Location getLocation(){
    return new Location(Bukkit.getWorld(Loc.world), Loc.x, Loc.y, Loc.z, Loc.yaw, Loc.pitch);
  }

  public ItemStack createItem(Player p, QOL pl, boolean cost){
    Location l = getLocation();
    ItemStack item = new ItemStack(Item,1);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+Name);
    List<String> lore = new ArrayList<String>();
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    int c = cost(p,pl);
    if(cost){
      if(c == -1)
        lore.add(ChatColor.RED + "Cost: 5 Levels");
      else
        lore.add(ChatColor.RED+"Cost: "+ c + " XP");
    }
    lore.add(ChatColor.RED+"Coordinates:");
    lore.add(ChatColor.RED+"X: "+(int)Math.floor(l.getX())+" Y: "+(int)Math.floor(l.getY())+ " Z: "+(int)Math.floor(l.getZ()));
    lore.add(ChatColor.RED+l.getWorld().getName());
    meta.setLore(lore);
    meta.addEnchant(Enchantment.OXYGEN, 1, false);
    item.setItemMeta(meta);
    return item;
  }
}
