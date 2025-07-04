package io.github.carlosdiamon.notifier.announcement.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

public class TitlesComponent implements AnnouncementComponent {

	private final String title;
	private final String subtitle;

	private final Duration fadeIn;
	private final Duration stay;
	private final Duration fadeOut;

	public TitlesComponent(
		final @NotNull String title,
		final @Nullable String subtitle,
		final @NotNull Duration fadeIn,
		final @NotNull Duration stay,
		final @NotNull Duration fadeOut
	) {
		this.title = title;
		this.subtitle = subtitle;
		this.fadeIn = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}

	public TitlesComponent(
		final @NotNull String title,
		final @Nullable String subtitle
	) {
		this.title = title;
		this.subtitle = subtitle;
		final Title.Times defaults = Title.DEFAULT_TIMES;

		this.fadeIn = defaults.fadeIn();
		this.stay = defaults.stay();
		this.fadeOut = defaults.fadeOut();
	}

	public TitlesComponent(final @NotNull String title) {
		this(title, null);
	}

	@Override
	public @NotNull AnnouncementIdentifier getIdentifier() {
		return AnnouncementIdentifier.TITLES;
	}

	public @NotNull String getTitle() {
		return this.title;
	}

	public @Nullable String getSubtitle() {
		return this.subtitle;
	}

	public @NotNull Duration getFadeIn() {
		return this.fadeIn;
	}

	public @NotNull Duration getStay() {
		return this.stay;
	}

	public @NotNull Duration getFadeOut() {
		return this.fadeOut;
	}
}
