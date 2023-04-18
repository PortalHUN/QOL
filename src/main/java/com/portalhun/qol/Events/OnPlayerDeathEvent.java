package com.portalhun.qol.Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeathEvent implements Listener {
  @EventHandler
  public void OnPlayerDeathEvent(PlayerDeathEvent e){
    e.setDeathMessage(ChatColor.RED+e.getDeathMessage()+" Location: World: "+e.getEntity().getLocation().getWorld().getName()+" X: "+(int) Math.floor(e.getEntity().getLocation().getX())+" Y: "+(int) Math.floor(e.getEntity().getLocation().getY())+" Z: "+(int) Math.floor(e.getEntity().getLocation().getZ()));
  }
}
