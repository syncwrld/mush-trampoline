package me.syncwrld.mushjumppad.command;

import me.syncwrld.mushjumppad.map.StaticPlaceMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cJust players can execute this command.");
            return false;
        }

        Player player = (Player) commandSender;
        if (!player.hasPermission("pad.admin")) {
            player.sendMessage("§cYou can't execute this command.");
            return false;
        }

        boolean isAlreadyPlacing = StaticPlaceMap.isPlacing(player);
        String message = isAlreadyPlacing ? "§eYou exit pad place mode." : "§aYou join pad place mode.";
        player.sendMessage(message);

        if (isAlreadyPlacing) {
            StaticPlaceMap.removePlacer(player);
        }
        else {
            StaticPlaceMap.addPlacer(player);
        }

        return false;
    }

}
