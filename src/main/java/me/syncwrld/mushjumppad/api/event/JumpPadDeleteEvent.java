package me.syncwrld.mushjumppad.api.event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JumpPadDeleteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player padRemover;
    private final Location location;
    private final Block block;

    public JumpPadDeleteEvent(Player padRemover, Location location, Block block) {
        this.padRemover = padRemover;
        this.location = location;
        this.block = block;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Block getBlock() {
        return block;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPadRemover() {
        return padRemover;
    }

}
