package de.donythepony.event;

import de.donythepony.group.api.structure.Group;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GroupJoinEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player joinedPlayer;
    private final Group group;

    public GroupJoinEvent(Player joinedPlayer, Group group) {
        this.joinedPlayer = joinedPlayer;
        this.group = group;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getJoinedPlayer() {
        return joinedPlayer;
    }

    public Group getGroup() {
        return group;
    }
}
