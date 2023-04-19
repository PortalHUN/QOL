package com.portalhun.qol.Commands;

import com.portalhun.qol.Items.TeleportCompassItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCompassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player p){
            p.getInventory().addItem(TeleportCompassItem.getTeleportCompass());
            p.sendRawMessage(ChatColor.BLUE+"Here is your compass.");
        }else{
            sender.sendMessage(ChatColor.RED+"You can't do that.");
        }
        return true;
    }
}
