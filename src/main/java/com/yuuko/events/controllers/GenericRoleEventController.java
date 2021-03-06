package com.yuuko.events.controllers;

import com.yuuko.modules.utility.commands.ReactionRoleCommand;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericRoleEventController {
    private static final Logger log = LoggerFactory.getLogger(GenericEmoteEventController.class);

    public GenericRoleEventController(GenericRoleEvent event) {
        if(event instanceof RoleDeleteEvent) {
            roleDeleteEvent((RoleDeleteEvent) event);
        }
    }

    public void roleDeleteEvent(RoleDeleteEvent event) {
        ReactionRoleCommand.DatabaseInterface.removeReactionRole(event.getRole());
    }
}
