package com.basketbandit.core;

import com.basketbandit.core.modules.Command;
import com.basketbandit.core.modules.Module;
import org.discordbots.api.client.DiscordBotListAPI;

import java.util.LinkedList;
import java.util.List;

public class Cache {
    public static long PING;
    public static int GUILD_COUNT;
    public static int USER_COUNT;
    public static List<Command> COMMANDS;
    public static List<Module> MODULES;
    public static List<String> SETTINGS;
    public static DiscordBotListAPI BOT_LIST;
    public static LinkedList<String> LAST_TEN;
    public static String LATEST_INFO;
    public static String[] STANDARD_STRINGS;
    public static int MESSAGES_PROCESSED;
    public static int COMMANDS_PROCESSED;
    public static int REACTS_PROCESSED;

    static void updatePing() {
        PING = Configuration.BOT.getPing();
    }
}
