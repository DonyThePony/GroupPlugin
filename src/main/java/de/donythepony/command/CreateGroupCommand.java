package de.donythepony.command;

import de.donythepony.GroupPlugin;
import de.donythepony.group.api.structure.Group;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGroupCommand implements CommandExecutor {

    /**
     * Command to create a new group.
     * A group can only be created if the player is not in a group.
     * @param commandSender
     * @param command
     * @param label
     * @param args args[0]=>GroupName
     * @return true if a group was created
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(GroupPlugin.groupManager.getGroupByPlayer(player) != null) { //Check if player is in a group.
                player.sendMessage("You're already in a group!");
                return true;
            }
            if(args.length > 0){ //Check given params
                String groupName = args[0];
                if(GroupPlugin.groupManager.doesGroupNameExist(groupName)) { //Check if name is already taken
                    player.sendMessage("Your desired group name '"+groupName+"' is already taken.");
                    return true;
                }
                Group group = new Group(groupName, player);
                if (GroupPlugin.groupManager.addGroup(group)) { //Check if the group already exists
                    System.out.println("[GroupPlugin] The group name '"+groupName+"' was created.");
                    return true;
                } else { //Error-Message if the group couldn't be created.
                    player.sendMessage("Your desired group couldn't be created!");
                    System.out.println("[GroupPlugin] The group '"+groupName+"' couldn't be created.");
                    return true;
                }
            } else { //Error-Message Name for group not given.
                player.sendMessage("[GroupPlugin] Please enter a name for your group.");
                return false;
            }
        }
        return false;
    }
}
