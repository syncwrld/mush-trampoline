package me.syncwrld.mushjumppad.task;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.api.particle.type.ParticleType;
import com.github.fierioziy.particlenativeapi.core.asm.particle.type.ParticleTypesProvider_1_8;
import me.syncwrld.mushjumppad.JumpPadBootstrap;
import me.syncwrld.mushjumppad.map.JumpPadCache;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PersistentPadEffectTask implements Runnable {

  private final ParticleNativeAPI nativeAPI;

  public PersistentPadEffectTask(JumpPadBootstrap bootstrap) {
    this.nativeAPI = bootstrap.getParticleAPI();
  }

  @Override
  public void run() {
    JumpPadCache cache = new JumpPadCache();
    for (Location location : cache.getLocationMap()) {
      nativeAPI
          .LIST_1_8
          .VILLAGER_HAPPY
          .packet(true, location.add(0.7, 1.2, 0.5))
          .sendInRadiusTo(Bukkit.getOnlinePlayers(), 10);
    }
  }

}
