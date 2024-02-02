package ru.cod331n.menu;

import org.bukkit.event.Listener;
import ru.cod331n.event.ClickListener;

public interface ButtonObservable {
    void registerListener(ClickListener clickListener);
    void unregisterListener(ClickListener clickListener);
    Listener getListener(int slotId);
    Listener getListener(String name);
}
