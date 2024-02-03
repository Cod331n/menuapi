package ru.cod331n.service;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.cod331n.menu.MenuItem;
import ru.cod331n.menu.model.Menu;
import ru.cod331n.util.ColorUtil;

import java.util.List;

public class ServiceHelper {
    protected void registerMenuListeners(Menu menu) {
        for (MenuItem menuItem : menu.getMenuInterface().getButtons().values()) {
            menu.registerListener(menuItem.getClickListener());
        }
    }

    protected void unregisterMenuListeners(Menu menu) {
        for (MenuItem menuItem : menu.getMenuInterface().getButtons().values()) {
            menu.unregisterListener(menuItem.getClickListener());
        }
    }

    protected void renderButtons(Menu menu) {
        menu.getMenuInterface().getButtons().forEach((slotId, button) -> {
            menu.getMenuInterface()
                    .getButtons()
                    .values()
                    .forEach(btn -> btn.getIcon()
                            .setItemMeta(fillOutItemMeta(btn.getIcon(), btn.getName(), btn.getNameColor(), btn.getLoreLines())));
            menu.getInventory().setItem(slotId, button.getIcon());
        });
    }

    protected ItemMeta fillOutItemMeta(ItemStack item, String name, String nameColor, List<String> loreLines) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.withColor(nameColor + name));
        loreLines.forEach(line -> loreLines.set(loreLines.indexOf(line), ColorUtil.withColor(line)));
        meta.setLore(loreLines);
        return meta;
    }
}
