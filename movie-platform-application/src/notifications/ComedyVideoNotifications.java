package notifications;

import domain.AbstractVideo;
import domain.Genre;

import java.util.ArrayList;

public class ComedyVideoNotifications extends AbstractNotifications {
    public ComedyVideoNotifications() {
        super(NotificationServicesEnum.COMEDY_MOVIE_SERVICE, new ArrayList<>());
    }

    @Override
    public void emit(AbstractVideo video) {
        if (video.getGenres().contains(Genre.COMEDY)) {
            for (VideoObserver observer : observerList) {
                observer.onNewVideo(video);
            }
        }
    }
}
