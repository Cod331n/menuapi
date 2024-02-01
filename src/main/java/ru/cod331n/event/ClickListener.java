package ru.cod331n.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class ClickListener implements Listener {

    private Consumer<InventoryClickEvent> consumer;

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        consumer.accept(event);
    }
}
