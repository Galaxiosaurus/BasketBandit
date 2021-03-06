package com.yuuko.modules.interaction.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.events.entity.MessageEvent;
import com.yuuko.modules.Command;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;
import java.util.List;

public class PatCommand extends Command {
    private static final List<String> interactionImage = Arrays.asList(
            "https://i.imgur.com/2lacG7l.gif",
            "https://i.imgur.com/UWbKpx8.gif",
            "https://i.imgur.com/4ssddEQ.gif",
            "https://i.imgur.com/2k0MFIr.gif",
            "https://i.imgur.com/NNOz81F.gif"
    );

    public PatCommand() {
        super("pat", Arrays.asList("-pat @user"), 1);
    }

    @Override
    public void onCommand(MessageEvent context) {
        if(!context.getMessage().getMentionedMembers().isEmpty()) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setDescription(context.i18n( "target").formatted(context.getMember().getEffectiveName(), context.getMessage().getMentionedMembers().get(0).getEffectiveName()))
                    .setImage(interactionImage.get(getRandom(interactionImage.size())));
            MessageDispatcher.sendMessage(context, embed.build());
        }
    }
}
