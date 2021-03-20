package com.yuuko.commands.audio.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.commands.Command;
import com.yuuko.commands.audio.handlers.AudioManager;
import com.yuuko.events.entity.MessageEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;

public class PauseCommand extends Command {

    public PauseCommand() {
        super("pause", 0, -1L, Arrays.asList("-pause"), false, null);
    }

    @Override
    public void onCommand(MessageEvent e) throws Exception {
        EmbedBuilder embed = new EmbedBuilder().setTitle("Pausing").setDescription("The player has been paused.");
        MessageDispatcher.reply(e, embed.build());
        AudioManager.getGuildAudioManager(e.getGuild()).getPlayer().setPaused(true);
    }

}
