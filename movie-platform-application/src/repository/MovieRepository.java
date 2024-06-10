package repository;

import domain.AbstractVideo;
import domain.Actor;
import domain.User;
import email.Email;
import email.EmailException;
import email.EmailService;
import notifications.VideoNotifications;

import java.util.List;
import java.util.Map;

public class MovieRepository {
    private final Map<String, AbstractVideo> videoMap;

    private final Map<String, Actor> actorMap;

    private final Map<String, User> userMap;


    private final List<VideoNotifications> videoNotificationsServiceList;

    private final EmailService emailService;

    public MovieRepository(Map<String, AbstractVideo> videoMap, Map<String, Actor> actorMap, Map<String, User> userMap, List<VideoNotifications> videoNotificationsServiceList) {
        this.videoMap = videoMap;
        this.actorMap = actorMap;
        this.userMap = userMap;
        this.videoNotificationsServiceList = videoNotificationsServiceList;
        this.emailService = new EmailService();
    }

    public void addUser(User user, List<String> subscribeServiceList) {
        userMap.put(user.getName(), user);

        if (!subscribeServiceList.isEmpty()) {
            videoNotificationsServiceList.stream().filter(service -> subscribeServiceList.contains(service.getName())).forEach(service -> {
                System.out.println("registering user to: " + service.getName());
                service.subscribe(user);
            });
        }
    }

    public void addVideo(AbstractVideo video) {
        videoMap.put(video.getName(), video);

        for (Actor actor : video.getActorList()) {
            if (!video.getActorList().contains(actor)) {
                actor.getFilmography().add(video);
            }
        }

        for (VideoNotifications videoNotificationsService : videoNotificationsServiceList) {
            System.out.println("Emitting from: " + videoNotificationsService.getName());
            videoNotificationsService.emit(video);
        }
    }

    public void addActor(Actor actor) {
        actorMap.put(actor.getName(), actor);

        for (AbstractVideo video : actor.getFilmography()) {
            if (!video.getActorList().contains(actor)) {
                video.getActorList().add(actor);
            }
        }
    }

    public void renewSubscription(String userName, int credit) {
        if (!userMap.containsKey(userName)) {
            System.out.println("User not found");
            return;
        }

        User user = userMap.get(userName);
        boolean isRenewed = user.renewSubscription(credit);
        if (isRenewed) {
            try {
                emailService.sendNotificationEmail(new Email().setTo(user));
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, AbstractVideo> getVideoMap() {
        return videoMap;
    }

    public Map<String, Actor> getActorMap() {
        return actorMap;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public EmailService getEmailService() {
        return emailService;
    }
}
