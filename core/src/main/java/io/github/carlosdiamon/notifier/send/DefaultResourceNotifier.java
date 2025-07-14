package io.github.carlosdiamon.notifier.send;

import io.github.carlosdiamon.notifier.Notifier;
import io.github.carlosdiamon.notifier.ResourceNotifier;
import io.github.carlosdiamon.notifier.announcement.Announcement;
import io.github.carlosdiamon.notifier.announcement.provider.AnnouncementProvider;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DefaultResourceNotifier implements ResourceNotifier {

	private final Notifier delegate;
	private final AnnouncementProvider provider;

	public DefaultResourceNotifier(
		final @NotNull Notifier delegate,
		final @NotNull AnnouncementProvider provider
	) {
		this.delegate = delegate;
		this.provider = provider;
	}

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull String path,
		final @NotNull TagResolver... replacements
	) {
		audience.forEachAudience(child -> {
			final Announcement announcement = this.provider.resolve(child, path);
			this.delegate.send(child, mode, announcement, replacements);
		});
	}

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull String mode,
		final @NotNull Announcement announcement,
		final @NotNull TagResolver... replacements
	) {
		this.delegate.send(audience, mode, announcement, replacements);
	}
}
