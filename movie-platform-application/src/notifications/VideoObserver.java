package notifications;

import domain.AbstractVideo;

public interface VideoObserver {
    void onNewVideo(AbstractVideo video);
}
