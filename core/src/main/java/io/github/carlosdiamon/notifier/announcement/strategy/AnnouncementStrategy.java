package io.github.carlosdiamon.notifier.announcement.strategy;

import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

/**
 * Defines a strategy for sending a {@link AnnouncementComponent} to an {@link Audience}.
 * @param <C> the type of {@link AnnouncementComponent} this strategy handles
 */
public interface AnnouncementStrategy<C extends AnnouncementComponent> {

	/**
	 * Sends the given notice component to the specified audience using the provided formatter and replacement.
	 *
	 * @param audience    the target audience to send the notice to
	 * @param component   the notice component to be sent
	 * @param formatter   the formatter to apply to the message
	 * @param replacement the replacement object for formatting
	 */
	void send(
		@NotNull Audience audience,
		@NotNull C component,
		@NotNull Formatter formatter,
		@NotNull TagResolver replacement
	);

	/**
	 * Return the class of the component to which this strategy will be applied.
	 *
	 * @return the associated class
	 */
	@NotNull Class<C> sealed();
}