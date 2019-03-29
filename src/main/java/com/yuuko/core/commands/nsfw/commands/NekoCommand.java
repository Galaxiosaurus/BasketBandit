package com.yuuko.core.commands.nsfw.commands;

import com.google.gson.JsonObject;
import com.yuuko.core.Configuration;
import com.yuuko.core.MessageHandler;
import com.yuuko.core.commands.Command;
import com.yuuko.core.commands.nsfw.NSFWModule;
import com.yuuko.core.events.extensions.MessageEvent;
import com.yuuko.core.utilities.json.JsonBuffer;
import net.dv8tion.jda.core.EmbedBuilder;

public class NekoCommand extends Command {

    public NekoCommand() {
        super("neko", NSFWModule.class, 0, new String[]{"-neko", "-neko <type>"}, true, null);
    }

    @Override
    public void onCommand(MessageEvent e) {
        try {
            JsonObject json = new JsonBuffer("https://nekos.life/api/v2/img/" + ((e.hasParameters()) ? e.getCommand()[1] : "lewd"), "default", "default").getAsJsonObject();

            if(json != null && !json.has("msg")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Neko: " + json.get("url").getAsString())
                        .setImage(json.get("url").getAsString())
                        .setFooter(Configuration.STANDARD_STRINGS[1] + e.getMember().getEffectiveName(), e.getAuthor().getEffectiveAvatarUrl());
                MessageHandler.sendMessage(e, embed.build());
            } else {
                EmbedBuilder embed = new EmbedBuilder().setTitle("Invalid Parameter").setDescription("'femdom', 'nekoapi_v3', 'tickle', 'classic', 'ngif', 'erofeet', 'meow', 'erok', 'poke', 'les', 'hololewd', 'lewdk', 'keta', 'feetg', 'nsfw_neko_gif', 'eroyuri', 'kiss', '8ball', 'kuni', 'tits', 'pussy_jpg', 'cum_jpg', 'pussy', 'lewdkemo', 'lizard', 'slap', 'lewd', 'cum', 'cuddle', 'spank', 'smallboobs', 'Random_hentai_gif', 'avatar', 'fox_girl', 'nsfw_avatar', 'hug', 'gecg', 'boobs', 'pat', 'feet', 'smug', 'kemonomimi', 'solog', 'holo', 'wallpaper', 'bj', 'woof', 'yuri', 'trap', 'anal', 'baka', 'blowjob', 'holoero', 'feed', 'neko', 'gasm', 'hentai', 'futanari', 'ero', 'solo', 'waifu', 'pwankg', 'eron', 'erokemo'");
                MessageHandler.sendMessage(e, embed.build());
            }

        } catch(Exception ex) {
            log.error("An error occurred while running the {} class, message: {}", this, ex.getMessage(), ex);
        }
    }

}
