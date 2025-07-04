package io.github.carlosdiamon.notifier.announcement.strategy;

import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.announcement.component.MessageComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public class MessageStrategy implements AnnouncementStrategy<MessageComponent> {

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull MessageComponent component,
		final @NotNull Formatter formatter,
		final @NotNull TagResolver replacement
	) {
		for (final String message : component.getMessages()) {
			audience.sendMessage(formatter.apply(audience, message, replacement));
		}
	}

	@Override
	public @NotNull Class<MessageComponent> sealed() {
		return MessageComponent.class;
	}
}
