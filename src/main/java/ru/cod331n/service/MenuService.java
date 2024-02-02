package ru.cod331n.service;

import ru.cod331n.menu.model.Menu;

public interface MenuService {
    Menu load(Menu menu);
    void unload(Menu menu);
    void close(Menu menu);
    Menu loadNext(Menu menu);
    Menu loadPrev(Menu menu);
}
