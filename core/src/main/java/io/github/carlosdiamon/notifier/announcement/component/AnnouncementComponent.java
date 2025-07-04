package io.github.carlosdiamon.notifier.announcement.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a component that can be sent to an audience.
 */
public interface AnnouncementComponent {


	/**
	 * Returns the unique identifier for this component.
	 *
	 * @return the {@link AnnouncementIdentifier} instance.
	 */
	@NotNull AnnouncementIdentifier getIdentifier();

}