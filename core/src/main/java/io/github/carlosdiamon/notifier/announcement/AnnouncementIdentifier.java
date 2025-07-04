package io.github.carlosdiamon.notifier.announcement;

import io.github.carlosdiamon.notifier.announcement.component.ActionbarComponent;
import io.github.carlosdiamon.notifier.announcement.component.MessageComponent;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import io.github.carlosdiamon.notifier.announcement.component.TitlesComponent;
import org.jetbrains.annotations.NotNull;


/**
 * Represents a unique, type-safe identifier for a specific kind of {@link AnnouncementComponent}.
 */
public interface AnnouncementIdentifier {

	AnnouncementIdentifier MESSAGE = of("message", MessageComponent.class);
	AnnouncementIdentifier ACTIONBAR = of("actionbar", ActionbarComponent.class);
	AnnouncementIdentifier TITLES = of("titles", TitlesComponent.class);

	static @NotNull AnnouncementIdentifier of(
		final @NotNull String key,
		final @NotNull Class<? extends AnnouncementComponent> clazz
	) {
		return new AnnouncementIdentifierImpl(key, clazz);
	}

	/**
	 * Returns the unique key associated with this identifier.
	 *
	 * @return the string key
	 */
	@NotNull String getKey();

	/**
	 * Returns the class of the {@link AnnouncementComponent} associated with this identifier.
	 *
	 * @return the component class
	 */
	@NotNull Class<? extends AnnouncementComponent> getType();

}
