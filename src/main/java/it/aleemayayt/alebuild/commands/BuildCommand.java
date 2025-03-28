package it.aleemayayt.alebuild.commands;

import it.aleemayayt.alebuild.AleBuild;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class BuildCommand implements CommandExecutor {

    private AleBuild plugin;

    public BuildCommand(AleBuild plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Questo comando può essere eseguito solo da un giocatore.").color(NamedTextColor.RED));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("alebuild.build")) {
            String noPermissionMessage = plugin.getConfig().getString("messages.no-permission");
            noPermissionMessage = ChatColor.translateAlternateColorCodes('&', noPermissionMessage);
            player.sendMessage(Component.text(noPermissionMessage).color(NamedTextColor.RED));
            return false;
        }

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
