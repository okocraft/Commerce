package net.okocraft.commerce.api;

import org.jetbrains.annotations.NotNull;

public class APISetter {

    public static void set(@NotNull Commerce instance) {
        APIHolder.instance = instance;
    }

    public static void unset() {
        APIHolder.instance = null;
    }
}
