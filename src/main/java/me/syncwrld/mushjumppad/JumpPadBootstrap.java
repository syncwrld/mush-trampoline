package me.syncwrld.mushjumppad;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import me.syncwrld.mushjumppad.command.PadCommand;
import me.syncwrld.mushjumppad.event.custom.ActionJumpPadCreate;
import me.syncwrld.mushjumppad.event.custom.ActionJumpPadDelete;
import me.syncwrld.mushjumppad.event.custom.ActionPlayerJumpInPad;
import me.syncwrld.mushjumppad.event.raw.TriggerBlockBreak;
import me.syncwrld.mushjumppad.event.raw.TriggerBlockPlace;
import me.syncwrld.mushjumppad.registry.JumpBlockRegistry;
import me.syncwrld.mushjumppad.task.PersistentMovementTask;
import me.syncwrld.mushjumppad.task.PersistentPadEffectTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class JumpPadBootstrap extends JavaPlugin {

    public static Plugin getInstance() {
        return JavaPlugin.getPlugin(JumpPadBootstrap.class);
    }

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 19746);
        setupConfig();
        JumpBlockRegistry.load();

        registerListeners(
                new TriggerBlockPlace(),
                new TriggerBlockBreak(),
                new ActionJumpPadCreate(),
                new ActionJumpPadDelete(),
                new ActionPlayerJumpInPad()
        );

        this.getServer().getScheduler().runTaskTimer(
                this, new PersistentMovementTask(), 0L, 2L
        );

        if (this.getConfig().getBoolean("enable-effect")) {
            this.getServer().getScheduler().runTaskTimer(
                    this, new PersistentPadEffectTask(this), 0L, 10L
            );
        }

        this.getCommand("pad").setExecutor(new PadCommand());
    }

    private void setupConfig() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists())
            saveResource("config.yml", false);
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public ParticleNativeAPI getParticleAPI() {
        return ParticleNativeCore.loadAPI(this);
    }

}
