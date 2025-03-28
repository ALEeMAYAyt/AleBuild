package it.aleemayayt.alebuild;

import it.aleemayayt.alebuild.commands.BuildCommand;
import it.aleemayayt.alebuild.commands.ReloadCommand;
import it.aleemayayt.alebuild.events.BuildEventListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class AleBuild extends JavaPlugin {

    private Map<Player, Boolean> playerBuildPermission = new HashMap<>();
    private String buildEnabledMessage;
    private String buildDisabledMessage;
    private String reloadSuccessMessage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadMessages();

        getCommand("build").setExecutor(new BuildCommand(this));
        getCommand("alebuild").setExecutor(new ReloadCommand(this));

        getServer().getPluginManager().registerEvents(new BuildEventListener(this), this);
    }

    private void loadMessages() {
        buildEnabledMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.build-enabled"));
        buildDisabledMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.build-disabled"));
        reloadSuccessMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.reload-success"));
    }

    public boolean canBuild(Player player) {
        return playerBuildPermission.getOrDefault(player, false);
    }

    public void setCanBuild(Player player, boolean canBuild) {
        playerBuildPermission.put(player, canBuild);
    }

    public String getBuildEnabledMessage() {
        return buildEnabledMessage;
    }

    public String getBuildDisabledMessage() {
        return buildDisabledMessage;
    }

    public String getReloadSuccessMessage() {
        return reloadSuccessMessage;
    }

    public void reloadPlugin() {
        reloadConfig();
        loadMessages();
    }
}
