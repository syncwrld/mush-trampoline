package me.syncwrld.mushjumppad.task;

import me.syncwrld.mushjumppad.api.event.PlayerAtPadEvent;
import me.syncwrld.mushjumppad.map.JumpPadCache;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class PersistentMovementTask implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Block downBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
            if (downBlock.isEmpty() || downBlock.getType() == Material.AIR)
                continue;

            JumpPadCache cache = new JumpPadCache();
            if (cache.isPad(downBlock)) {
                Bukkit.getPluginManager().callEvent(
                        new PlayerAtPadEvent(player, downBlock)
                );
            }
        }
    }

}
