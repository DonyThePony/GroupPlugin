package de.donythepony.command;

import de.donythepony.structure.Group;
import de.donythepony.util.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvitePlayerToGroupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length > 0) {
                String playerName = args[0];
                Player targetPlayer = Bukkit.getPlayer(playerName);

                if(targetPlayer != null) {
                    Group group = GroupManager.getInstance().getGroupByPlayer(player);
                    group.invitePlayer(targetPlayer);
                }
            }
        }

        return false;
    }
}
