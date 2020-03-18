package de.donythepony.command;

import de.donythepony.inventory.GroupViewInventory;
import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewGroupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Group group = GroupManager.getInstance().getGroupByPlayer(player);
            if(group != null) {
                SmartInventory groupViewInventory = SmartInventory.builder()
                        .id("ViewGroupInventory")
                        .provider(new GroupViewInventory(group))
                        .title(group.getName())
                        .build();

                groupViewInventory.open(player);
            } else {
                player.sendMessage("Your aren't in a group.");
            }
        }

        return true;
    }
}
