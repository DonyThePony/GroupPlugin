package de.donythepony.listener;

import de.donythepony.GroupPlugin;
import de.donythepony.event.GroupInviteEvent;
import de.donythepony.event.GroupJoinEvent;
import de.donythepony.event.GroupKickEvent;
import de.donythepony.group.api.event.GroupAddExpEvent;
import de.donythepony.group.api.event.GroupDeathEvent;
import de.donythepony.group.api.event.GroupKillEvent;
import de.donythepony.group.api.structure.Group;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class GroupEventListener implements Listener {

    /**
     * Handle Invite.
     * Player who get's invited has to in no group.
     * @param event
     */
    @EventHandler
    public void onGroupInviteEvent(GroupInviteEvent event) {
        Player invitedPlayer = event.getInvitedPlayer();
        Group group = event.getGroup();
        invitedPlayer.sendMessage("You've been invited to the Group '" + group.getName() + "' !");

        TextComponent inviteMessage = new TextComponent("[Accept]");
        inviteMessage.setColor(ChatColor.DARK_PURPLE);
        inviteMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/joinGroup " + group.getId()));
        inviteMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to join '" + group.getName() + "'").create()));

        invitedPlayer.spigot().sendMessage(inviteMessage);
    }

    /**
     * Handle Player event when he joins the group.
     * @param event
     */
    @EventHandler
    public void onGroupJoinEvent(GroupJoinEvent event) {
        Player joinedPlayer = event.getJoinedPlayer();
        Group group = event.getGroup();

        ComponentBuilder message = new ComponentBuilder(joinedPlayer.getDisplayName())
                .color(ChatColor.WHITE).bold(true).underlined(true).append(" Joined our group!")
                .color(ChatColor.DARK_GREEN);

        group.notifyAllMembers(message.create());
    }

    @EventHandler
    public void onGroupKickEvent(GroupKickEvent event) {
        Player kickedPlayer = event.getKickedPlayer();
        Group group = event.getGroup();

        ComponentBuilder kickMessage = new ComponentBuilder("You have been kicked by ")
                .color(ChatColor.RED).bold(true).append(group.getLeader().getDisplayName())
                .color(ChatColor.RED);

        kickedPlayer.spigot().sendMessage(kickMessage.create());

        ComponentBuilder groupMessage = new ComponentBuilder(kickedPlayer.getDisplayName() + " have been kicked by ")
                .color(ChatColor.RED).bold(true).append(group.getLeader().getDisplayName())
                .color(ChatColor.RED);

        group.notifyAllMembers(groupMessage.create());
    }

    @EventHandler
    public void onPlayerCollectExp(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        Group group = GroupPlugin.groupManager.getGroupByPlayer(player);
        if(group != null) {
            GroupAddExpEvent groupAddExpEvent = new GroupAddExpEvent(player, group, event.getAmount());
            Bukkit.getPluginManager().callEvent(groupAddExpEvent);
        }
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if(killer != null) {
            Group group = GroupPlugin.groupManager.getGroupByPlayer(killer);
            if(group != null) {
                Entity victim = event.getEntity();
                GroupKillEvent killEvent = new GroupKillEvent(killer, victim, group);
                Bukkit.getPluginManager().callEvent(killEvent);
            }
        }

        if(event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Group group = GroupPlugin.groupManager.getGroupByPlayer(victim);
            if(group != null) {
                GroupDeathEvent deathEvent = new GroupDeathEvent(victim, group);
                Bukkit.getPluginManager().callEvent(deathEvent);
            }
        }
    }
}
