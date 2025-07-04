package io.github.carlosdiamon.notifier;

import io.github.carlosdiamon.notifier.announcement.Announcement;
import io.github.carlosdiamon.notifier.announcement.AnnouncementImpl;
import io.github.carlosdiamon.notifier.announcement.component.AnnouncementComponent;
import io.github.carlosdiamon.notifier.announcement.component.MessageComponent;
import io.github.carlosdiamon.notifier.component.ActionbarComponentSerializer;
import io.github.carlosdiamon.notifier.component.MessageComponentSerializer;
import io.github.carlosdiamon.notifier.component.TitlesComponentSerializer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public enum AnnouncementSerializer implements TypeSerializer<Announcement> {
	INSTANCE;

	private static final Map<String, TypeSerializer<? extends AnnouncementComponent>> SERIALIZERS;

	static {
		SERIALIZERS = Map.of(
			"message", MessageComponentSerializer.INSTANCE,
			"actionbar", ActionbarComponentSerializer.INSTANCE,
			"titles", TitlesComponentSerializer.INSTANCE
		);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Announcement deserialize(
		final @NotNull Type type,
		final @NotNull ConfigurationNode node
	) throws SerializationException {

		// node: 'name'
		if (node.virtual()) {
			MessageComponent message;

			if (node.isList()) {
				message = new MessageComponent(Objects.requireNonNull(node.getList(String.class)));
			} else {
				message = new MessageComponent(Objects.requireNonNull(node.getString()));
			}

			return new AnnouncementImpl(message);
		}

		// multi
		final List<AnnouncementComponent> components = new ArrayList<>();
		for (Map.Entry<Object, ? extends ConfigurationNode> entry : node.childrenMap().entrySet()) {
			final String key = entry.getKey().toString();
			final ConfigurationNode value = entry.getValue();

			if (value == null || value.virtual()) {
				throw new SerializationException("Missing or virtual config node for key: " + key);
			}

			final TypeSerializer serializer = getSerializer(key);
			components.add((AnnouncementComponent) serializer.deserialize(type, value)); // node: sub
		}

		return new AnnouncementImpl(components);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void serialize(
		final @NotNull Type type,
		final @Nullable Announcement obj,
		final @NotNull ConfigurationNode node
	) throws SerializationException {
		if (obj == null) {
			throw new SerializationException("Announcement object is null.");
		}

		final List<AnnouncementComponent> components = new ArrayList<>(obj.getComponents());

		// node: 'message'
		if (components.size() == 1 && components.get(0) instanceof MessageComponent) {
			final MessageComponent message = (MessageComponent) components.get(0);
			if (message.isSingleMessage()) {
				node.set(String.class, message.getFirstMessage());
			} else {
				node.setList(String.class, message.getMessages());
			}
			return;
		}

		// node: sub:..., ...
		for (final AnnouncementComponent component : components) {
			final TypeSerializer serializer = getSerializer(component.getIdentifier().getKey());
			serializer.serialize(type, component, node);
		}
	}

	@SuppressWarnings("rawtypes")
	private @NotNull TypeSerializer getSerializer(final @NotNull String key) throws SerializationException {
		final TypeSerializer serializer = SERIALIZERS.get(key);

		if (serializer == null) {
			throw new SerializationException("Unknown announcement key: " + key);
		}

		return serializer;
	}
}