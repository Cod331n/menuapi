package ru.cod331n.service;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.cod331n.menu.model.Menu;

import java.util.List;

public class ImplMenuService implements MenuService {

    @Override
    public Menu load(Menu menu) {
        renderButtons(menu);

        menu.getMenuHolders().forEach(holder -> holder.openInventory(menu.getInventory()));
        return menu;
    }

    @Override
    public Menu unload(Menu menu) {
        menu.getMenuHolders().forEach(HumanEntity::closeInventory);
        return menu;
    }

    private void renderButtons(Menu menu) {
        menu.getMenuInterface().getButtons().forEach((slotId, button) -> {
            menu.getMenuInterface()
                    .getButtons()
                    .values()
                    .forEach(btn -> btn.getIcon()
                            .setItemMeta(fillOutItemMeta(btn.getIcon(), btn.getName(), btn.getNameColor(), btn.getLoreLines())));
            menu.getInventory().setItem(slotId, button.getIcon());
        });
    }

    private ItemMeta fillOutItemMeta(ItemStack item, String name, String nameColor, List<String> loreLines) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nameColor + name));
        loreLines.forEach(line -> {
            int index = loreLines.indexOf(line);
            loreLines.set(index, ChatColor.translateAlternateColorCodes('&', line));
        });
        meta.setLore(loreLines);
        return meta;
    }
}
