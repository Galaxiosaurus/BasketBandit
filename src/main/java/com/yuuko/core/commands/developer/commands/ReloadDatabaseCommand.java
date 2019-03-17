package com.yuuko.core.commands.developer.commands;

import com.yuuko.core.commands.Command;
import com.yuuko.core.commands.developer.DeveloperModule;
import com.yuuko.core.database.connections.MetricsDatabaseConnection;
import com.yuuko.core.database.connections.SettingsDatabaseConnection;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ReloadDatabaseCommand extends Command {

    public ReloadDatabaseCommand() {
        super("redatabase", DeveloperModule.class, 1, new String[]{"-redatabase"}, false, null);
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] command) {
        try {
            new MetricsDatabaseConnection();
            new SettingsDatabaseConnection();
        } catch(Exception ex) {
            log.error("An error occurred while running the {} class, message: {}", this, ex.getMessage(), ex);
        }
    }
}
