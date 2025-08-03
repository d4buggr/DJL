# DJL - Discord Java Library
Fast and easy to use library to make discord bots
(Library is still under developement)

# USAGE:
creating bot:

now we will create your first bot instance that you'll be able to configure at your own
```java
DJL app = DJLBuilder.create("YOUR TOKEN").start();
```

getting JDA:
```java
DJL app = DJLBuilder.create("YOUR TOKEN").start();
JDA jda = app.getJDA();
```
creating your first command:

now we will create first command called Ping that will send a reply message Pong!
if you have already created your app then you have to do this.
PingCommand.java
```java
public class PingCommand extends DJLCommands {

    @Override
    public String command() {
        return "ping";
    }

    @Override
    public String description() {
        return "replies with pong!";
    }

    @Override
    public void handleCommand(@NotNull SlashCommandInteractionEvent event) {
        if (event.getCommandString().equals(command())) {
            event.reply("Pong!").setEphemeral(true).queue();
        }
    }
}
```

Main.java
```java
new CommandManager(jda)
.createCommand(PingCommand.class)
.build();
```

now you'll see how to add arguments to a command so the only thing you have to do is add this
PingCommand.java
```java
public class PingCommand extends DJLCommands {

    @Override
    public String command() {
        return "ping";
    }

    @Override
    public String description() {
        return "replies with pong!";
    }

    @Override
    public List<OptionData> options() {
        return Arrays.asList(
                new OptionData(OptionType.STRING, "message", "adds message to ping")
        );
    }

    @Override
    public void handleCommand(@NotNull SlashCommandInteractionEvent event) {
        if (event.getCommandString().equals(command())) {
            String optionResult = event.getOption("message").getAsString();
            event.reply("Pong! " + optionResult + " !").setEphemeral(true).queue();
        }
    }
}
```

Feel free to ask any questions
More will be added soon.
