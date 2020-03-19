package de.donythepony.inventory;

import de.donythepony.structure.Group;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class GroupViewInventory implements InventoryProvider {

    private final Group group;

    public GroupViewInventory(Group group) {
        this.group = group;
    }

    /**
     * Build menu for Group-Member.
     * With head of every member from the given group.
     * @param player
     * @param inventoryContents
     */
    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        inventoryContents.fillBorders(ClickableItem.empty(new ItemStack(Material.GLASS_PANE)));

        inventoryContents.newIterator("PlayerHeads", SlotIterator.Type.HORIZONTAL, SlotPos.of(1,1)).allowOverride(false);
        SlotIterator it = inventoryContents.iterator("PlayerHeads").get();

        for(Player currentPlayer : group.getAllMembers()) {
            ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) headItemStack.getItemMeta();
            Objects.requireNonNull(skullMeta).setDisplayName(currentPlayer.getDisplayName());
            skullMeta.setOwningPlayer(currentPlayer);
            headItemStack.setItemMeta(skullMeta);

            it.next();
            it.set(ClickableItem.empty(headItemStack));
        }
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
