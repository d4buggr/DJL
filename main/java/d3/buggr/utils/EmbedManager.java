package d3.buggr.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import d3.buggr.models.Embed;

import java.awt.*;

public class EmbedManager {

    private Embed embed;
    private User user;

    public EmbedManager(Embed embed, User user) {
        this.embed = embed;
        this.user = user;
    }

    public MessageEmbed build() {
        EmbedBuilder builder = new EmbedBuilder();

        if (!embed.title.isBlank()) {builder.setTitle(fixDefaultPlaceholders(embed.title, user));}
        if (!embed.description.isBlank()) {builder.setDescription(fixDefaultPlaceholders(embed.description, user));}
        if (!embed.colors.isBlank()) {builder.setColor(fixColors(embed.colors));}
        if (!embed.footer.isBlank()) {builder.setFooter(fixDefaultPlaceholders(embed.footer, user));}
        if (!embed.thumbnailURL.isBlank()) {builder.setThumbnail(fixDefaultPlaceholders(embed.thumbnailURL, user));}
        if (!embed.imageURL.isBlank()) {builder.setImage(fixDefaultPlaceholders(embed.imageURL, user));}

        return builder.build();
    }

    public String fixDefaultPlaceholders(String text, User user) {
        try {
            return text.replace("{USER}", user.getName()).replace("{PROFILE}", user.getAvatarUrl()).replace("{PING}", user.getAsMention()).replace("%NEWLINE%", "\n");
        } catch (NullPointerException e) {
            ///
        }
        return "ERROR CONTACT DEVELOPER";
    }

    public EmbedManager addPlaceholder(String textToReplace, String placeholder) {
        this.embed.title = embed.title.replace(placeholder, textToReplace);
        this.embed.description = embed.description.replace(placeholder, textToReplace);
        this.embed.footer = embed.footer.replace(placeholder, textToReplace);
        return this;
    }

    private Color fixColors(String colors) {

        String[] customColors = colors.split(",");

        int r = Integer.valueOf(customColors[0]);
        int g = Integer.valueOf(customColors[1]);
        int b = Integer.valueOf(customColors[2]);

        return new Color(r, g, b);

    }

}
