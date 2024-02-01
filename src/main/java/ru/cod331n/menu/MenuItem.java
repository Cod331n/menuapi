package ru.cod331n.menu;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import ru.cod331n.Main;
import ru.cod331n.event.ClickListener;

import java.util.List;

@Data
@Accessors(chain = true, fluent = false)
public class MenuItem {
    @NonNull
    private String name;
    @NonNull
    private String nameColor;
    @NonNull
    private List<String> loreLines;
    @NonNull
    private ItemStack icon;
    // Canceled by default
    private ClickListener clickListener = new ClickListener(event -> event.setCancelled(true));
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
        Bukkit.getPluginManager().registerEvents(this.clickListener, Main.plugin);
    }
}
