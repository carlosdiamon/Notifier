package io.github.carlosdiamon.notifier.announcement.component;

import io.github.carlosdiamon.notifier.announcement.AnnouncementIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class MessageComponent implements AnnouncementComponent {

	private final List<String> messages;

	public MessageComponent(final @NotNull String message) {
		this.messages = Collections.singletonList(message);
	}

	public MessageComponent(final @NotNull List<String> messages) {
		this.messages = List.copyOf(messages);
	}

	public MessageComponent(final @NotNull String... messages) {
		this.messages = List.of(messages);
	}

	@Override
	public @NotNull AnnouncementIdentifier getIdentifier() {
		return AnnouncementIdentifier.MESSAGE;
	}

	public boolean isSingleMessage() {
		return this.messages.size() == 1;
	}

	public @NotNull String getFirstMessage() {
		if (isEmpty()) {
			return "";
		}

		return this.messages.get(0);
	}

	public boolean isEmpty() {
		return this.messages.isEmpty();
	}

	public @NotNull List<String> getMessages() {
		return this.messages;
	}

}
