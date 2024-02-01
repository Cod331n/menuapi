package ru.cod331n.service;

import ru.cod331n.menu.model.Menu;

public interface MenuService {
    Menu load(Menu menu);
    Menu unload(Menu menu);
}
