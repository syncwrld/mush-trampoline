package me.syncwrld.mushjumppad.api.event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JumpPadCreateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player padPlacer;
    private final Location location;
    private final Block block;

    public JumpPadCreateEvent(Player padPlacer, Location location, Block block) {
        this.padPlacer = padPlacer;
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

    public Player getPadPlacer() {
        return padPlacer;
    }

}
