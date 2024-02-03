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
    private final ServiceHelper serviceHelper = new ServiceHelper();

    @Override
    public Menu load(Menu menu) {
        serviceHelper.renderButtons(menu);
        serviceHelper.registerMenuListeners(menu);

        menu.getMenuHolders().forEach(holder -> holder.openInventory(menu.getInventory()));
        return menu;
    }

    @Override
    public void unload(Menu menu) {
        serviceHelper.unregisterMenuListeners(menu);
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
}
