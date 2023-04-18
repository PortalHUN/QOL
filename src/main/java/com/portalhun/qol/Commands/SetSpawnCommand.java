package com.portalhun.qol.Commands;

import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
  private final QOL pl;
  public SetSpawnCommand(QOL pl){
    this.pl = pl;
  }
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if(sender instanceof Player p){
      if(p.isOp()){
        Location loc = p.getLocation();
        pl.getConfig().set("spawn",loc);
        pl.saveConfig();
        p.sendRawMessage(ChatColor.BLUE+"Spawn location set.");
      }else p.sendRawMessage(ChatColor.RED+"You can't do this.");
    }else {
      sender.sendMessage(ChatColor.RED+"You can't do this.");
    }
    return true;
  }
}
