package me.syncwrld.mushjumppad.event.raw;

import java.util.Set;
import me.syncwrld.mushjumppad.api.event.JumpPadCreateEvent;
import me.syncwrld.mushjumppad.map.StaticPlaceMap;
import me.syncwrld.mushjumppad.registry.JumpBlockRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TriggerBlockPlace implements Listener {

  @EventHandler
  public void tryCaptureJumpPadPlace(BlockPlaceEvent event) {
    Block block = event.getBlock();
    Material blockType = block.getType();
    Set<Material> allowedTypes = JumpBlockRegistry.getAllowedList();

    Player player = event.getPlayer();
    if (!allowedTypes.contains(blockType) && StaticPlaceMap.isPlacing(player)) {
        player.sendMessage("§cThis block can't be part of a JumpPad, configure it in config.yml.");
      return;
    }

    if (allowedTypes.contains(blockType) && StaticPlaceMap.isPlacing(player)) {
      Bukkit.getPluginManager()
          .callEvent(new JumpPadCreateEvent(player, block.getLocation(), block));
    }
  }
}
