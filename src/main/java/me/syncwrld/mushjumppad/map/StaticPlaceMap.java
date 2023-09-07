package me.syncwrld.mushjumppad.map;

import org.bukkit.entity.Player;

import java.util.LinkedList;

public class StaticPlaceMap {

    private static final LinkedList<Player> placePlayers = new LinkedList<>();

    public static boolean isPlacing(Player player) {
        return placePlayers.contains(player);
    }

    public static void removePlacer(Player player) {
        placePlayers.remove(player);
    }

    public static void addPlacer(Player player) {
        placePlayers.add(player);
    }

}
