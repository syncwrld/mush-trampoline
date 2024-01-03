package me.syncwrld.mushjumppad.event.custom;

import com.cryptomorin.xseries.messages.ActionBar;
import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.api.event.JumpPadCreateEvent;
import me.syncwrld.mushjumppad.util.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ActionJumpPadCreate implements Listener {

  @EventHandler
  public void actionWhenPlayerPutNewJumpPad(JumpPadCreateEvent event) {
    Location location = event.getLocation();
    String serializedLocation = LocationSerializer.serializeLocation(location);

    final Plugin plugin = JumpPadBootstrap.getInstance();
    final FileConfiguration config = plugin.getConfig();
    final List<String> currentList = config.getStringList("places");

    ActionBar.sendActionBar(event.getPadPlacer(), "§aYou have placed a new jump pad.");

    currentList.add(serializedLocation);
    config.set("places", currentList);
    plugin.saveConfig();
  }

}
