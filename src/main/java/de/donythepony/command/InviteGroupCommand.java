package de.donythepony.command;

import de.donythepony.GroupPlugin;
import de.donythepony.event.GroupInviteEvent;
import de.donythepony.structure.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InviteGroupCommand implements CommandExecutor {

    /**
     * Command to send an invitation to a target.
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
            if(args.length > 0){
                String playerName = args[0]; //Check if given Player exists
                Player target = Bukkit.getPlayer(playerName);

                if(target != null) {
                    Group group = GroupPlugin.groupManager.getGroupByPlayer(player);
                    if(group != null && group.getLeader().equals(player)) { //Check if group exist and sender equals leader
                        if(!group.invitePlayer(target)) { //Check if invitation was successful
                            player.sendMessage("Player " + target.getDisplayName() + " is already in a group.");
                        } else {
                            GroupInviteEvent inviteEvent = new GroupInviteEvent(player, group);
                            Bukkit.getPluginManager().callEvent(inviteEvent);
                        }
                        return true;
                    }
                } else { //Error-Message Player couldn't be found
                    player.sendMessage("This player does not exist.");
                    return false;
                }
            } else { //Error-Message No player-name was given
                player.sendMessage("[GroupPlugin] Please enter a player name.");
                return false;
            }
            return true;
        }

        return false;
    }
}
