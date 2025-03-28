package it.aleemayayt.alebuild.commands;

import it.aleemayayt.alebuild.AleBuild;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class ReloadCommand implements CommandExecutor {

    private AleBuild plugin;

    public ReloadCommand(AleBuild plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            String usageMessage = ChatColor.translateAlternateColorCodes('&', "&c&l[&4&l!&c&l] &bUso: &a/alebuild reload");
            sender.sendMessage(usageMessage);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!(sender instanceof Player)) {
                plugin.reloadPlugin();
                String reloadSuccessMessage = plugin.getConfig().getString("messages.reload-success");
                reloadSuccessMessage = ChatColor.translateAlternateColorCodes('&', reloadSuccessMessage);
                sender.sendMessage(reloadSuccessMessage);
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("alebuild.reload")) {
                String noPermissionMessage = plugin.getConfig().getString("messages.no-permission");
                noPermissionMessage = ChatColor.translateAlternateColorCodes('&', noPermissionMessage);
                player.sendMessage(Component.text(noPermissionMessage).color(NamedTextColor.RED));
                return false;
            }

            plugin.reloadPlugin();
            String reloadSuccessMessage = plugin.getConfig().getString("messages.reload-success");
            reloadSuccessMessage = ChatColor.translateAlternateColorCodes('&', reloadSuccessMessage);
            player.sendMessage(reloadSuccessMessage);
            return true;
        }

        return false;
    }
}
