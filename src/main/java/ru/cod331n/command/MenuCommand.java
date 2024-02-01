package ru.cod331n.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import ru.cod331n.Main;
import ru.cod331n.event.ClickListener;
import ru.cod331n.menu.MenuInterface;
import ru.cod331n.menu.MenuItem;
import ru.cod331n.menu.model.Menu;

import java.util.*;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // The example of the api usage
        Menu menu = new Menu(
                new HashSet<>(Bukkit.getOnlinePlayers()),
                null,
                null,
                "name",
                "title",
                InventoryType.CHEST,
                new MenuInterface(
                        Collections.singletonMap(0, new MenuItem(
                                "name",
                                "&a",
                                Arrays.asList("&3Hi", "This is the line", "&1Bro"),
                                new ItemStack(Material.ANVIL)
                        ))
                )
        );
        menu.getButtonByName("name").setClickListener(new ClickListener(event -> Bukkit.broadcastMessage("Меню открыто")));

        Main.plugin.getMenuService().load(menu);
        return true;
    }
}
