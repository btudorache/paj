package tests;

import domain.AbstractVideo;
import domain.Actor;
import domain.Genre;
import domain.User;
import org.junit.Test;
import repository.DatabaseRepositoryLoader;
import repository.MovieRepository;
import service.DatabaseService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestReports {

    @Test
    public void testGetPremiumUsers() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        DatabaseService service = new DatabaseService(repository);

        List<User> premiumUsers = service.getPremiumUsers();
        assertEquals(premiumUsers.size(), 1);

        User premiumUser = premiumUsers.get(0);
        assertTrue(premiumUser.isPremiumUser());
    }

    @Test
    public void testGetModernMovies() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        DatabaseService service = new DatabaseService(repository);

        List<AbstractVideo> modernMovies = service.getModernMovies();
        assertEquals(modernMovies.size(), 4);
    }

    @Test
    public void testGetVideosSortedByLongest() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        DatabaseService service = new DatabaseService(repository);

        SortedSet<AbstractVideo> longestVideos = service.getVideosSortedByLongest();
        List<AbstractVideo> sortedArray = new ArrayList<>(longestVideos);
        for (int i = 1; i < sortedArray.size(); i++) {
            assertTrue(sortedArray.get(i).getRuntime() > sortedArray.get(i - 1).getRuntime());
        }
    }

    @Test
    public void testGetVideosSortedByGenre() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        DatabaseService service = new DatabaseService(repository);

        Map<Genre, List<AbstractVideo>> videosByGenre = service.getVideosSortedByGenre();
        assertEquals(videosByGenre.keySet().size(), 4);
        assertEquals(videosByGenre.get(Genre.THRILLER).size(), 4);
        assertEquals(videosByGenre.get(Genre.ACTION).size(), 3);
        assertEquals(videosByGenre.get(Genre.ACTION_ADVENTURE).size(), 1);
        assertEquals(videosByGenre.get(Genre.DRAMA).size(), 3);
    }

    @Test
    public void testGetNumberOfMoviesActedIn() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        DatabaseService service = new DatabaseService(repository);

        Map<Actor, Integer> moviesActedIn = service.getNumberOfMoviesActedIn();
        Map<String, Actor> actorMap = repository.getActorMap();
        Actor actor1 = actorMap.get("Tim Robbins");
        assertTrue(moviesActedIn.containsKey(actor1));
        assertTrue(moviesActedIn.get(actor1) == 1);

        Actor actor2 = actorMap.get("Morgan Freeman");
        assertTrue(moviesActedIn.containsKey(actor2));
        assertTrue(moviesActedIn.get(actor2) == 2);

        Actor actor3 = actorMap.get("Marlon Brando");
        assertTrue(moviesActedIn.containsKey(actor3));
        assertTrue(moviesActedIn.get(actor3) == 1);
    }
}
