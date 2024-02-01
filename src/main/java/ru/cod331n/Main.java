package ru.cod331n;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.cod331n.command.MenuCommand;
import ru.cod331n.service.ImplMenuService;
import ru.cod331n.service.MenuService;

@Getter
public class Main extends JavaPlugin {

    public static Main plugin;
    private final MenuService menuService = new ImplMenuService();

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("menu").setExecutor(new MenuCommand());
        System.out.println(Bukkit.getCommandAliases());
        System.out.println("Menus started working");
    }

    @Override
    public void onDisable() {

    }
}