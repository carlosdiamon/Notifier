package io.github.carlosdiamon.notifier;

import io.github.carlosdiamon.notifier.announcement.Announcement;
import io.github.carlosdiamon.notifier.announcement.strategy.ActionbarStrategy;
import io.github.carlosdiamon.notifier.announcement.strategy.MessageStrategy;
import io.github.carlosdiamon.notifier.announcement.strategy.TitlesStrategy;
import io.github.carlosdiamon.notifier.announcement.strategy.registry.StrategyProvider;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * This interface defines methods for sending a notice to an audience.
 */
@SuppressWarnings("unused")
public interface Notifier {

	String DEFAULT_MODE = "default";

	static @NotNull StrategyProvider createDefaultStrategyProvider() {
		final StrategyProvider provider = new StrategyProvider();
		provider.register(new MessageStrategy());
		provider.register(new ActionbarStrategy());
		provider.register(new TitlesStrategy());
		return provider;
	}

	void send(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	);

	default void send(
		final @NotNull Collection<Audience> audiences,
		final @NotNull String mode,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	) {
		send(Audience.audience(audiences), mode, announcement, replacements);
	}

	default void send(
		final @NotNull Audience audience,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	) {
		send(audience, DEFAULT_MODE, announcement, replacements);
	}

	default void send(
		final @NotNull Collection<Audience> audiences,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	) {
		send(Audience.audience(audiences), DEFAULT_MODE, announcement, replacements);
	}
}
