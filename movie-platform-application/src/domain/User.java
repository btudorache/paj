package domain;

import notifications.VideoObserver;

import java.util.ArrayList;
import java.util.List;

public class User implements VideoObserver {
    private final String name;
    private boolean isPremiumUser;

    private int premiumDurationInMonths;
    private final List<AbstractVideo> favoriteVideos;

    private final List<AbstractVideo> toWatch;

    public User(String name, boolean isPremiumUser) {
        this.name = name;
        setUpSubscription(isPremiumUser);
        favoriteVideos = new ArrayList<>();
        toWatch = new ArrayList<>();
    }

    public User(String name, boolean isPremiumUser, List<AbstractVideo> favoriteVideos) {
        this.name = name;
        setUpSubscription(isPremiumUser);
        this.favoriteVideos = favoriteVideos;
        toWatch = new ArrayList<>();
    }

    private void setUpSubscription(boolean isPremiumUser) {
        this.isPremiumUser = isPremiumUser;
        if (isPremiumUser) {
            premiumDurationInMonths = 3;
        } else {
            premiumDurationInMonths = 0;
        }
    }

    public boolean renewSubscription(int credit) {
        if (credit < 50) {
            System.out.println("Subscription not renewed");
            return false;
        } else {
            this.isPremiumUser = true;
            premiumDurationInMonths = 3;
            System.out.println("Subscription renewed");
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isPremiumUser() {
        return isPremiumUser;
    }

    public int getPremiumDurationInMonths() {
        return premiumDurationInMonths;
    }

    public List<AbstractVideo> getFavoriteVideos() {
        return favoriteVideos;
    }

    public List<AbstractVideo> getToWatch() {
        return toWatch;
    }

    @Override
    public void onNewVideo(AbstractVideo video) {
        System.out.println("on new video gets called");
        toWatch.add(video);
    }
}
