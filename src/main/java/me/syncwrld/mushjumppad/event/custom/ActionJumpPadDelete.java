package me.syncwrld.mushjumppad.event.custom;

import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.api.event.JumpPadCreateEvent;
import me.syncwrld.mushjumppad.api.event.JumpPadDeleteEvent;
import me.syncwrld.mushjumppad.util.LocationSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ActionJumpPadDelete implements Listener {

    @EventHandler
    public void actionWhenPlayerPutNewJumpPad(JumpPadDeleteEvent event) {
        Location location = event.getLocation();
        String serializedLocation = LocationSerializer.serializeLocation(location);

        final Plugin plugin = JumpPadBootstrap.getInstance();
        final FileConfiguration config = plugin.getConfig();
        final List<String> currentList = config.getStringList("places");

        event.getPadRemover().sendMessage("§cYou removed an existing pad.");

        currentList.remove(serializedLocation);
        config.set("places", currentList);
        plugin.saveConfig();
    }

}
