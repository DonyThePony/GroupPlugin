package de.donythepony;
import de.donythepony.command.*;
import de.donythepony.listener.GroupEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class GroupPlugin extends JavaPlugin {

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
