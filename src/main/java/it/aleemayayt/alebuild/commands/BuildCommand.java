package it.aleemayayt.alebuild.commands;

import it.aleemayayt.alebuild.AleBuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    private AleBuild plugin;

    public BuildCommand(AleBuild plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Questo comando può essere eseguito solo da un giocatore.");
            return false;
        }

        Player player = (Player) sender;
        if (plugin.canBuild(player)) {
            plugin.setCanBuild(player, false);
            player.sendMessage(plugin.getBuildDisabledMessage());
        } else {
            plugin.setCanBuild(player, true);
            player.sendMessage(plugin.getBuildEnabledMessage());
        }

        return true;
    }
}
