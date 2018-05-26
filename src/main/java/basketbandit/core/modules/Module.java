package basketbandit.core.modules;

import basketbandit.core.Database;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Module {

    private final String moduleName;
    private final String dbModuleName;

    public Module(String moduleName, String dbModuleName) {
        this.moduleName = moduleName;
        this.dbModuleName = dbModuleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getDbModuleName() {
        return dbModuleName;
    }

    public boolean checkModuleSettings(MessageReceivedEvent e) {
        Database db = new Database();

        if(!db.checkModuleSettings(dbModuleName, e.getGuild().getId())) {
            e.getTextChannel().sendMessage("Sorry " + e.getAuthor().getAsMention() + ", " + moduleName + " is disabled.").queue();
            return false;
        } else {
            return true;
        }
    }

    // Abstract method signature to ensure method is implemented.
    protected abstract boolean executeCommand(MessageReceivedEvent e);
}
