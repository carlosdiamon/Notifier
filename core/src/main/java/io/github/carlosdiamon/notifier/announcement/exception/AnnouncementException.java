package io.github.carlosdiamon.notifier.announcement.exception;

public class AnnouncementException extends RuntimeException {

	public AnnouncementException(String message) {
		super(message);
	}

	public AnnouncementException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AnnouncementException(final Throwable cause) {
		super(cause);
	}

	public AnnouncementException(
		final String message,
		final Throwable cause,
		final boolean enableSuppression,
		final boolean writableStackTrace
	) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AnnouncementException() {
	}

}
