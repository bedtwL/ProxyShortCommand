package me.bedtwL.proxyshortcommand.listener;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;

public class ServerCommand extends Command {
    public ServerInfo server;
    public BaseComponent message;
    public ServerCommand(String name, String permission, ServerInfo targetServer,String messageSend,String... aliases) {
        super(name, permission, aliases);
        server=targetServer;
        message= new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',messageSend)).build();
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer)
        {
            if (message!=null)
            {
                commandSender.sendMessage(message);
            }
            ((ProxiedPlayer) commandSender).connect(server);
        }
        else
        {
            commandSender.sendMessage(new ComponentBuilder("§cThis command can only run on player!").build());
        }
    }
}
