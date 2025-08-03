package d3.buggr;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Collection;
import java.util.EnumSet;

public class DJLBuilder {

    private JDA JDA;

    private String Token;

    private Collection<GatewayIntent> GATEWAY_INTENTS;

    private DJLBuilder(String Token) {
        this.Token = Token;
    }

    private DJLBuilder(String Token, Collection<GatewayIntent> GatewayIntents) {
        this.Token = Token;
        this.GATEWAY_INTENTS = GatewayIntents;
    }

    public static DJLBuilder create(String Token) {
        return new DJLBuilder(Token);
    }

    public static DJLBuilder create(String Token, Collection<GatewayIntent> GatewayIntents) {
        return new DJLBuilder(Token, GatewayIntents);
    }

    public DJL start() {
        this.JDA = JDABuilder.create(Token, GATEWAY_INTENTS != null ? GATEWAY_INTENTS : EnumSet.noneOf(GatewayIntent.class)).build();
        return new DJL(this.JDA);
    }


}