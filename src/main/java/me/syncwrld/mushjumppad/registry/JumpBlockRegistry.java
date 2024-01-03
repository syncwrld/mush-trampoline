package me.syncwrld.mushjumppad.registry;

import com.cryptomorin.xseries.XMaterial;
import java.util.*;
import me.syncwrld.mushjumppad.JumpPadBootstrap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class JumpBlockRegistry {

  private static final Set<Material> allowedList = new HashSet<>();

  public static void load() {
    allowedList.clear();
    final Plugin plugin = JumpPadBootstrap.getInstance();
    FileConfiguration config = plugin.getConfig();
    List<String> allowedTypesAsString = config.getStringList("jump-blocks");

    for (String string : allowedTypesAsString) {
      Optional<XMaterial> material = XMaterial.matchXMaterial(string);
      if (material.isPresent()) {
        allowedList.add(material.get().parseMaterial());
      } else {
        Bukkit.getConsoleSender().sendMessage("§c[JumpPad] Block type not found: " + string + ", try to configure it to another type. Check if your version supports this block type.");
      }
    }
  }

  public static Set<Material> getAllowedList() {
    return allowedList;
  }
}
