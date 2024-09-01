package me.bedtwL.proxyshortcommand.listener;

import me.bedtwL.proxyshortcommand.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class MainCommand extends Command {
    Main plugin;
    public MainCommand(Main Mainplugin) {
        super("pcmd","bedtwl.shortcmd");
        plugin=Mainplugin;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length>0)
        {
            switch (args[0].toLowerCase())
            {
                case "reload":
                    if (commandSender.hasPermission("bedtwl.shortcmd.reload"))
                    {
                        plugin.registerCommands();
                        commandSender.sendMessage(new ComponentBuilder("§eCommands reloaded successfully!").create());
                    }
                    return;
                default:
                    printUsage(commandSender);
                    return;
            }

        }
        printUsage(commandSender);
    }
    public void printUsage(CommandSender commandSender)
    {
        commandSender.sendMessage(new ComponentBuilder("§ebedtwL Proxy Short command").build());
        commandSender.sendMessage(new ComponentBuilder("§e Command:").build());
        if (commandSender.hasPermission("bedtwl.shortcmd.reload"))
            commandSender.sendMessage(new ComponentBuilder("§6 /pcmd reload§7 - §ereload commands").build());
    }
}
