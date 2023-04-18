package com.portalhun.qol.Commands;

import com.portalhun.qol.Models.Warp;
import com.portalhun.qol.QOL;
import com.portalhun.qol.Storage.WarpStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class WarpCommand implements CommandExecutor {
  private final QOL pl;
  public WarpCommand(QOL pl){
    this.pl = pl;
  }
  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if(commandSender instanceof Player p){
      if(args.length == 1){
        Warp w = WarpStorage.findWarpByName(args[0]);
        if(w !=null){
          Location l = w.Loc.getLocation(pl);
          if(l.getWorld() == p.getWorld()){
            double cost = pl.getConfig().getDouble("xp-cost");
            int all = (int) Math.floor(p.getLocation().distance(l)*cost);
            if(p.getTotalExperience()-all >= 0){
              p.giveExp(-all);
              p.teleport(l, TeleportCause.COMMAND);
              p.sendRawMessage(ChatColor.BLUE+"Teleported to "+w.Name+"! " + "Cost: "+(-all)+" XP");
            }else{
              p.sendRawMessage(ChatColor.RED+"You don't have enough XP. Cost: "+(-all)+" XP - You have: " + p.getTotalExperience()+" XP");
            }
          }else{
            if(p.getLevel()-5>=0 ){
              p.setLevel(p.getLevel()-5);
              p.teleport(l, TeleportCause.COMMAND);
              p.sendRawMessage(ChatColor.BLUE+"Teleported to "+w.Name+"! "+"Cost: 5 XP Level.");
            }else{
              p.sendRawMessage(ChatColor.RED+"You need 5 XP level to teleport through worlds.");
            }
          }
        }else p.sendRawMessage(ChatColor.RED+"Unknown Warp.");
      } else p.sendRawMessage(ChatColor.RED+"Missing name.");
    }else commandSender.sendMessage(ChatColor.RED+"You can't do this.");
    return true;
  }
}
