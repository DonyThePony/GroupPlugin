package de.donythepony.event;

import de.donythepony.structure.Group;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GroupDeclineEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player declinedPlayer;
    private final Group group;

    public GroupDeclineEvent(Player declinedPlayer, Group group) {
        this.declinedPlayer = declinedPlayer;
        this.group = group;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getDeclinedPlayer() {
        return declinedPlayer;
    }

    public Group getGroup() {
        return group;
    }
}
