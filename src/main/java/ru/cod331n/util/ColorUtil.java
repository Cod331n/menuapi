package ru.cod331n.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ColorUtil {
    private static final char ALT_COLOR_CHAR = '&';
    public String withColor(String string) {
        return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR, string);
    }
}
