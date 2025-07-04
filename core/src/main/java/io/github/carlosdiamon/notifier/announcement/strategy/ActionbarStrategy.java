package io.github.carlosdiamon.notifier.announcement.strategy;

import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.announcement.component.ActionbarComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public class ActionbarStrategy implements AnnouncementStrategy<ActionbarComponent> {

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull ActionbarComponent component,
		final @NotNull Formatter formatter,
		final @NotNull TagResolver replacement
	) {
		final Component message = formatter.apply(audience, component.getMessage(), replacement);
		audience.sendActionBar(message);
	}

	@Override
	public @NotNull Class<ActionbarComponent> sealed() {
		return ActionbarComponent.class;
	}
}
