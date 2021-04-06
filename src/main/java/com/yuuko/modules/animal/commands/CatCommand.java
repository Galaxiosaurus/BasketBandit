package com.yuuko.modules.animal.commands;

import com.yuuko.MessageDispatcher;
import com.yuuko.events.entity.MessageEvent;
import com.yuuko.io.RequestHandler;
import com.yuuko.modules.Command;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;

public class CatCommand extends Command {
    private static final String BASE_URL = "https://api.thecatapi.com/v1/images/search";

    public CatCommand() {
        super("cat", 0, -1L, Arrays.asList("-cat"), false, null);
    }

    @Override
    public void onCommand(MessageEvent context) throws Exception {
        EmbedBuilder embed = new EmbedBuilder().setTitle(context.i18n( "title"))
                .setImage(new RequestHandler(BASE_URL).getJsonArray().get(0).getAsJsonObject().get("url").getAsString());
        MessageDispatcher.reply(context, embed.build());
    }

}