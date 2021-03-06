package com.yuuko.modules.audio.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.events.entity.MessageEvent;
import com.yuuko.modules.Command;
import com.yuuko.modules.audio.handlers.AudioManager;
import com.yuuko.modules.audio.handlers.GuildAudioManager;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;

public class LoopCommand extends Command {

    public LoopCommand() {
        super("loop", Arrays.asList("-loop"));
    }

    @Override
    public void onCommand(MessageEvent context) {
        GuildAudioManager manager = AudioManager.getGuildAudioManager(context.getGuild());
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(context.i18n( "title"))
                .setDescription(context.i18n( "desc").formatted(!manager.getScheduler().isLooping()));
        MessageDispatcher.reply(context, embed.build());
        manager.getScheduler().setLooping(!manager.getScheduler().isLooping());
    }

}
