package repository;

import domain.*;
import notifications.ComedyVideoNotifications;
import notifications.NewVideoNotifications;
import notifications.RetroVideoNotifications;
import notifications.VideoNotifications;
import repository.MovieRepository;

import java.util.*;

public class DatabaseRepositoryLoader {
    public static MovieRepository getSampleRepository() {
        Movie movie1 = new Movie("The Shawshank Redemption", 1994, 142);
        movie1.setGenres(new ArrayList<>(List.of(Genre.THRILLER, Genre.ACTION_ADVENTURE, Genre.DRAMA)));

        Movie movie2 = new Movie("The Godfather", 1972, 175);
        movie2.setGenres(new ArrayList<>(List.of(Genre.THRILLER, Genre.ACTION)));

        Movie movie3 = new Movie("The Dark Knight", 2008, 152);
        movie3.setGenres(new ArrayList<>(List.of(Genre.THRILLER, Genre.ACTION)));

        TVShow show1 = new TVShow("Breaking Bad", 2008, new ArrayList<>(Arrays.asList(new Season(1, 350), new Season(2, 650), new Season(3, 663), new Season(4, 643), new Season(5, 810))));
        show1.setGenres(new ArrayList<>(List.of(Genre.ACTION, Genre.DRAMA)));

        TVShow show2 = new TVShow("Chernobyl", 2019, new ArrayList<>(List.of(new Season(1, 293))));
        show2.setGenres(new ArrayList<>(List.of(Genre.THRILLER, Genre.DRAMA)));

        Actor actor1 = new Actor("Tim Robbins", new ArrayList<>(List.of(movie1)));
        Actor actor2 = new Actor("Morgan Freeman", new ArrayList<>(List.of(movie1, movie3)));
        movie1.setActorList(new ArrayList<>(Arrays.asList(actor1, actor2)));

        Actor actor3 = new Actor("Marlon Brando", new ArrayList<>(List.of(movie2)));
        Actor actor4 = new Actor("Al Pacino", new ArrayList<>(List.of(movie2)));
        movie2.setActorList(new ArrayList<>(Arrays.asList(actor3, actor4)));

        Actor actor5 = new Actor("Christian Bale", new ArrayList<>(List.of(movie3)));
        Actor actor6 = new Actor("Heath Ledger", new ArrayList<>(List.of(movie3)));
        movie2.setActorList(new ArrayList<>(Arrays.asList(actor5, actor6)));

        Actor actor7 = new Actor("Bryan Cranston", new ArrayList<>(List.of(show1)));
        Actor actor8 = new Actor("Aaron Paul", new ArrayList<>(List.of(show1)));
        show1.setActorList(new ArrayList<>(Arrays.asList(actor7, actor8)));

        Actor actor9 = new Actor("Jessie Buckley", new ArrayList<>(List.of(show2)));
        Actor actor10 = new Actor("Jared Harris", new ArrayList<>(List.of(show2)));
        show2.setActorList(new ArrayList<>(Arrays.asList(actor9, actor10)));

        User simpleUser1 = new User("user1_simple", false);
        User simpleUser2 = new User("user2_simple", false);
        User premiumUser1 = new User("user1_premium", true);

        Map<String, AbstractVideo> videoMap = new HashMap<>();
        videoMap.put(movie1.getName(), movie1);
        videoMap.put(movie2.getName(), movie2);
        videoMap.put(movie3.getName(), movie3);
        videoMap.put(show1.getName(), show1);
        videoMap.put(show2.getName(), show2);

        Map<String, Actor> actorMap = new HashMap<>();
        actorMap.put(actor1.getName(), actor1);
        actorMap.put(actor2.getName(), actor2);
        actorMap.put(actor3.getName(), actor3);
        actorMap.put(actor4.getName(), actor4);
        actorMap.put(actor5.getName(), actor5);
        actorMap.put(actor6.getName(), actor6);
        actorMap.put(actor7.getName(), actor7);
        actorMap.put(actor8.getName(), actor8);
        actorMap.put(actor9.getName(), actor9);
        actorMap.put(actor10.getName(), actor10);

        Map<String, User> userMap = new HashMap<>();
        userMap.put(simpleUser1.getName(), simpleUser1);
        userMap.put(simpleUser2.getName(), simpleUser2);
        userMap.put(premiumUser1.getName(), premiumUser1);

        List<VideoNotifications> notificationServices = new ArrayList<>(Arrays.asList(new NewVideoNotifications(), new ComedyVideoNotifications(), new RetroVideoNotifications()));

        return new MovieRepository(videoMap, actorMap, userMap, notificationServices);
    }
}
