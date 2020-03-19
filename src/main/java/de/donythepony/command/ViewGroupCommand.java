package de.donythepony.command;

import de.donythepony.GroupPlugin;
import de.donythepony.inventory.GroupViewInventory;
import de.donythepony.structure.Group;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewGroupCommand implements CommandExecutor {

    /**
     * Command for Group-View Panel.
     * @param commandSender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Group group = GroupPlugin.groupManager.getGroupByPlayer(player);
            if(group != null) { //Check if Player is in a group
                SmartInventory groupViewInventory = SmartInventory.builder()
                        .id("ViewGroupInventory")
                        .provider(new GroupViewInventory(group))
                        .title(group.getName())
                        .build();

                groupViewInventory.open(player);
            } else { //If Group was not found => Error.
                player.sendMessage("Your aren't in a group.");
                System.out.println("ViewGroupDebug: " + GroupPlugin.groupManager.instanceID);
            }
            return true;
        }

        return false;
    }
}
