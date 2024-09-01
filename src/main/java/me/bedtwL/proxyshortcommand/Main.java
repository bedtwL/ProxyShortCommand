package me.bedtwL.proxyshortcommand;

import me.bedtwL.proxyshortcommand.listener.MainCommand;
import me.bedtwL.proxyshortcommand.listener.ServerCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class Main extends Plugin {

    private final Map<String, Command> registeredCommands = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        registerCommands();
        getProxy().getPluginManager().registerCommand(this, new MainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void loadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, configFile.toPath());
                getLogger().info("Default config.yml has been copied to plugin folder.");
            } catch (IOException e) {
                getLogger().log(Level.SEVERE, "Could not create config file", e);
            }
        }
    }

    public void registerCommands() {
        unregisterCommands(); // Clean up existing commands before re-registering

        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            Configuration commandsSection = config.getSection("commands");

            for (String commandName : commandsSection.getKeys()) {
                String serverName = commandsSection.getString(commandName + ".server");
                String message = commandsSection.getString(commandName + ".message", null);
                String permission = commandsSection.getString(commandName + ".permission", null);
                String[] aliases = commandsSection.getStringList(commandName + ".aliases").toArray(new String[0]);

                ServerInfo targetServer = ProxyServer.getInstance().getServerInfo(serverName);

                if (targetServer != null) {
                    ServerCommand command = new ServerCommand(commandName, permission, targetServer, message, aliases);
                    ProxyServer.getInstance().getPluginManager().registerCommand(this, command);
                    registeredCommands.put(commandName, command);
                } else {
                    getLogger().warning("Server '" + serverName + "' does not exist in BungeeCord configuration.");
                }
            }
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not load config file", e);
        }
    }

    public void unregisterCommands() {
        for (Command command : registeredCommands.values()) {
            ProxyServer.getInstance().getPluginManager().unregisterCommand(command);
        }
        registeredCommands.clear();
    }
}
