package com.yuuko.core.events.entity;

import com.yuuko.core.commands.Command;
import com.yuuko.core.commands.Module;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class MessageEvent extends GuildMessageReceivedEvent {

    private Module module;
    private Command command;
    private String prefix;
    private String parameters;

    public MessageEvent(GuildMessageReceivedEvent event) {
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());
    }

    public String getPrefix() {
        return prefix;
    }

    public Module getModule() {
        return module;
    }

    public Command getCommand() {
        return command;
    }

    public String getParameters() {
        return parameters;
    }

    /**
     * Sets prefix associated with the message event.
     * Returns MessageEvent object so method can be used as a parameter.
     *
     * @param prefix value to assign to prefix field.
     * @return MessageEvent
     */
    public MessageEvent setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Sets module associated with the message event.
     * Returns MessageEvent object so method can be used as a parameter.
     *
     * @param module value to assign to module field.
     * @return MessageEvent
     */
    public MessageEvent setModule(Module module) {
        this.module = module;
        return this;
    }

    /**
     * Sets command associated with the message event.
     * Returns MessageEvent object so method can be used as a parameter.
     *
     * @param command value to assign to command field.
     * @return MessageEvent
     */
    public MessageEvent setCommand(Command command) {
        this.command = command;
        return this;
    }

    /**
     * Sets parameters associated with the message event.
     * Returns MessageEvent object so method can be used as a parameter.
     *
     * @param parameters value to assign to parameters field.
     * @return MessageEvent
     */
    public MessageEvent setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Checks if the event command has parameters by checking if the
     * parameters field contains a value other than null.
     *
     * @return boolean
     */
    public boolean hasParameters() {
        return parameters != null;
    }
}
