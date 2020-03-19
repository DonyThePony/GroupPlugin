package de.donythepony.structure;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class GroupMemberList {

    private final LinkedList<Player> playerList = new LinkedList<>();

    public int getSize() {
        return playerList.size();
    }

    public void addPlayer(Player player) {
        if(!playerList.contains(player)) {
            playerList.add(player);
        }
    }

    public void notifyAllMembers(BaseComponent[] message) {
        for(Player player : playerList) {
            player.spigot().sendMessage(message);
        }
    }

    public boolean hasPlayer(Player player) {
        return playerList.contains(player);
    }

    public LinkedList<Player> getAllPlayers() {
        return playerList;
    }
}
