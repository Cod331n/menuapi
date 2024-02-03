package ru.cod331n;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.cod331n.command.MenuCommand;
import ru.cod331n.service.ImplMenuService;
import ru.cod331n.service.MenuService;
import ru.cod331n.service.ServiceHelper;

@Getter
public class Main extends JavaPlugin {

    public static Main plugin;
    private final MenuService menuService = new ImplMenuService();

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("menu").setExecutor(new MenuCommand());
    }

    @Override
    public void onDisable() {

    }
}