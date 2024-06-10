package factory;

import notifications.AbstractNotifications;
import notifications.ComedyVideoNotifications;
import notifications.NewVideoNotifications;
import notifications.RetroVideoNotifications;

public class NotificationsFactory {
    public static AbstractNotifications newNotificationsService(String accountType) {
        if (accountType == null) {
            return null;
        }

        return switch (accountType.toUpperCase()) {
            case "COMEDY_MOVIE_SERVICE" -> new ComedyVideoNotifications();
            case "NEW_MOVIE_SERVICE" -> new NewVideoNotifications();
            case "RETRO_MOVIE_SERVICE" -> new RetroVideoNotifications();
            default -> null;
        };
    }
}
