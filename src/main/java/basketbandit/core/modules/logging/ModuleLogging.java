package basketbandit.core.modules.logging;

import basketbandit.core.Configuration;
import basketbandit.core.modules.Module;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class ModuleLogging extends Module {

    private long startExecutionNano;

    public ModuleLogging() {
        super("ModuleLogging", "moduleLogging");
    }

    public ModuleLogging(MessageReceivedEvent e, long startExecutionNano, String prefix) {
        super("ModuleLogging", "moduleLogging");

        // We don't check inside the class if the module is active because the module
        // because it would be checked after every message and the Module super class
        // displays a message if it is off.
        this.startExecutionNano = startExecutionNano;
        executeCommand(e, prefix);
    }

    protected void executeCommand(MessageReceivedEvent e, String prefix) {
        try {
            List<TextChannel> log = e.getGuild().getTextChannelsByName("command-log", true);

            if(log.size() > 0) {
                long totalExecutionMilli = (System.nanoTime() - startExecutionNano)/1000000;
                log.get(0).sendMessage("```" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " used command " + e.getMessage().getContentDisplay() + " in " + e.getMessage().getChannel().getName() + ". (Execution time: "+ totalExecutionMilli +"ms)```").queue();
            }
        } catch(Exception ex) {
            e.getTextChannel().sendMessage("This server has logging enabled but has no 'command-log' text channel, please add that channel or disable the module by typing the '" + Configuration.GLOBAL_PREFIX + "module logging' command to stop this message.").queue();
        }
    }
}