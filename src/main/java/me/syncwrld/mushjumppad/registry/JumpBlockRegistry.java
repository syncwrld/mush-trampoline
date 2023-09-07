package me.syncwrld.mushjumppad.registry;

import me.syncwrld.mushjumppad.JumpPadBootstrap;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JumpBlockRegistry {

    private static final Set<Material> allowedList = new HashSet<>();

    public static void load() {
        final Plugin plugin = JumpPadBootstrap.getInstance();
        FileConfiguration config = plugin.getConfig();
        List<String> allowedTypesAsString = config.getStringList("jump-blocks");

        for (String string : allowedTypesAsString) {
            Material material = Material.getMaterial(string);
            if (material != null) {
                allowedList.add(material);
            }
        }
    }

    public static Set<Material> getAllowedList() {
        return allowedList;
    }
}
