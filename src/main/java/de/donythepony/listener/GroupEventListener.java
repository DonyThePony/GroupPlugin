package de.donythepony.listener;

import de.donythepony.event.GroupDeclineEvent;
import de.donythepony.event.GroupInviteEvent;
import de.donythepony.event.GroupJoinEvent;
import de.donythepony.structure.Group;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GroupEventListener implements Listener {

    @EventHandler
    public void onGroupInviteEvent(GroupInviteEvent event) {
        Player invitedPlayer = event.getInvitedPlayer();
        Group group = event.getGroup();
        invitedPlayer.sendMessage("You've been invited to the Group '" + group.getName() + "' !");

        TextComponent inviteMessage = new TextComponent("[Join Group]");
        inviteMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/joinGroup " + group.getId()));
        inviteMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to join '" + group.getName() + "'").create()));

        TextComponent declineMessage = new TextComponent("[Decline]");
        declineMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/declineGroupInvitation " + group.getId()));
        declineMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to decline this invitation. '" + group.getName() + "'").create()));

        invitedPlayer.spigot().sendMessage(inviteMessage);
        invitedPlayer.spigot().sendMessage(declineMessage);
    }

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
    public void onGroupDeclineEvent(GroupDeclineEvent event) {
        Player declinedPlayer = event.getDeclinedPlayer();
        Group group = event.getGroup();

        ComponentBuilder message = new ComponentBuilder(declinedPlayer.getDisplayName())
                .color(ChatColor.WHITE).bold(true).underlined(true).append(" Declined the invitation.")
                .color(ChatColor.DARK_RED);

        group.notifyAllMembers(message.create());
    }

}
