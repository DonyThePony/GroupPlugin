package de.donythepony.inventory;

import de.donythepony.event.GroupInviteEvent;
import de.donythepony.event.GroupKickEvent;
import de.donythepony.group.api.structure.Group;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Objects;

public class GroupLeaderPanelInventory implements InventoryProvider {

    private final Group group;

    public GroupLeaderPanelInventory(Group group) {
        this.group = group;
    }

    /**
     * Build menu for Group-Leader.
     * With head of every member from the given group.
     * @param groupLeader
     * @param inventoryContents
     */
    @Override
    public void init(Player groupLeader, InventoryContents inventoryContents) {
        if(groupLeader != group.getLeader()) {
            return;
        }

        inventoryContents.fillBorders(ClickableItem.empty(new ItemStack(Material.GLASS_PANE)));

        inventoryContents.newIterator("PlayerHeads", SlotIterator.Type.HORIZONTAL, SlotPos.of(1,1)).allowOverride(false);
        SlotIterator it = inventoryContents.iterator("PlayerHeads").get();

        for(Player currentPlayer : group.getAllMembers()) {
            ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) headItemStack.getItemMeta();

            //Build Item with Head-Skin
            Objects.requireNonNull(skullMeta).setDisplayName(currentPlayer.getDisplayName());
            skullMeta.setOwningPlayer(currentPlayer);
            headItemStack.setItemMeta(skullMeta);

            //Build Lore
            ItemMeta itemMeta = headItemStack.getItemMeta();
            itemMeta.setLore(List.of(ChatColor.WHITE+"Left-Click for stats-menu", ChatColor.WHITE+"Right-Click for kick."));
            headItemStack.setItemMeta(itemMeta);



            it.next();
            it.set(ClickableItem.of(headItemStack, event -> {
                if(event.isRightClick() && currentPlayer != groupLeader) {
                    group.kickPlayer(currentPlayer);
                    GroupKickEvent kickEvent = new GroupKickEvent(currentPlayer, group);
                    Bukkit.getPluginManager().callEvent(kickEvent);
                } else if(event.isLeftClick()) {
                    groupLeader.closeInventory();

                    SmartInventory groupStatInventory = SmartInventory.builder()
                            .id("ViewGroupInventory")
                            .provider(new GroupStatInventory(groupLeader, group))
                            .title(group.getName())
                            .build();

                    groupStatInventory.open(groupLeader);
                }
            }));
        }
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {
        if(player != group.getLeader()) {
            return;
        }
    }
}
