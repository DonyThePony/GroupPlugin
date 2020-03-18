package de.donythepony.structure;

import de.donythepony.event.GroupInviteEvent;
import de.donythepony.util.GroupManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.UUID;

public class Group {

    private GroupMemberList memberList = new GroupMemberList();
    private LinkedList<Player> invitedList = new LinkedList<>();

    private Player leader;
    private String name;
    private int level;
    private int maxSize;
    private UUID id;

    public Group(String name, Player leader) {
        this.name = name;
        this.leader = leader;
        memberList.addPlayer(leader);
        id = UUID.randomUUID();
    }

    public void invitePlayer(Player player) {
        invitedList.add(player);
        GroupInviteEvent inviteEvent = new GroupInviteEvent(player, this);
        Bukkit.getPluginManager().callEvent(inviteEvent);
    }

    public void addPlayer(Player player) {
        invitedList.remove(player);
        memberList.addPlayer(player);
    }

    public void notifyAllMembers(BaseComponent[] message) {
        memberList.notifyAllMembers(message);
    }

    public boolean isPlayerInvited(Player player) {
        return invitedList.contains(player);
    }

    public void declinePlayer(Player player) {
        invitedList.remove(player);
    }

    public boolean hasPlayer(Player player) {
        return memberList.hasPlayer(player);
    }

    public Player getLeader() {
        return leader;
    }

    public void setLeader(Player leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return memberList.getSize();
    }

    public String getId() {
        return id.toString();
    }

    public LinkedList<Player> getAllMembers() {
        return memberList.getAllPlayers();
    }
}
