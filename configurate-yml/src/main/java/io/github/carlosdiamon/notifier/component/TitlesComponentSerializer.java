package io.github.carlosdiamon.notifier.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import io.github.carlosdiamon.notifier.announcement.component.TitlesComponent;
import net.kyori.adventure.util.Ticks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public enum TitlesComponentSerializer implements TypeSerializer<TitlesComponent> {
	INSTANCE;

	private static final String TITLES = AnnouncementIdentifier.TITLES.getKey();

	private static final String TITLE = "title";
	private static final String SUBTITLE = "subtitle";
	private static final String FADE_IN = "fade-in";
	private static final String STAY = "stay";
	private static final String FADE_OUT = "fade-out";

	@Override
	public TitlesComponent deserialize(
		final @NotNull Type type,
		final @NotNull ConfigurationNode node
	) throws SerializationException {

		final String title = Objects.requireNonNull(node.node(TITLE).getString());
		final String subtitle = node.node(SUBTITLE).getString();
		// https://minecraft.fandom.com/es/wiki/Comandos/title
		final long fadeInTicks = node.node(FADE_IN).getLong(10);
		final long stayTicks = node.node(STAY).getLong(70);
		final long fadeOutTicks = node.node(FADE_OUT).getLong(20);

		return new TitlesComponent(title, subtitle, Ticks.duration(fadeInTicks), Ticks.duration(stayTicks), Ticks.duration(fadeOutTicks));
	}

	@Override
	public void serialize(
		final @NotNull Type type,
		final @Nullable TitlesComponent obj,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		if (obj == null) {
			return;
		}

		final ConfigurationNode mainNode = node.node(TITLES);
		mainNode.node(TITLE).set(String.class, obj.getTitle());

		if (obj.getSubtitle() != null) {
			mainNode.node(SUBTITLE).set(String.class, obj.getSubtitle());
		}

		mainNode.node(FADE_IN).set(Long.class, obj.getFadeIn().getSeconds() * Ticks.TICKS_PER_SECOND);
		mainNode.node(STAY).set(Long.class, obj.getStay().getSeconds() * Ticks.TICKS_PER_SECOND);
		mainNode.node(FADE_OUT).set(Long.class, obj.getFadeOut().getSeconds() * Ticks.TICKS_PER_SECOND);
	}
}
