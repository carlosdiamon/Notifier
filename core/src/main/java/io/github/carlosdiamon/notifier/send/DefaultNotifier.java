package io.github.carlosdiamon.notifier.send;

import io.github.carlosdiamon.notifier.Notifier;
import io.github.carlosdiamon.notifier.announcement.Announcement;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import io.github.carlosdiamon.notifier.announcement.strategy.registry.StrategyProvider;
import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.observer.NotifierObserver;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DefaultNotifier implements Notifier {

	private final Formatter formatter;
	private final NotifierObserver observer;
	private final StrategyProvider strategies;

	public DefaultNotifier(
		final @NotNull StrategyProvider strategies,
		final @NotNull Formatter formatter,
		final @NotNull NotifierObserver observer
	) {
		this.strategies = strategies;
		this.formatter = formatter;
		this.observer = observer;
	}

	public DefaultNotifier(
		final @NotNull Formatter formatter,
		final @NotNull NotifierObserver observer
	) {
		this.strategies = Notifier.createDefaultStrategyProvider();
		this.formatter = formatter;
		this.observer = observer;
	}

	public DefaultNotifier(final @NotNull Formatter formatter) {
		this.strategies = Notifier.createDefaultStrategyProvider();
		this.formatter = formatter;
		this.observer = NotifierObserver.DEFAULT;
	}

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	) {
		final TagResolver replacement = (replacements.length == 0)
		                                ? TagResolver.empty()
		                                : TagResolver.resolver(replacements);

		for (final AnnouncementComponent component : announcement.getComponents()) {
			this.observer.observe(audience, mode, component);
			this.strategies.resolve(audience, component, this.formatter, replacement);
		}
	}
}
