package service;

import domain.AbstractVideo;
import domain.Actor;
import domain.Genre;
import domain.User;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface Service {
    List<User> getPremiumUsers();

    List<AbstractVideo> getModernMovies();

    SortedSet<AbstractVideo> getVideosSortedByLongest();

    Map<Genre, List<AbstractVideo>> getVideosSortedByGenre();

    Map<Actor, Integer> getNumberOfMoviesActedIn();


}
