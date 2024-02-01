package ru.cod331n.menu.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import ru.cod331n.Main;
import ru.cod331n.event.ClickListener;
import ru.cod331n.menu.ButtonObservable;
import ru.cod331n.menu.MenuInterface;
import ru.cod331n.menu.MenuItem;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true, fluent = false)
public class Menu implements ButtonObservable {
    @NonNull
    private Set<? extends Player> menuHolders;
    @Nullable
    private Menu menuAfter;
    @Nullable
    private Menu menuBefore;
    @NonNull
    private String name;
    @NonNull
    private String title;
    @NonNull
    private InventoryType inventoryType;
    @NonNull
    private MenuInterface menuInterface;
    private Inventory inventory;

    public Menu(@NonNull Set<? extends Player> menuHolders, @Nullable Menu menuAfter, @Nullable Menu menuBefore, @NonNull String name, @NonNull String title, @NonNull InventoryType inventoryType, @NonNull MenuInterface menuInterface) {
        this.menuHolders = menuHolders;
        this.menuAfter = menuAfter;
        this.menuBefore = menuBefore;
        this.name = name;
        this.title = title;
        this.inventoryType = inventoryType;
        this.menuInterface = menuInterface;

        createInventory();
        registerListeners();
    }

    public MenuItem getButtonByName(String name) {
        return menuInterface.getButtons().values().stream()
                .filter(button -> button.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Button '" + name + "' not found in menu '" + this.name + "'"));
    }

    public MenuItem getButtonBySlotId(int slotId) {
        return menuInterface.getButtons().entrySet().stream()
                .filter(entry -> entry.getKey() == slotId)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Button in slot " + slotId + " not found in menu '" + this.name + "'"));
    }

    @Override
    public void registerListeners() {
        for (MenuItem menuItem : menuInterface.getButtons().values()) {
            ClickListener clickListener = menuItem.getClickListener();
            Bukkit.getPluginManager().registerEvents(clickListener, Main.plugin);
        }
    }

    @Override
    public Listener getListener(int slotId) {
        return getButtonBySlotId(slotId).getClickListener();
    }

    @Override
    public Listener getListener(String name) {
        return getButtonByName(name).getClickListener();
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(null, this.inventoryType, this.title);
    }
}
