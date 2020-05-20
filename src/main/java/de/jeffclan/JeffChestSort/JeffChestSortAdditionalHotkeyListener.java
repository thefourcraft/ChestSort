package de.jeffclan.JeffChestSort;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class JeffChestSortAdditionalHotkeyListener implements Listener {
	
	JeffChestSortPlugin plugin;
	
	public JeffChestSortAdditionalHotkeyListener(JeffChestSortPlugin jeffChestSortPlugin) {
		this.plugin = jeffChestSortPlugin;
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		// Only continue if clicked outside of the chest
		if(e.getClickedInventory()!=null) {
			return;
		}
		if(e.getInventory().getType() != InventoryType.CHEST) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		
		if(!p.hasPermission("chestsort.use")) return;
		
		plugin.registerPlayerIfNeeded(p);
		JeffChestSortPlayerSetting setting = plugin.perPlayerSettings.get(p.getUniqueId().toString());
		
		if(e.isLeftClick() && setting.leftClick) {
			de.jeffclan.utils.InventoryHelper.stuffPlayerInventoryIntoAnother(p.getInventory(), e.getInventory());
		} else if(e.isRightClick() && setting.rightClick) {
			de.jeffclan.utils.InventoryHelper.stuffInventoryIntoAnother(e.getInventory(), p.getInventory());
		}
	}

}
