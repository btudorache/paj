package tests;

import domain.Genre;
import domain.Movie;
import domain.User;
import notifications.NewVideoNotifications;
import notifications.NotificationServicesEnum;
import org.junit.Test;
import repository.DatabaseRepositoryLoader;
import repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNotifications {

    @Test
    public void testNewVideoNotifications() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        User newUser = new User("new_user", false);
        assertEquals(newUser.getToWatch().size(), 0);
        repository.addUser(newUser, new ArrayList<>(List.of(NotificationServicesEnum.NEW_MOVIE_SERVICE)));

        Movie newMovie = new Movie("Unfrosted", 2024, 93);
        Movie horrorMovie = new Movie("The Shining", 1980, 146);
        repository.addVideo(newMovie);
        repository.addVideo(horrorMovie);

        assertEquals(newUser.getToWatch().size(), 1);
    }

    @Test
    public void testComedyVideoNotifications() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        User newUser = new User("new_user", false);
        assertEquals(newUser.getToWatch().size(), 0);
        repository.addUser(newUser, new ArrayList<>(List.of(NotificationServicesEnum.COMEDY_MOVIE_SERVICE)));

        Movie horrorMovie = new Movie("The Shining", 1980, 146);
        horrorMovie.setGenres(new ArrayList<>(List.of(Genre.THRILLER, Genre.HORROR)));
        Movie comedyMovie = new Movie("Dumb and Dumber", 1994, 107);
        horrorMovie.setGenres(new ArrayList<>(List.of(Genre.COMEDY)));
        repository.addVideo(horrorMovie);
        repository.addVideo(comedyMovie);

        assertEquals(newUser.getToWatch().size(), 1);
    }

    @Test
    public void testRetroVideoNotifications() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        User newUser = new User("new_user", false);
        assertEquals(newUser.getToWatch().size(), 0);
        repository.addUser(newUser, new ArrayList<>(List.of(NotificationServicesEnum.RETRO_MOVIE_SERVICE)));

        Movie newMovie = new Movie("Unfrosted", 2024, 93);
        Movie retroMovie1 = new Movie("The Shining", 1980, 146);
        Movie retroMovie2 = new Movie("Aguirre, the Wrath of God", 1972, 94);
        repository.addVideo(newMovie);
        repository.addVideo(retroMovie1);
        repository.addVideo(retroMovie2);

        assertEquals(newUser.getToWatch().size(), 2);

    }
}
