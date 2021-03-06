package com.yuuko.modules.interaction.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.events.entity.MessageEvent;
import com.yuuko.modules.Command;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;
import java.util.List;

public class CryCommand extends Command {
    private static final List<String> interactionImage = Arrays.asList(
            "https://i.imgur.com/X2AemjJ.gif",
            "https://i.imgur.com/3LAPKgh.gif",
            "https://i.imgur.com/87EBrze.gif",
            "https://i.imgur.com/im5XQUl.gif",
            "https://i.imgur.com/fGaUNGF.gif"
    );

    public CryCommand() {
        super("cry", Arrays.asList("-cry"));
    }

    @Override
    public void onCommand(MessageEvent context) {
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription(context.i18n( "self").formatted(context.getMember().getEffectiveName()))
                .setImage(interactionImage.get(getRandom(interactionImage.size())));
        MessageDispatcher.sendMessage(context, embed.build());
    }
}
