package com.portalhun.qol.ChatInput;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class UpdateWarpNameInput {
  public static HashMap<UUID, Warp> chatInput = new HashMap<>();
  public static void AddToList(Player p, Warp w){
    chatInput.put(p.getUniqueId(), w);
    p.sendRawMessage(ChatColor.BLUE+"Write a name in chat.");
    p.sendRawMessage(ChatColor.BLUE+"Write "+ChatColor.RED+ "'cancel'"+ChatColor.BLUE+" to cancel.");
    p.closeInventory();
  }

  public static void Answer(Player p, String str) {
    if(str.equalsIgnoreCase("cancel") || str.equalsIgnoreCase(" ")){
      p.sendRawMessage(ChatColor.RED+"Warp naming cancelled.");
      chatInput.remove(p.getUniqueId());
      return;
    }
    Warp w = chatInput.get(p.getUniqueId());
    try {
      WarpStorage.updateWarpName(w.Name, str);
      p.sendRawMessage(ChatColor.BLUE+"Updated the Warp Name.");
      chatInput.remove(p.getUniqueId());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
