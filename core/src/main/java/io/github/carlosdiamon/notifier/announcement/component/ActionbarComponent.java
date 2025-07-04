package io.github.carlosdiamon.notifier.announcement.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import org.jetbrains.annotations.NotNull;

public class ActionbarComponent implements AnnouncementComponent {

	private final String message;

	public ActionbarComponent(final @NotNull String message) {
		this.message = message;
	}

	@Override
	public @NotNull AnnouncementIdentifier getIdentifier() {
		return AnnouncementIdentifier.ACTIONBAR;
	}

	public @NotNull String getMessage() {
		return this.message;
	}

}
