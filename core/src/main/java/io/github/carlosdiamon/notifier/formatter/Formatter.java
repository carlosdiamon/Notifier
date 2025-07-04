package io.github.carlosdiamon.notifier.formatter;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a formatter that produces a {@link Component} from a raw message,
 * an {@link Audience}, and an optional replacement.
 */
@FunctionalInterface
public interface Formatter {

	/**
	 * Formats the raw message.
	 *
	 * @param audience    the target {@link Audience}
	 * @param raw         the raw message string to format
	 * @param replacement the replacement value
	 * @return the formatted {@link Component}.
	 */
	@NotNull Component apply(
		final @NotNull Audience audience,
		final @NotNull String raw,
		final @NotNull TagResolver replacement
	);
}