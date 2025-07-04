package io.github.carlosdiamon.notifier.announcement;

import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AnnouncementIdentifierImpl implements AnnouncementIdentifier {

	private final String key;
	private final Class<? extends AnnouncementComponent> clazz;

	public AnnouncementIdentifierImpl(
		final @NotNull String key,
		final @NotNull Class<? extends AnnouncementComponent> clazz
	) {
		this.key = key;
		this.clazz = clazz;
	}

	@Override
	public @NotNull String getKey() {
		return key;
	}

	@Override
	public @NotNull Class<? extends AnnouncementComponent> getType() {
		return clazz;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof AnnouncementIdentifierImpl)) return false;
		return Objects.equals(key, ((AnnouncementIdentifierImpl) object).key);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(key);
	}
}
