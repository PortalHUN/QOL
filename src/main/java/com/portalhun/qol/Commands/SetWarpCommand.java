package com.portalhun.qol.Commands;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetWarpCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if(commandSender instanceof Player p){
      if(args.length==1){
        if(p.getLevel()-3>=0){
          Warp w = null;
          try {
            w = WarpStorage.createWarp(new Warp(args[0], p.getLocation()));
          } catch (IOException e) {
            e.printStackTrace();
          }
          if(w != null){
            p.sendRawMessage(ChatColor.BLUE+"Warp set.");
            p.setLevel(p.getLevel()-3);
          }else{
            p.sendRawMessage(ChatColor.RED+"This name is already in use.");
          }
        }else{
          p.sendRawMessage(ChatColor.RED+"You need 3 XP level to set a warp.");
        }
      }else{
        p.sendRawMessage(ChatColor.RED+"Missing name.");
      }
    }
    return true;
  }
}
