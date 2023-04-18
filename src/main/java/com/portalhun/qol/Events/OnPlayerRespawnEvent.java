package com.portalhun.qol.Events;

import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnEvent implements Listener {
  private final QOL pl;
  public OnPlayerRespawnEvent(QOL pl){
    this.pl = pl;
  }

  @EventHandler
  public void onPlayerRespawnEvent(PlayerRespawnEvent e){
    if(!e.isBedSpawn()){
      Location loc = pl.getConfig().getLocation("spawn");
      if(loc !=null){
        e.setRespawnLocation(loc);
      }else{
        e.getPlayer().sendRawMessage(ChatColor.RED+"You need to set a Spawn Location to respawn there.");
      }
    }
  }
}
