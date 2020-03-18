package de.donythepony.command;

import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGroupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(GroupManager.getInstance().getGroupByPlayer(player) != null) {
                player.sendMessage("You're already in a group!");
                return true;
            }
            if(args.length > 0){
                String groupName = args[0];
                Group group = new Group(groupName, player);
                GroupManager.getInstance().addGroup(group);
                return true;
            } else {
                player.sendMessage("[GroupPlugin] Please enter a name for your group.");
                return false;
            }
        }

        return true;
    }
}
