package notifications;

import domain.AbstractVideo;

public interface VideoNotifications {
    String getName();

    void subscribe(VideoObserver user);

    void emit(AbstractVideo video);
}
