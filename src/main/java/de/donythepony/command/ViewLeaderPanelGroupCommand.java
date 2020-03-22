package de.donythepony.command;

import de.donythepony.GroupPlugin;
import de.donythepony.group.api.structure.Group;
import de.donythepony.inventory.GroupLeaderPanelInventory;
import de.donythepony.inventory.GroupViewInventory;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewLeaderPanelGroupCommand implements CommandExecutor {

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
            if(group != null && group.getLeader() == player) { //Check if Player is in a group
                SmartInventory groupViewInventory = SmartInventory.builder()
                        .id("GroupLeaderPanelInventory")
                        .provider(new GroupLeaderPanelInventory(group))
                        .title(group.getName() + " Leader-Panel")
                        .build();

                groupViewInventory.open(player);
            } else { //If Group was not found => Error.
                if(group == null) {
                    player.sendMessage("Your aren't in a group.");
                } else {
                    player.sendMessage("Your are not the leader.");
                }
            }
            return true;
        }

        return false;
    }
}
