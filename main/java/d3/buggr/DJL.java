package d3.buggr;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import d3.buggr.config.DJLConfig;

import java.util.List;

public class DJL {

    private JDA JDA;

    public DJL(JDA JDA) {
        this.JDA = JDA;
    }

    public DJLConfig getConfigManager() {
        return new DJLConfig();
    }

    public DJL setActivity(Activity.ActivityType activity, String info) {
        this.JDA.getPresence().setActivity(Activity.of(activity, info));
        return this;
    }

    public <T extends ListenerAdapter> DJL addListener(T object) {
        this.JDA.addEventListener(object);
        return this;
    }

    public boolean checkForRole(Member member, String roleID) {
        if (member.getRoles().contains(this.JDA.getRoleById(roleID))) {
            return true;
        }
        return false;
    }

    public DJL shutdown() {
        this.JDA.cancelRequests();
        this.JDA.shutdown();
        return this;
    }

    public Guild getGuildById(String guildID) {
        return this.JDA.getGuildById(guildID);
    }

    public PrivateChannel openPrivateChannelById(String userID) {
        return this.JDA.openPrivateChannelById(userID).complete();
    }

    public List<User> getJDAUsers() {
        return this.JDA.getUsers();
    }

    public TextChannel getTextChannelById(String channelID) {
        return this.JDA.getTextChannelById(channelID);
    }

    public PrivateChannel getPrivateChannelById(String userID) {
        return this.JDA.getPrivateChannelById(userID);
    }

    public ForumChannel getForumChannelById(String forumID) {
        return this.JDA.getForumChannelById(forumID);
    }

    public ThreadChannel getThreadChannelById(String threadID) {
        return this.JDA.getThreadChannelById(threadID);
    }

    public JDA getJDA() {

        if (this.JDA != null) {
            return this.JDA;
        }

        throw new NullPointerException("Jda is null.");

    }

}
