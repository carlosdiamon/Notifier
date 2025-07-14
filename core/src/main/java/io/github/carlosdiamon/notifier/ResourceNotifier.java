package io.github.carlosdiamon.notifier;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@SuppressWarnings("unused")
public interface ResourceNotifier extends Notifier {

	void send(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull String path,
		final @NotNull TagResolver... replacements
	);

	default void send(
		final @NotNull Collection<Audience> audiences,
		final @NotNull String mode,
		final @NotNull String path,
		final @NotNull TagResolver... replacements
	) {
		send(Audience.audience(audiences), mode, path, replacements);
	}

	default void send(
		final @NotNull Audience audience,
		final @NotNull String path,
		final @NotNull TagResolver... replacements
	) {
		send(audience, DEFAULT_MODE, path, replacements);
	}

	default void send(
		final @NotNull Collection<Audience> audiences,
		final @NotNull String path,
		final @NotNull TagResolver... replacements
	) {
		send(Audience.audience(audiences), DEFAULT_MODE, path, replacements);
	}
}
