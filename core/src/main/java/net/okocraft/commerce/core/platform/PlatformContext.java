package net.okocraft.commerce.core.platform;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

public record PlatformContext(@NotNull Logger logger, @NotNull Path dataDirectory) {
}
