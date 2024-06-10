package tests;

import domain.AbstractVideo;
import domain.Actor;
import domain.User;
import org.junit.Test;
import repository.DatabaseRepositoryLoader;
import repository.MovieRepository;

import java.util.Map;

import static org.junit.Assert.*;

public class TestRepository {

    @Test
    public void testSampleRepositoryVideos() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        Map<String, AbstractVideo> videoMap = repository.getVideoMap();
        assertEquals(videoMap.values().size(), 5);
        assertTrue(videoMap.containsKey("The Shawshank Redemption"));
        assertTrue(videoMap.containsKey("The Godfather"));
        assertTrue(videoMap.containsKey("The Dark Knight"));
        assertTrue(videoMap.containsKey("Breaking Bad"));
        assertTrue(videoMap.containsKey("Chernobyl"));

        AbstractVideo video1 = videoMap.get("The Shawshank Redemption");
        AbstractVideo video2 = videoMap.get("The Godfather");
        assertEquals(video1.getReleaseYear(), 1994);
        assertEquals(video2.getReleaseYear(), 1972);
    }

    @Test
    public void testSampleRepositoryActors() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        Map<String, Actor> actorMap = repository.getActorMap();
        assertEquals(actorMap.values().size(), 10);
        assertTrue(actorMap.containsKey("Tim Robbins"));
        assertTrue(actorMap.containsKey("Morgan Freeman"));
        assertTrue(actorMap.containsKey("Marlon Brando"));
        assertTrue(actorMap.containsKey("Al Pacino"));
        assertTrue(actorMap.containsKey("Christian Bale"));
        assertTrue(actorMap.containsKey("Heath Ledger"));
        assertTrue(actorMap.containsKey("Bryan Cranston"));
        assertTrue(actorMap.containsKey("Aaron Paul"));
        assertTrue(actorMap.containsKey("Jessie Buckley"));
        assertTrue(actorMap.containsKey("Jared Harris"));

        Actor actor1 = actorMap.get("Tim Robbins");
        Actor actor2 = actorMap.get("Morgan Freeman");
        assertEquals(actor1.getFilmography().size(), 1);
        assertEquals(actor2.getFilmography().size(), 2);
    }

    @Test
    public void testSampleRepositoryUsers() {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        Map<String, User> userMap = repository.getUserMap();
        assertEquals(userMap.values().size(), 3);
        assertTrue(userMap.containsKey("user1_simple"));
        assertTrue(userMap.containsKey("user2_simple"));
        assertTrue(userMap.containsKey("user1_premium"));

        User userSimple = userMap.get("user1_simple");
        assertFalse(userSimple.isPremiumUser());

        User premiumUser = userMap.get("user1_premium");
        assertTrue(premiumUser.isPremiumUser());
    }
}
