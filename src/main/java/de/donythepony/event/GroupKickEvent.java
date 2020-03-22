package de.donythepony.event;

import de.donythepony.group.api.structure.Group;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GroupKickEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player invitedPlayer;
    private final Group group;

    public GroupKickEvent(Player invitedPlayer, Group group) {
        this.invitedPlayer = invitedPlayer;
        this.group = group;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getInvitedPlayer() {
        return invitedPlayer;
    }

    public Group getGroup() {
        return group;
    }
}
