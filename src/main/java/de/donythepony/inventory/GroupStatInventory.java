package de.donythepony.inventory;

import de.donythepony.group.api.structure.Group;
import de.donythepony.group.api.structure.GroupPlayer;
import de.donythepony.group.api.util.GroupManager;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public class GroupStatInventory implements InventoryProvider {

    private final Player selectedPlayer;
    private final Group group;

    public GroupStatInventory(Player selectedPlayer, Group group) {
        this.selectedPlayer = selectedPlayer;
        this.group = group;
    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        inventoryContents.fillBorders(ClickableItem.empty(new ItemStack(Material.GLASS_PANE)));

        inventoryContents.newIterator("PlayerHeads", SlotIterator.Type.HORIZONTAL, SlotPos.of(1,1)).allowOverride(false);
        SlotIterator it = inventoryContents.iterator("PlayerHeads").get();

        GroupPlayer groupPlayer = group.getGroupPlayerFromPlayer(player);
        List<ItemStack> itemStackList = new LinkedList<>();

        //Exp
        ItemStack expInfo = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta expInfoMeta = expInfo.getItemMeta();
        expInfoMeta.setLore(List.of("Collected Exp: " + groupPlayer.getCollectedExp()));
        expInfoMeta.setDisplayName("Collected Exp");
        expInfo.setItemMeta(expInfoMeta);
        itemStackList.add(expInfo);

        //Kills
        ItemStack killInfo = new ItemStack(Material.NAME_TAG);
        ItemMeta killInfoMeta = killInfo.getItemMeta();
        killInfoMeta.setLore(List.of("Kills: " + groupPlayer.getKills(), "Player-Kills: " + groupPlayer.getPlayerKills()));
        killInfoMeta.setDisplayName("Kill-Stats");
        killInfo.setItemMeta(killInfoMeta);
        itemStackList.add(killInfo);

        for(ItemStack itemStack : itemStackList) {
            it.next();
            it.set(ClickableItem.empty(itemStack));
        }

    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
