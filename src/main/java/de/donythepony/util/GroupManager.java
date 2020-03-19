package de.donythepony.util;

import de.donythepony.structure.Group;
import org.bukkit.entity.Player;

import java.util.LinkedList;

/**
 * This Utility Class helps to work with groups.
 */
public class GroupManager {

    private static GroupManager instance;

    private LinkedList<Group> groupList = new LinkedList<>();

    public static GroupManager getInstance() {
        if(instance == null) {
            instance = new GroupManager();
        }

        return instance;
    }

    public Group getGroupById(String id) {
        for(Group group : groupList) {
            if(group.getId().equals(id)) {
                return group;
            }
        }

        return null;
    }

    public boolean addGroup(Group group) {
        if(!groupList.contains(group)) {
            groupList.add(group);
            return true;
        }
        return false;
    }

    public Group getGroupByPlayer(Player player) {
        for(Group group : groupList) {
            if(group.hasPlayer(player)) {
                return group;
            }
        }

        return null;
    }

    public boolean doesGroupNameExist(String groupName) {
        return getGroupByName(groupName) != null ? true : false;
    }

    public Group getGroupByName(String groupName) {
        for(Group group : groupList) {
            if(group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }
}
