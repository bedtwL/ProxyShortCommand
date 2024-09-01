## bedtwL's Short Command Plugin

bedtwL's Short Command is a BungeeCord plugin that allows server administrators to create dynamic server commands that can be easily configured and reloaded without restarting the proxy. This plugin is ideal for simplifying server management and providing players with quick access to various server commands.

### Features

- **Dynamic Command Configuration**: Easily configure commands via `config.yml`.
- **Server Teleportation**: Redirect players to specified servers with custom commands.
- **Custom Messages**: Display customizable messages when commands are executed.
- **Permission-Based Commands**: Set permissions for individual commands to control access.
- **Aliases Support**: Create multiple aliases for a single command.
- **Reload Commands**: Reload all commands dynamically without restarting the proxy.

### Commands and Permissions

| Command                | Description                                  | Permission                  |
|------------------------|----------------------------------------------|-----------------------------|
| `/pcmd reload`         | Reloads all the commands from the configuration file. | `bedtwL.shortcmd.reload`    |

### Installation

1. **Download the Plugin**: Place the `bedtwL's Short Command` plugin JAR file into the `plugins` folder of your BungeeCord server.
2. **Restart the Server**: Restart your BungeeCord server to generate the default configuration file.
3. **Configure the Plugin**: Open the `config.yml` file in the `plugins/ProxyShortCommand` folder to configure your custom commands.
4. **Reload Commands**: Use the `/pcmd reload` command to reload the commands after making changes to the `config.yml` file.

### Configuration

The `config.yml` file allows you to define custom commands that will run specified server commands or teleport players to different servers.

#### Example `config.yml`

```yaml
commands:
  example:
    server: "lobby"
    message: "&aWelcome to the lobby!"
    permission: "bedtwL.shortcmd.example"
    aliases: ["ex", "exmpl"]

  hub:
    server: "hub"
    message: "&cTeleporting to the hub!"
    permission: null
    aliases: []
```

#### Configuration Options

- **`server`**: The name of the server to which the player will be connected.
- **`message`**: A message sent to the player when the command is executed (supports color codes).
- **`permission`**: The required permission to use the command. Set to `null` for no permission requirement.
- **`aliases`**: A list of aliases for the command.

### Usage

1. **Add Commands**: Edit the `config.yml` file to add your custom commands.
2. **Reload Configuration**: Run `/pcmd reload` to apply changes without restarting the server.
3. **Execute Commands**: Use your custom commands in-game or in the BungeeCord console.

### Permissions

- **`bedtwL.shortcmd`**: Required to use any commands defined by the plugin.
- **`bedtwL.shortcmd.reload`**: Required to use the `/pcmd reload` command.

### Support

For support or to report issues, please visit our GitHub repository or contact the developer directly.
