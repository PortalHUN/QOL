package com.portalhun.qol.ChatInput;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NewWarpNameInput {
  public static List<UUID> chatInput = new ArrayList<>();
  public static void AddToList(Player p){
    chatInput.add(p.getUniqueId());
    p.sendRawMessage(ChatColor.BLUE+"Write a name in chat.");
    p.sendRawMessage(ChatColor.BLUE+"Write "+ChatColor.RED+ "'cancel'"+ChatColor.BLUE+" to cancel.");
    p.closeInventory();
  }

  public static void Answer(Player p, String str) {
    if(str.equalsIgnoreCase("cancel")) {
      p.sendRawMessage(ChatColor.RED+"Warp creating cancelled.");
      chatInput.remove(p.getUniqueId());
      p.setLevel(p.getLevel()+3);
      return;
    }
    try {
      WarpStorage.createWarp(str, p.getLocation());
      p.sendRawMessage(ChatColor.BLUE+"Saved the Warp.");
      chatInput.remove(p.getUniqueId());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
