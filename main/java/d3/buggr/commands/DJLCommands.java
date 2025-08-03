package d3.buggr.commands;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DJLCommands extends ListenerAdapter implements DJLCommandsImpl {

    @Override
    public String command() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<OptionData> options() {
        return null;
    }

    @Override
    public void handleCommand(@NotNull SlashCommandInteractionEvent event) {
    }

    @Override
    public void handleButton(@NotNull ButtonInteractionEvent event) {
    }

    @Override
    public void handleModal(@NotNull ModalInteractionEvent event) {
    }

    @Override
    public void handleMenu(@NotNull StringSelectInteractionEvent event) {
    }
}
