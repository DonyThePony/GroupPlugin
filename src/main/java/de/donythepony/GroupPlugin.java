package de.donythepony;
import de.donythepony.command.*;
import de.donythepony.listener.GroupEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GroupPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[GroupPlugin] Starting...");

        getCommand("createGroup").setExecutor(new CreateGroupCommand());
        getCommand("joinGroup").setExecutor(new JoinGroupCommand());
        getCommand("invitePlayerToGroup").setExecutor(new InviteGroupCommand());
        getCommand("viewGroup").setExecutor(new ViewGroupCommand());

        getServer().getPluginManager().registerEvents(new GroupEventListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[GroupPlugin] Stopping...");
    }
}
