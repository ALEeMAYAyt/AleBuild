package it.aleemayayt.alebuild.events;

import it.aleemayayt.alebuild.AleBuild;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;

public class BuildEventListener implements Listener {

    private AleBuild plugin;

    public BuildEventListener(AleBuild plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!plugin.canBuild(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!plugin.canBuild(player)) {
            event.setCancelled(true);
        }
    }
}
