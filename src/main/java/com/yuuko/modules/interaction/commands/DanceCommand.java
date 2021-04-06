package com.yuuko.modules.interaction.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.events.entity.MessageEvent;
import com.yuuko.modules.Command;
import com.yuuko.utilities.MessageUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.Arrays;
import java.util.List;

public class DanceCommand extends Command {
    private static final List<String> interactionImage = Arrays.asList(
            "https://i.imgur.com/TwMOUGe.gif",
            "https://i.imgur.com/9uzmcJd.gif",
            "https://i.imgur.com/AgScXfC.gif",
            "https://i.imgur.com/pBm2v7f.gif",
            "https://i.imgur.com/KkdPUnz.gif"
    );

    public DanceCommand() {
        super("dance", 0, -1L, Arrays.asList("-dance", "-dance @user"), false, null);
    }

    @Override
    public void onCommand(MessageEvent context) throws Exception {
        if(MessageUtilities.checkIfUserMentioned(context)) {
            Member target = MessageUtilities.getMentionedMember(context, true);
            if(target != null) {
                EmbedBuilder embed = new EmbedBuilder().setDescription(context.i18n( "target").formatted(context.getMember().getEffectiveName(), target.getEffectiveName())).setImage(interactionImage.get(getRandom(interactionImage.size())));
                MessageDispatcher.sendMessage(context, embed.build());
            }
            return;
        }

        EmbedBuilder embed = new EmbedBuilder().setDescription(context.i18n( "self").formatted(context.getMember().getEffectiveName())).setImage(interactionImage.get(getRandom(interactionImage.size())));
        MessageDispatcher.sendMessage(context, embed.build());
    }
}