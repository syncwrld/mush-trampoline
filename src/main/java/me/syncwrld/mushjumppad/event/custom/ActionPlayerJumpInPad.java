package me.syncwrld.mushjumppad.event.custom;

import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.api.event.PlayerAtPadEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class ActionPlayerJumpInPad implements Listener {

    @EventHandler
    public void actionWhenPlayerStayInPad(PlayerAtPadEvent event) {
        final Player player = event.getPlayer();
        final Plugin bootstrap =  JumpPadBootstrap.getInstance();
        final double jumpForce = bootstrap.getConfig().getDouble("jump-power");

        player.setVelocity(new Vector(0, jumpForce, 0));
    }

}
