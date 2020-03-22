package de.donythepony.listener;

import de.donythepony.event.GroupInviteEvent;
import de.donythepony.event.GroupJoinEvent;
import de.donythepony.group.api.structure.Group;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
}
