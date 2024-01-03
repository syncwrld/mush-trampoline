package me.syncwrld.mushjumppad.api.event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAtPadEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private final Player player;
  private final Block block;

  public PlayerAtPadEvent(Player player, Block block) {
    this.player = player;
    this.block = block;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public Block getBlock() {
    return block;
  }

  public Player getPlayer() {
    return player;
  }

}
