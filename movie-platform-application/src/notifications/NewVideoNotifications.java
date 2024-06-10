package notifications;

import domain.AbstractVideo;

import java.util.ArrayList;
import java.util.List;

public class NewVideoNotifications extends AbstractNotifications {
    public NewVideoNotifications() {
        super(NotificationServicesEnum.NEW_MOVIE_SERVICE, new ArrayList<>());
    }

    @Override
    public void emit(AbstractVideo video) {
        if (video.getReleaseYear() >= 2024) {
            for (VideoObserver observer : observerList) {
                observer.onNewVideo(video);
            }
        }
    }
}
