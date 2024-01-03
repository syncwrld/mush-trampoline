package me.syncwrld.mushjumppad.command;

import com.google.common.base.Stopwatch;
import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.map.StaticPlaceMap;
import me.syncwrld.mushjumppad.registry.JumpBlockRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class PadCommand implements CommandExecutor {

  private final JumpPadBootstrap plugin;

  public PadCommand(JumpPadBootstrap plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if (args.length == 0) {
      PluginDescriptionFile description = plugin.getDescription();
      commandSender.sendMessage(
          new String[] {
            "§e"
                + description.getName()
                + " version "
                + description.getVersion()
                + " by "
                + String.join(", ", description.getAuthors()),
            "§eSource code: " + description.getWebsite(),
            "§7Type /pad help for command help!",
          });
      return false;
    }

    String action = args[0].toLowerCase();
    switch (action) {
      case "help":
        handleHelp(commandSender);
        break;
      case "toggle":
        handleToggle(commandSender);
        break;
      case "reload":
        handleReload(commandSender);
        break;
      default:
        commandSender.sendMessage("§cUnknown sub-command.");
    }

    return false;
  }

  void handleToggle(CommandSender commandSender) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage("§cJust players can execute this command.");
      return;
    }

    Player player = (Player) commandSender;
    if (!player.hasPermission("pad.admin")) {
      player.sendMessage("§cYou can't execute this command.");
      return;
    }

    boolean isAlreadyPlacing = StaticPlaceMap.isPlacing(player);
    String message = isAlreadyPlacing ? "§eYou exit pad place mode." : "§aYou join pad place mode.";
    player.sendMessage(message);

    if (isAlreadyPlacing) {
      StaticPlaceMap.removePlacer(player);
    } else {
      StaticPlaceMap.addPlacer(player);
    }
  }

  void handleHelp(CommandSender commandSender) {
    commandSender.sendMessage(
        new String[] {
          "§7/pad toggle - Toggle place mode.",
          "§7/pad help - Display this help message.",
          "§7/pad reload - Reload plugin configuration."
        });
  }

  void handleReload(CommandSender commandSender) {
    if (!commandSender.hasPermission("pad.admin")) {
      commandSender.sendMessage("§cYou can't execute this command.");
      return;
    }
    Stopwatch started = Stopwatch.createStarted();
    plugin.reloadConfig();
    JumpBlockRegistry.load();
    commandSender.sendMessage("§aConfiguration reloaded in " + started.stop().toString() + ".");
  }
}
