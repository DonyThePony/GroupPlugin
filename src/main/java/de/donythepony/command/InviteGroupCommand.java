package de.donythepony.command;

import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InviteGroupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(args.length > 0){
                String playerName = args[0];
                Player target = Bukkit.getPlayer(playerName);

                if(target != null) {
                    Group group = GroupManager.getInstance().getGroupByPlayer(player);
                    if(group != null && group.getLeader().equals(player)) {
                        group.invitePlayer(target);
                    }
                }

                return true;
            } else {
                player.sendMessage("[GroupPlugin] Please enter a name for your group.");
                return false;
            }
        }

        return true;
    }
}
