package io.github.carlosdiamon.notifier.announcement;

import io.github.carlosdiamon.notifier.announcement.component.ActionbarComponent;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import io.github.carlosdiamon.notifier.announcement.component.MessageComponent;
import io.github.carlosdiamon.notifier.announcement.component.TitlesComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an announcement composed of one or more components.
 */
@SuppressWarnings("unused")
public interface Announcement {

	static @NotNull Announcement message(final @NotNull String@NotNull... messages) {
		return new AnnouncementImpl(Collections.singleton(new MessageComponent(messages)));
	}

	static @NotNull Announcement actionbar(final @NotNull String message) {
		return new AnnouncementImpl(new ActionbarComponent(message));
	}

	static @NotNull Announcement titles(
		final @NotNull String title,
		final @Nullable String subtitle,
		final @NotNull Duration fadeIn,
		final @NotNull Duration stay,
		final @NotNull Duration fadeOut
	) {
		return new AnnouncementImpl(new TitlesComponent(
			title,
			subtitle,
			fadeIn,
			stay,
			fadeOut
		));
	}

	static @NotNull Announcement titles(
		final @NotNull String title,
		final @Nullable String subtitle
	) {
		return new AnnouncementImpl(new TitlesComponent(title, subtitle));
	}

	static @NotNull Announcement titles(final @NotNull String title) {
		return new AnnouncementImpl(new TitlesComponent(title));
	}

	static @NotNull Announcement empty() {
		return AnnouncementImpl.EMPTY;
	}

	static @NotNull Builder create() {
		return new Builder();
	}

	/**
	 * Returns all components of this announcement.
	 *
	 * @return a collection of announcement components
	 */
	@NotNull Collection<AnnouncementComponent> getComponents();


	final class Builder {
		private final Map<AnnouncementIdentifier, AnnouncementComponent> components;

		public Builder() {
			this.components = new LinkedHashMap<>();
		}

		public @NotNull Builder message(final @NotNull String@NotNull... message) {
			return putComponent(new MessageComponent(message));
		}

		public @NotNull Builder actionbar(final @NotNull String message) {
			return putComponent(new ActionbarComponent(message));
		}

		public @NotNull Builder titles(
			final @NotNull String title,
			final @Nullable String subtitle,
			final @NotNull Duration fadeIn,
			final @NotNull Duration stay,
			final @NotNull Duration fadeOut
		) {
			return putComponent(new TitlesComponent(
				title,
				subtitle,
				fadeIn,
				stay,
				fadeOut
			));
		}

		public @NotNull Builder titles(
			final @NotNull String title,
			final @Nullable String subtitle
		) {
			return putComponent(new TitlesComponent(
				title,
				subtitle
			));
		}

		public @NotNull Builder titles(final @NotNull String title) {
			return putComponent(new TitlesComponent(title));
		}

		private @NotNull Builder putComponent(final @NotNull AnnouncementComponent component) {
			this.components.put(component.getIdentifier(), component);
			return this;
		}

		public @NotNull Announcement build() {
			return new AnnouncementImpl(components.values());
		}

	}
}
