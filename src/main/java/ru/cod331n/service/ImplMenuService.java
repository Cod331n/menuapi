package ru.cod331n.service;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.cod331n.menu.MenuItem;
import ru.cod331n.menu.model.Menu;
import ru.cod331n.util.ColorUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class ImplMenuService implements MenuService {

    @Override
    public Menu load(Menu menu) {
        renderButtons(menu);
        registerMenuListeners(menu);

        menu.getMenuHolders().forEach(holder -> holder.openInventory(menu.getInventory()));
        return menu;
    }

    @Override
    public void unload(Menu menu) {
        unregisterMenuListeners(menu);
    }

    @Override
    public void close(Menu menu) {
        menu.getMenuHolders().forEach(HumanEntity::closeInventory);
    }

    @Override
    public Menu loadNext(Menu menu) {
        unload(menu);

        if (menu.getMenuAfter() != null) {
            return load(menu.getMenuAfter());
        }

        throw new NoSuchElementException("menu " + menu.getName() + " doesn't have the menu after it");
    }

    @Override
    public Menu loadPrev(Menu menu) {
        unload(menu);

        if (menu.getMenuBefore() != null) {
            return load(menu.getMenuBefore());
        }

        throw new NoSuchElementException("menu " + menu.getName() + " doesn't have the menu before it");
    }

    private void registerMenuListeners(Menu menu) {
        for (MenuItem menuItem : menu.getMenuInterface().getButtons().values()) {
            menu.registerListener(menuItem.getClickListener());
        }
    }

    private void unregisterMenuListeners(Menu menu) {
        for (MenuItem menuItem : menu.getMenuInterface().getButtons().values()) {
            menu.unregisterListener(menuItem.getClickListener());
        }
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
        meta.setDisplayName(ColorUtil.withColor(nameColor + name));
        loreLines.forEach(line -> loreLines.set(loreLines.indexOf(line), ColorUtil.withColor(line)));
        meta.setLore(loreLines);
        return meta;
    }
}
