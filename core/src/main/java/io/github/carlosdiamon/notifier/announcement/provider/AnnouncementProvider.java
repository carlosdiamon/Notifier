package io.github.carlosdiamon.notifier.announcement.provider;

import io.github.carlosdiamon.notifier.announcement.Announcement;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for resolving a {@link Announcement} based on the given {@link Audience} and path.
 */
@FunctionalInterface
public interface AnnouncementProvider {

	/**
	 * Resolves a {@link Announcement} for the specified audience and path.
	 * <p>
	 * Consider using properties from the {@link Audience}, such as {@link net.kyori.adventure.identity.Identity},
	 * to provide context-aware components (e.g., using the audience's Locale or UUID).
	 *
	 * @param audience the target {@link Audience} for which the component is resolved
	 * @param path     the configuration path or key identifying the component
	 * @return the resolved {@link Announcement}
	 */
	@NotNull Announcement resolve(
		final @NotNull Audience audience,
		final @NotNull String path
	);

}
