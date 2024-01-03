package me.syncwrld.mushjumppad.event.raw;

import java.util.Set;

import com.cryptomorin.xseries.messages.ActionBar;
import me.syncwrld.mushjumppad.api.event.JumpPadDeleteEvent;
import me.syncwrld.mushjumppad.map.JumpPadCache;
import me.syncwrld.mushjumppad.map.StaticPlaceMap;
import me.syncwrld.mushjumppad.registry.JumpBlockRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class TriggerBlockBreak implements Listener {

  @EventHandler
  public void tryCaptureJumpPadDelete(BlockBreakEvent event) {
    Block block = event.getBlock();
    Material blockType = block.getType();
    Set<Material> allowedTypes = JumpBlockRegistry.getAllowedList();

    JumpPadCache padCache = new JumpPadCache();
    Player player = event.getPlayer();

    if (allowedTypes.contains(blockType)
        && padCache.isPad(block)
        && !(StaticPlaceMap.isPlacing(player))) {
      ActionBar.sendActionBar(player, "§cYou have to enable place mode to remove pads.");
      event.setCancelled(true);
      return;
    }

    if (allowedTypes.contains(blockType)
        && StaticPlaceMap.isPlacing(player)
        && padCache.isPad(block)) {
      Bukkit.getPluginManager()
          .callEvent(new JumpPadDeleteEvent(player, block.getLocation(), block));
    }
  }

}
