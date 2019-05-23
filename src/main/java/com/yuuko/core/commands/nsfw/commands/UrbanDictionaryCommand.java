package com.yuuko.core.commands.nsfw.commands;

import com.google.gson.JsonObject;
import com.yuuko.core.MessageHandler;
import com.yuuko.core.commands.Command;
import com.yuuko.core.commands.nsfw.NsfwModule;
import com.yuuko.core.events.extensions.MessageEvent;
import com.yuuko.core.utilities.json.JsonBuffer;
import net.dv8tion.jda.core.EmbedBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class UrbanDictionaryCommand extends Command {

    public UrbanDictionaryCommand() {
        super("urban", NsfwModule.class, 1, Arrays.asList("-urban <term>"), true, null);
    }

    @Override
    public void onCommand(MessageEvent e) {
        JsonObject json = new JsonBuffer("https://api.urbandictionary.com/v0/define?term=" + e.getCommand().get(1).replace(" ", "%20"), "default", "default").getAsJsonObject();

        if(json.get("list").getAsJsonArray().size() < 1) {
            EmbedBuilder embed = new EmbedBuilder().setTitle("No Results").setDescription("Search for **_" + e.getCommand().get(1) + "_** produced no results.");
            MessageHandler.sendMessage(e, embed.build());
            return;
        }

        JsonObject data = json.get("list").getAsJsonArray().get(0).getAsJsonObject();

        double thumbsUp = data.get("thumbs_up").getAsDouble();
        double thumbsDown = data.get("thumbs_down").getAsDouble();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(data.get("word").getAsString(), data.get("permalink").getAsString())
                .setDescription(data.get("definition").getAsString().replace("[", "").replace("]", ""))
                .addField("Example", data.get("example").getAsString().replace("[", "").replace("]", ""), false)
                .setFooter("\uD83D\uDC4D " + data.get("thumbs_up").getAsString() + " \uD83D\uDC4E " + data.get("thumbs_down").getAsString() + " \uD83D\uDCCC " + new BigDecimal((thumbsUp/(thumbsUp+thumbsDown))*100).setScale(2, RoundingMode.HALF_UP) + "%", null);
        MessageHandler.sendMessage(e, embed.build());
    }

}