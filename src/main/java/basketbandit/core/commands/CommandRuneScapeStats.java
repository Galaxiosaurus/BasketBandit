package basketbandit.core.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CommandRuneScapeStats extends Command {

    CommandRuneScapeStats() {
        super("rsstats", "runescape", null);
    }

    public CommandRuneScapeStats(MessageReceivedEvent e) {
        super("rsstats", "runescape", null);
        executeCommand(e);
    }

    /**
     * Executes command using MessageReceivedEvent e.
     * @param e; MessageReceivedEvent.
     * @return boolean; if the command executed correctly.
     */
    protected boolean executeCommand(MessageReceivedEvent e) {
        String[] commandArray = e.getMessage().getContentRaw().split("\\s+", 2);
        String command = commandArray[0].toLowerCase();
        String player = commandArray[1].toLowerCase();

        Boolean osrs = (command.contains("osstats"));

        try {
            URL url = (!osrs) ? new URL("http://services.runescape.com/m=hiscore/index_lite.ws?player=" + player) : new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + player);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String gameVersion = (osrs) ? "[OSRS]" : "[RS3]";
            StringBuilder statString = new StringBuilder();
            for(String line; (line = reader.readLine()) != null;) {
                statString.append(line);
            }
            String[] skills = statString.toString().split(",");
            StringBuilder messageString = new StringBuilder("```Showing stats for " + player + "! "+ gameVersion +"```" +
                    "```Attack          :: " + skills[3] + "\n"
                    + "Defense         :: " + skills[5] + "\n"
                    + "Strength        :: " + skills[7] + "\n"
                    + "Constitution    :: " + skills[9] + "\n"
                    + "Ranged          :: " + skills[11] + "\n"
                    + "Prayer          :: " + skills[13] + "\n"
                    + "Magic           :: " + skills[15] + "\n"
                    + "Cooking         :: " + skills[17] + "\n"
                    + "Woodcutting     :: " + skills[19] + "\n"
                    + "Fletching       :: " + skills[21] + "\n"
                    + "Fishing         :: " + skills[23] + "\n"
                    + "Firemaking      :: " + skills[25] + "\n"
                    + "Crafting        :: " + skills[27] + "\n"
                    + "Smithing        :: " + skills[29] + "\n"
                    + "Mining          :: " + skills[31] + "\n"
                    + "Herblore        :: " + skills[33] + "\n"
                    + "Agility         :: " + skills[35] + "\n"
                    + "Thieving        :: " + skills[37] + "\n"
                    + "Slayer          :: " + skills[39] + "\n"
                    + "Farming         :: " + skills[41] + "\n"
                    + "Runecrafting    :: " + skills[43] + "\n"
                    + "Hunter          :: " + skills[45] + "\n"
                    + "Construction    :: " + skills[47] + "");
            if(!osrs) {
                messageString.append("\n"+"Summoning       :: " + skills[49] + "\n"
                        + "Dungeoneering   :: " + skills[51] + "\n"
                        + "Divination      :: " + skills[53] + "\n"
                        + "Invention       :: " + skills[55]);
            }
            messageString.append("``````Total Level     :: " + skills[1] + "```");

            e.getTextChannel().sendMessage(messageString).queue();
            return true;
        } catch(Exception r) {
            e.getTextChannel().sendMessage("Oops, looks like I messed up! (Or that account doesn't exist!) <:ErioTouwa:420413779323650050>").queue();
            return false;
        }

    }

}