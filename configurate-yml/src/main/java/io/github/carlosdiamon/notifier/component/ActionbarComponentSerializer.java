package io.github.carlosdiamon.notifier.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import io.github.carlosdiamon.notifier.announcement.component.ActionbarComponent;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public enum ActionbarComponentSerializer implements TypeSerializer<ActionbarComponent> {
	INSTANCE;

	private static final String ACTIONBAR = AnnouncementIdentifier.ACTIONBAR.getKey();

	@Override
	public ActionbarComponent deserialize(
		final @NotNull Type type,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		return new ActionbarComponent(Objects.requireNonNull(node.getString()));
	}

	@Override
	public void serialize(
		final @NotNull Type type,
		final @Nullable ActionbarComponent obj,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		if (obj == null) {
			return;
		}

		node.node(ACTIONBAR).set(String.class, obj.getMessage());
	}
}
