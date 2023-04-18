package com.portalhun.qol.Models;

import com.portalhun.qol.QOL;
import org.bukkit.Location;

public class LocationModel {
  public String world;
  public Double x;
  public Double y;
  public Double z;
  public float pitch;
  public float yaw;
  public LocationModel(Location l){
    world = l.getWorld().getName();
    x = l.getX();
    y = l.getY();
    z = l.getZ();
    pitch = l.getPitch();
    yaw = l.getYaw();
  }

  public Location getLocation(QOL pl){
    return new Location(pl.getServer().getWorld(world), x, y, z, yaw, pitch);
  }
}
