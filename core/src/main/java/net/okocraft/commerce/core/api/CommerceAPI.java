package net.okocraft.commerce.core.api;

import net.okocraft.commerce.api.Commerce;
import net.okocraft.commerce.core.CommerceCore;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class CommerceAPI implements Commerce {

    private final CommerceCore core;

    public CommerceAPI(@NotNull CommerceCore core) {
        this.core = core;
    }

    @Override
    public @NotNull Logger getLogger() {
        return core.context().logger();
    }
}
