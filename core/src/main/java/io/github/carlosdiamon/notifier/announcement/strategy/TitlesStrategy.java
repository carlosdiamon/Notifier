package io.github.carlosdiamon.notifier.announcement.strategy;

import io.github.carlosdiamon.notifier.formatter.Formatter;
import io.github.carlosdiamon.notifier.announcement.component.TitlesComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

public class TitlesStrategy implements AnnouncementStrategy<TitlesComponent> {

	@Override
	public void send(
		final @NotNull Audience audience,
		final @NotNull TitlesComponent component,
		final @NotNull Formatter formatter,
		final @NotNull TagResolver replacement
	) {
		final Component title = formatter.apply(audience, component.getTitle(), replacement);

		final String rawSub = component.getSubtitle();

		Component subtitle;
		if (rawSub != null) {
			subtitle = formatter.apply(audience, rawSub, replacement);
		} else {
			subtitle = Component.empty();
		}

		final Title.Times times = Title.Times.times(component.getFadeIn(), component.getStay(), component.getFadeOut());
		audience.showTitle(Title.title(title, subtitle, times));
	}

	@Override
	public @NotNull Class<TitlesComponent> sealed() {
		return TitlesComponent.class;
	}
}
