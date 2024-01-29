package com.thenodemc.pokeballpackfix;

import net.minecraft.server.v1_16_R3.PacketPlayOutSetSlot;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PackStatusListener implements Listener {

    ItemStack item = createItem(new ItemStack(Material.BARRIER),"Poke Ball Loading...",new String[] {"Please wait."});

    @EventHandler(ignoreCancelled = true)
    public void onResourcePackEvent (PlayerResourcePackStatusEvent e) {
        //On download replace pokeballs in inventory with temporary ghost items
        if (e.getStatus().equals(PlayerResourcePackStatusEvent.Status.ACCEPTED)) {
            for (int i = 0;i < e.getPlayer().getInventory().getContents().length;i++) {
                if (e.getPlayer().getInventory().getItem(i) != null) {
                    if (e.getPlayer().getInventory().getItem(i).getType().equals(Material.valueOf("PIXELMON_POKE_BALL"))) {
                        //Remap bukkit slots to NMS index
                        int index = i;
                        if (index < 9)
                            index = index + 36;
                        else if (index > 35)
                            index = 8 - (index - 36);
                        PacketPlayOutSetSlot pack = new PacketPlayOutSetSlot(0, index, CraftItemStack.asNMSCopy(item));
                        ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(pack);
                    }
                }
            }
        }

        //On finished applying Refresh inventory/show pokeballs
        if (e.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)) {
            e.getPlayer().updateInventory();
        }
    }

    public ItemStack createItem(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }
}
