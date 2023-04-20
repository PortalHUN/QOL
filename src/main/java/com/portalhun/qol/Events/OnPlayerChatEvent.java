package com.portalhun.qol.Events;

import com.portalhun.qol.ChatInput.NewWarpNameInput;
import com.portalhun.qol.ChatInput.UpdateWarpNameInput;
import com.portalhun.qol.QOL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChatEvent implements Listener {
  private final QOL pl;
  public OnPlayerChatEvent(QOL pl){
    this.pl = pl;
  }
  @EventHandler
  public void onPlayerChatEvent(AsyncPlayerChatEvent e){
    if(UpdateWarpNameInput.chatInput.containsKey(e.getPlayer().getUniqueId())){
      String str = e.getMessage();
      UpdateWarpNameInput.Answer(e.getPlayer(), str);
      e.setCancelled(true);
    }else if(NewWarpNameInput.chatInput.contains(e.getPlayer().getUniqueId())){
      String str = e.getMessage();
      NewWarpNameInput.Answer(e.getPlayer(),str);
      e.setCancelled(true);
    }
  }
}
