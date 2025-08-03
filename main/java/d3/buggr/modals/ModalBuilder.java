package d3.buggr.modals;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class ModalBuilder {

    private Modal.Builder modal;

    public ModalBuilder create(String ID, String title) {

        this.modal = Modal.create(ID, title);

        return this;
    }

    public ModalBuilder addComponent(String ID, String label, TextInputStyle style) {

        TextInput input = TextInput.create(ID, label, style).build();
        this.modal.addComponents(ActionRow.of(input));

        return this;

    }

    public Modal build() {
        return this.modal.build();
    }

}
