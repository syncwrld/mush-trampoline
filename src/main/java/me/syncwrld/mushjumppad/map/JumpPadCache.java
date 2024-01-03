package me.syncwrld.mushjumppad.map;

import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.util.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class JumpPadCache {

  private final List<Location> locationMap = new ArrayList<>();

  public JumpPadCache() {
    Plugin plugin = JumpPadBootstrap.getInstance();
    List<String> currentList = plugin.getConfig().getStringList("places");
    for (String string : currentList) {
      Location location = LocationSerializer.deserializeLocation(string);
      if (location != null) locationMap.add(location);
    }
  }

  public List<Location> getLocationMap() {
    return locationMap;
  }

  public boolean isPad(Block block) {
    return locationMap.contains(block.getLocation());
  }

}
