package d3.buggr.commands;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DJLCommandsImpl {
    String command();
    String description();
    List<OptionData> options();
    void handleCommand(@NotNull SlashCommandInteractionEvent event);
    void handleButton(@NotNull ButtonInteractionEvent event);
    void handleModal(@NotNull ModalInteractionEvent event);
    void handleMenu(@NotNull StringSelectInteractionEvent event);

}
