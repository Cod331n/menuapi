package ru.cod331n.menu;

import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Data
public class MenuInterface {
    @NonNull
    private Map<Integer, MenuItem> buttons;
}
