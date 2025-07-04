package io.github.carlosdiamon.notifier.observer;

import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

/**
 * Observer for notification modes.
 */
@FunctionalInterface
public interface NotifierObserver {

	NotifierObserver DEFAULT = (audience, mode, component) -> {};

	/**
	 * Observes a notice for the given audience, mode, and component.
	 *
	 * @param audience  the target audience to notify
	 * @param mode      the notice mode
	 * @param component the notice component to deliver
	 */
	void observe(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull AnnouncementComponent component
	);
}
