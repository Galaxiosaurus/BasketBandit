package com.yuuko.core.modules.core.commands;

import com.yuuko.core.Cache;
import com.yuuko.core.Configuration;
import com.yuuko.core.Statistics;
import com.yuuko.core.modules.Command;
import com.yuuko.core.utils.MessageHandler;
import com.yuuko.core.utils.Utils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandAbout extends Command {

    public CommandAbout() {
        super("about", "com.yuuko.core.modules.core.ModuleCore", 0, new String[]{"-about"}, null);
    }

    @Override
    public void executeCommand(MessageReceivedEvent e, String[] command) {
        User bot = e.getGuild().getMemberById(420682957007880223L).getUser();

        EmbedBuilder about = new EmbedBuilder()
                .setAuthor(bot.getName() + "#" + bot.getDiscriminator(), null, bot.getAvatarUrl())
                .setDescription(bot.getName() + ", programmed in " +
                        "[Java](https://www.oracle.com/uk/java/index.html) using [Maven](https://maven.apache.org/) " +
                        "for dependencies. Built upon the [JDA](https://github.com/DV8FromTheWorld/JDA) and [LavaPlayer](https://github.com/sedmelluq/lavaplayer) libraries. " +
                        "If you would like me on your guild [invite me!](https://discordapp.com/api/oauth2/authorize?client_id=420682957007880223&permissions=8&scope=bot)"
                )
                .setThumbnail(bot.getAvatarUrl())
                .addField("Author", "[0x00000000#0001](https://github.com/BasketBandit/)", true)
                .addField("Version", Configuration.VERSION, true)
                .addField("Servers", Statistics.GUILD_COUNT + "", true)
                .addField("Commands", Cache.COMMANDS.size() + "", true)
                .addField("Invocation", Configuration.GLOBAL_PREFIX + ", " + Utils.getServerPrefix(e.getGuild().getId()), true)
                .addField("Uptime", Statistics.RUNTIME.toString(), true)
                .addField("Ping", Statistics.PING + "", true);
        MessageHandler.sendMessage(e, about.build());
    }

}