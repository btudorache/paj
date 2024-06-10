package notifications;

import domain.AbstractVideo;
import domain.Season;

import java.util.List;

public abstract class AbstractNotifications implements VideoNotifications {

    protected String serviceName;
    protected List<VideoObserver> observerList;

    AbstractNotifications(String serviceName, List<VideoObserver> observerList) {
        this.serviceName = serviceName;
        this.observerList = observerList;
    }

    @Override
    public String getName() {
        return serviceName;
    }

    @Override
    public void subscribe(VideoObserver user) {
        System.out.println("subscribing user " + user);
        observerList.add(user);
    }
}
