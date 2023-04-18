package com.portalhun.qol.Models;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;

public class Warp {
  public String ID;
  public String Name;
  public LocationModel Loc;
  public Material Item;

  public Warp(String name, Location loc, Material item){
    Name = name;
    Loc = new LocationModel(loc);
    Item = item;
    ID = UUID.randomUUID().toString();
  }

  public Warp(String name, Location loc){
    Name = name;
    Loc = new LocationModel(loc);
    Item = Material.POINTED_DRIPSTONE;
  }
}
