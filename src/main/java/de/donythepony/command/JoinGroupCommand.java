package de.donythepony.command;

import de.donythepony.event.GroupInviteEvent;
import de.donythepony.event.GroupJoinEvent;
import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinGroupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String groupId = args[0];
            Group group = GroupManager.getInstance().getGroupById(groupId);

            if(group != null && group.isPlayerInvited(player)) {
                GroupJoinEvent joinEvent = new GroupJoinEvent(player, group);
                Bukkit.getPluginManager().callEvent(joinEvent);
                group.addPlayer(player);
                return true;
            } else {
                player.sendMessage("There is no invitation.");
            }
        }

        return false;
    }
}
