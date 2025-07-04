package io.github.carlosdiamon.notifier.announcement;

import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AnnouncementImpl implements Announcement {

	private final List<AnnouncementComponent> components;

	public AnnouncementImpl(final @NotNull AnnouncementComponent component) {
		this.components = Collections.singletonList(component);
	}

	public AnnouncementImpl(final Collection<AnnouncementComponent> components) {
		this.components = List.copyOf(components);
	}

	@Override
	public @NotNull Collection<AnnouncementComponent> getComponents() {
		return components;
	}
}
