package d3.buggr.selectMenu;

import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.List;

public class MenuBuilder {

    private StringSelectMenu.Builder menu;

    public MenuBuilder createMenu(String ID) {
        this.menu = StringSelectMenu.create(ID);

        return this;
    }

    public MenuBuilder addOption(String label, String value) {
        this.menu.addOption(label, value);
        return this;
    }

    public MenuBuilder addOption(String label, String value, String description) {
        this.menu.addOption(label, value, description);
        return this;
    }

    public MenuBuilder addOptions(List<SelectOption> options) {
        this.menu.addOptions(options);
        return this;
    }

    public StringSelectMenu build() {
        return this.menu.build();
    }
}
