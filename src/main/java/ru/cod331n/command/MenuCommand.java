package ru.cod331n.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import ru.cod331n.Main;
import ru.cod331n.event.ClickListener;
import ru.cod331n.menu.MenuInterface;
import ru.cod331n.menu.MenuItem;
import ru.cod331n.menu.model.Menu;
import ru.cod331n.service.MenuService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class MenuCommand implements CommandExecutor {
    private final MenuService menuService = Main.plugin.getMenuService();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // The example of the api usage
        Menu menu1 = new Menu(
                new HashSet<>(Bukkit.getOnlinePlayers()),
                "menu1",
                "menu1",
                InventoryType.CHEST,
                new MenuInterface(
                        Collections.singletonMap(0, new MenuItem(
                                "btnName1",
                                "&a",
                                Arrays.asList("&3Hi", "This is the line", "&1Bro"),
                                new ItemStack(Material.BEACON)
                        ))
                )
        );
        menu1.getButtonByName("btnName").setClickListener(new ClickListener(event -> {
            if (event.isLeftClick()) {
                menuService.loadNext(menu1);
            }
            event.setCancelled(true);
        }, menu1.getButtonSlotIdByName("btnName")));

        Menu menu2 = new Menu(
                new HashSet<>(Bukkit.getOnlinePlayers()),
                "menu2",
                "menu2",
                InventoryType.CHEST,
                new MenuInterface(
                        Collections.singletonMap(1, new MenuItem(
                                "btnName2",
                                "&4",
                                Arrays.asList("&3Hi", "This is the line", "&1Bro"),
                                new ItemStack(Material.ANVIL)
                        ))
                )
        );
        menu2.getButtonByName("btnName2").setClickListener(new ClickListener(event -> {
            if (event.isLeftClick()) {
                menuService.loadPrev(menu2);
            }
            event.setCancelled(true);
        }, menu2.getButtonSlotIdByName("btnName2")));

        menu1.setMenuAfter(menu2);
        menu2.setMenuBefore(menu1);

        menuService.load(menu1);
        return true;
    }
}
