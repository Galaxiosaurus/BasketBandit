package com.basketbandit.core.modules;

import com.basketbandit.core.Configuration;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

    private final String commandName;
    private final String commandModule;
    private final String[] commandUsage;
    private final Permission commandPermission;

    public Command(String commandName, String commandModule, String[] commandUsage, Permission commandPermission) {
        this.commandName = commandName;
        this.commandModule = commandModule;
        this.commandUsage = commandUsage;
        this.commandPermission = commandPermission;
    }

    public Command() {
        this.commandName = "";
        this.commandModule = "";
        this.commandUsage = null;
        this.commandPermission = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getGlobalName() {
        return Configuration.GLOBAL_PREFIX + commandName;
    }

    public String getCommandModule() {
        return commandModule;
    }

    public String[] getCommandUsage() {
        return commandUsage;
    }

    public Permission getCommandPermission() {
        return commandPermission;
    }

    // Abstract method signature to ensure method is implemented.
    protected abstract void executeCommand(MessageReceivedEvent e, String[] command);
}