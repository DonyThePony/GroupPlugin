package de.donythepony.command;

import de.donythepony.event.GroupDeclineEvent;
import de.donythepony.event.GroupJoinEvent;
import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeclineInvitationGroupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String groupId = args[0];
            Group group = GroupManager.getInstance().getGroupById(groupId);

            if(group != null && group.isPlayerInvited(player)) {
                group.declinePlayer(player);
                GroupDeclineEvent event = new GroupDeclineEvent(player, group);
                Bukkit.getPluginManager().callEvent(event);
                return true;
            } else {
                player.sendMessage("There is no invitation.");
            }
        }

        return false;
    }
}
