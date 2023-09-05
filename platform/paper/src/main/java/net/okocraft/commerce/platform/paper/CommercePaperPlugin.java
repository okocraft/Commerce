package net.okocraft.commerce.platform.paper;

import net.okocraft.commerce.core.CommerceCore;
import net.okocraft.commerce.core.platform.PlatformContext;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class CommercePaperPlugin extends JavaPlugin {

    private final PlatformContext context = new PlatformContext(getSLF4JLogger(), getDataFolder().toPath());

    private CommerceCore core;

    @Override
    public void onLoad() {
        this.core = new CommerceCore(context);

        try {
            core.load();
        } catch (Throwable e) {
            core = null;
            getSLF4JLogger().error("Failed to load Commerce.", e);
        }
    }

    @Override
    public void onEnable() {
        if (core == null) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        core.startup();
    }

    @Override
    public void onDisable() {
        if (core == null) {
            return;
        }

        core.shutdown();
    }
}
