package com.basketbandit.core.modules.audio.commands;

import com.basketbandit.core.Configuration;
import com.basketbandit.core.modules.Command;
import com.basketbandit.core.modules.audio.handlers.AudioManagerManager;
import com.basketbandit.core.modules.audio.handlers.GuildAudioManager;
import com.basketbandit.core.utils.MessageHandler;
import com.basketbandit.core.utils.Utils;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandQueue extends Command {

    public CommandQueue() {
        super("queue", "com.basketbandit.core.modules.audio.ModuleAudio", 0, new String[]{"-queue"}, null);
    }

    @Override
    public void executeCommand(MessageReceivedEvent e, String[] command) {
        GuildAudioManager manager = AudioManagerManager.getGuildAudioManager(e.getGuild().getId());

        try {
            synchronized(manager.scheduler.queue) {
                StringBuilder queue = new StringBuilder();
                int i = 1;

                for(AudioTrack track : manager.scheduler.queue) {
                    queue.append("`").append(i).append(":` ").append(track.getInfo().title).append(" · (").append(Utils.getTimestamp(track.getInfo().length)).append(") \n");
                    i++;
                    if(i > 10) {
                        break;
                    }
                }
                i--;

                if(i > 0) {
                    EmbedBuilder nextTracks = new EmbedBuilder()
                            .setTitle("Here are the next  + i +  tracks in the queue:")
                            .setDescription(queue.toString())
                            .setFooter(Configuration.VERSION + " ·  Requested by " + e.getAuthor().getName(), e.getGuild().getMemberById(Configuration.BOT_ID).getUser().getAvatarUrl());
                    MessageHandler.sendMessage(e, nextTracks.build());

                } else {
                    EmbedBuilder embed = new EmbedBuilder().setTitle("The queue is currently empty.");
                    MessageHandler.sendMessage(e, embed.build());
                }
            }
        } catch(Exception ex) {
            Utils.sendException(ex, e.getMessage().getContentRaw());
        }
    }

}
