package ru.cod331n.menu;

import org.bukkit.event.Listener;

public interface ButtonObservable {
    void registerListeners();

    Listener getListener(int slotId);
    Listener getListener(String name);
}
