package com.github.igotyou.FactoryMod.utility;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import vg.civcraft.mc.civmodcore.itemHandling.ItemMap;

public class InventoryHandling {

	public static boolean saveRemoval(Inventory i, ItemMap im) {
		for (ItemStack is : im.getItemStackRepresentation()) {
			if (isTool(is.getType())) {
				ItemMap currentMap = new ItemMap(is);
				boolean taken = false;
				for (ItemStack inventoryStack : i.getContents()) {
					if (inventoryStack == null) {
						continue;
					}
					if (inventoryStack.getType() == is.getType()) {
						ItemMap compareMap = new ItemMap(inventoryStack);
						if (compareMap.hashCode() == currentMap.hashCode()) {
							if (i.removeItem(inventoryStack).values().size() != 0) {

								return false;
							} else {
								taken = true;
								continue;
							}
						}
					}
				}
				if (!taken) {
					return false;
				}
			} else {
				if (i.removeItem(is).values().size() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isTool(Material m) {
		return m.getMaxStackSize() == 1;
	}

}
