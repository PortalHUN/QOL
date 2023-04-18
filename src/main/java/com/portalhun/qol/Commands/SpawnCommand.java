package com.portalhun.qol.Commands;

import com.portalhun.qol.QOL;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class SpawnCommand implements CommandExecutor {
  private final QOL pl;
  public SpawnCommand(QOL pl) {
    this.pl = pl;
  }
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if(sender instanceof Player p){
      Location loc =pl.getConfig().getLocation("spawn");
      if(loc !=null){
        if(loc.getWorld() == p.getWorld()){
          double cost = pl.getConfig().getDouble("xp-cost");
          int all = (int) Math.floor(p.getLocation().distance(loc)*cost);
          if(p.getTotalExperience()-all >= 0){
            p.giveExp(-all);
            p.teleport(loc, TeleportCause.COMMAND);
            p.sendRawMessage(ChatColor.BLUE+"Teleported to Spawn! Cost: "+(-all)+" XP");
          }else{
            p.sendRawMessage(ChatColor.RED+"You don't have enough XP. Cost: "+(-all)+" XP - You have: " + p.getTotalExperience()+" XP");
          }
        }else{
          if(p.getLevel()-5>=0 ){
            p.setLevel(p.getLevel()-5);
            p.teleport(loc, TeleportCause.COMMAND);
            p.sendRawMessage(ChatColor.BLUE+"You just teleported through worlds. Cost: 5 XP Level.");
          }else{
            p.sendRawMessage(ChatColor.BLUE+"You need 5 XP level to teleport through worlds.");
          }
        }
      }else sender.sendMessage(ChatColor.RED+"You need to set a Location first.");
    }else {
      sender.sendMessage(ChatColor.RED+"You can't do this.");
    }
    return true;
  }
}
