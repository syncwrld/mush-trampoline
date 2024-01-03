package me.syncwrld.mushjumppad.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public final class LocationSerializer extends Location {

  public LocationSerializer(World world, double x, double y, double z) {
    super(world, x, y, z);
  }

  public static String serializeLocation(Location loc) {
    return loc.getWorld().getName()
        + ','
        + loc.getX()
        + ','
        + loc.getY()
        + ','
        + loc.getZ()
        + ','
        + loc.getYaw()
        + ','
        + loc.getPitch();
  }

  public static Location deserializeLocation(String s) {
    String[] location = s.split(",");

    if (Bukkit.getWorld(location[0]) == null) return null;

    if (location.length < 5) return null;

    return new Location(
        Bukkit.getWorld(location[0]),
        Double.parseDouble(location[1]),
        Double.parseDouble(location[2]),
        Double.parseDouble(location[3]),
        Float.parseFloat(location[4]),
        Float.parseFloat(location[5]));
  }

}
