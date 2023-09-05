package net.okocraft.commerce.core;

import net.okocraft.commerce.api.APISetter;
import net.okocraft.commerce.core.api.CommerceAPI;
import net.okocraft.commerce.core.platform.PlatformContext;
import org.jetbrains.annotations.NotNull;

public final class CommerceCore {

    private final PlatformContext context;

    public CommerceCore(@NotNull PlatformContext context) {
        this.context = context;
    }

    public void load() {
        APISetter.set(new CommerceAPI(this));
    }

    public void startup() {
    }

    public void shutdown() {
        APISetter.unset();
    }

    public @NotNull PlatformContext context() {
        return context;
    }
}
