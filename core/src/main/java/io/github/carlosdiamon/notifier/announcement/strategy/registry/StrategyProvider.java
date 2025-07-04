package io.github.carlosdiamon.notifier.announcement.strategy.registry;

import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import io.github.carlosdiamon.notifier.announcement.strategy.AnnouncementStrategy;
import io.github.carlosdiamon.notifier.announcement.exception.AnnouncementException;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class StrategyProvider {

	private final Map<Class<? extends AnnouncementComponent>, AnnouncementStrategy<? extends AnnouncementComponent>> strategies;

	public StrategyProvider() {
		this.strategies = new HashMap<>();
	}

	public void register(final @NotNull AnnouncementStrategy<? extends AnnouncementComponent> strategy) {
		strategies.put(strategy.sealed(), strategy);
	}

	public void allRegister(final @NotNull Iterable<AnnouncementStrategy<? extends AnnouncementComponent>> strategies) {
		for (final AnnouncementStrategy<? extends AnnouncementComponent> strategy : strategies) {
			this.strategies.put(strategy.sealed(), strategy);
		}
	}

	public void resolve(
		final @NotNull Audience audience,
		final @NotNull AnnouncementComponent component,
		final @NotNull Formatter formatter,
		final @NotNull TagResolver replacement
	) {
		final Class<? extends AnnouncementComponent> clazz = component.getIdentifier().getType();
		dispatch(audience, component, clazz, formatter, replacement);
	}

	private <C extends AnnouncementComponent> void dispatch(
		final @NotNull Audience audience,
		final @NotNull AnnouncementComponent component,
		final @NotNull Class<C> type,
		final @NotNull Formatter formatter,
		final @NotNull TagResolver replacement
	) {
		final AnnouncementStrategy<C> strategy = getStrategy(type);
		strategy.send(audience, type.cast(component), formatter, replacement);
	}

	// Yes.
	@SuppressWarnings("unchecked")
	private <C extends AnnouncementComponent> @NotNull AnnouncementStrategy<C> getStrategy(final @NotNull Class<C> type) {
		final AnnouncementStrategy<? extends AnnouncementComponent> strategy = strategies.get(type);
		if (strategy == null) {
			throw new AnnouncementException("No strategy registered for type: " + type.getName());
		}
		return (AnnouncementStrategy<C>) strategy;
	}
}