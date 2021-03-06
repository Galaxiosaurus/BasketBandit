package com.yuuko.modules.developer.commands;

import com.yuuko.events.entity.MessageEvent;
import com.yuuko.modules.Command;
import com.yuuko.modules.audio.handlers.AudioManager;

import java.net.URI;
import java.util.Arrays;

public class LavalinkNodeCommand extends Command {

    public LavalinkNodeCommand() {
        super("lavalink", Arrays.asList("-lavalink <action> <node> <secret>"), 2);
    }

    @Override
    public void onCommand(MessageEvent context) {
        String[] params = context.getParameters().split("\\s+", 3);
        switch(params[0]) {
            case "add" -> AudioManager.LAVALINK.getLavalink().addNode(URI.create(params[1]), params[2]);
            case "remove" -> AudioManager.LAVALINK.getLavalink().removeNode(Integer.parseInt(params[1]));
            default -> {}
        }
    }
}
