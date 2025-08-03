package d3.buggr.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager {

    private JDA JDA;

    private List<TrustCommandsImpl> commandMap = new ArrayList<>();

    public CommandsManager(JDA JDA) {
        this.JDA = JDA;
    }

    public <T extends ListenerAdapter & TrustCommandsImpl> CommandsManager createCommand(TrustCommandsImpl command) {
        this.commandMap.add(command);
        return this;
    }

    public <T extends ListenerAdapter & TrustCommandsImpl> CommandsManager build() {
        if (!this.commandMap.isEmpty()) {

            commandMap.forEach(command -> {

                    if (command.command() == null) {
                        throw new RuntimeException("Please set a command name first.");
                    }

                    if (command.description() == null) {
                        throw new RuntimeException("Please set a command description first.");
                    }

                    if (command.options() == null || command.options().isEmpty()) {
                        JDA.upsertCommand(command.command(), command.description()).queue();
                        JDA.addEventListener(command);
                    } else {
                        JDA.upsertCommand(command.command(), command.description()).addOptions(command.options()).queue();
                        JDA.addEventListener(command);
                    }
            });

        }

        return this;
    }

}
