package de.donythepony;
import de.donythepony.command.*;
import de.donythepony.group.api.util.GroupManager;
import de.donythepony.listener.GroupEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class GroupPlugin extends JavaPlugin {

    public static GroupManager groupManager = (GroupManager) Bukkit.getServer().getPluginManager().getPlugin("GroupManager");

    @Override
    public void onEnable() {
        System.out.println("[GroupPlugin] Starting...");

        Objects.requireNonNull(getCommand("createGroup")).setExecutor(new CreateGroupCommand());
        Objects.requireNonNull(getCommand("joinGroup")).setExecutor(new JoinGroupCommand());
        Objects.requireNonNull(getCommand("invitePlayerToGroup")).setExecutor(new InviteGroupCommand());
        Objects.requireNonNull(getCommand("viewGroup")).setExecutor(new ViewGroupCommand());

        getServer().getPluginManager().registerEvents(new GroupEventListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[GroupPlugin] Stopping...");
    }
}
