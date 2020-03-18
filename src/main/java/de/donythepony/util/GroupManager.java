package de.donythepony.util;

import de.donythepony.structure.Group;
import org.bukkit.entity.Player;

import java.util.LinkedList;

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

    public void addGroup(Group group) {
        groupList.add(group);
    }

    public Group getGroupByPlayer(Player player) {
        for(Group group : groupList) {
            if(group.hasPlayer(player)) {
                return group;
            }
        }

        return null;
    }
}
