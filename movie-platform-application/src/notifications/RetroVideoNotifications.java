package notifications;

import domain.AbstractVideo;

import java.util.ArrayList;
import java.util.List;

public class RetroVideoNotifications extends AbstractNotifications {
    public RetroVideoNotifications() {
        super(NotificationServicesEnum.RETRO_MOVIE_SERVICE, new ArrayList<>());
    }

    @Override
    public void emit(AbstractVideo video) {
        if (video.getReleaseYear() < 1990) {
            for (VideoObserver observer : observerList) {
                observer.onNewVideo(video);
            }
        }
    }
}
