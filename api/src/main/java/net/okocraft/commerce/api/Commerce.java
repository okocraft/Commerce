package net.okocraft.commerce.api;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public interface Commerce {

    static @NotNull Commerce api() {
        if (APIHolder.instance == null) {
            throw new IllegalStateException("Commerce is not initialized.");
        }
        return APIHolder.instance;
    }

    @NotNull Logger getLogger();
}
