package io.github.carlosdiamon.notifier.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import io.github.carlosdiamon.notifier.announcement.component.MessageComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public enum MessageComponentSerializer implements TypeSerializer<MessageComponent> {
	INSTANCE;

	private static final String MESSAGE = AnnouncementIdentifier.MESSAGE.getKey();

	@Override
	public MessageComponent deserialize(
		final @NotNull Type type,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		if (node.isList()) {
			return new MessageComponent(Objects.requireNonNull(node.getList(String.class)));
		}

		return new MessageComponent(Objects.requireNonNull(node.getString()));
	}

	@Override
	public void serialize(
		final @NotNull Type type,
		final @Nullable MessageComponent obj,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		if (obj == null) {
			return;
		}

		final ConfigurationNode messageNode = node.node(MESSAGE);

		if (obj.isSingleMessage()) {
			messageNode.set(String.class, obj.getFirstMessage());
		} else {
			messageNode.setList(String.class, obj.getMessages());
		}
	}
}
