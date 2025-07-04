package io.github.carlosdiamon.notifier.formatter;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class AdventureFormatter implements Formatter {

	private final MiniMessage minimessage;

	public AdventureFormatter(final @NotNull MiniMessage minimessage) {
		this.minimessage = minimessage;
	}

	public AdventureFormatter() {
		this.minimessage = MiniMessage.miniMessage();
	}


	@Override
	public @NotNull Component apply(
		final @NotNull Audience audience,
		final @NotNull String raw,
		final @NotNull TagResolver replacement
	) {
		return minimessage.deserialize(raw, replacement);
	}
}
