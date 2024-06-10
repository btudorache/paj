package service;

import domain.AbstractVideo;
import domain.Actor;
import domain.Genre;
import domain.User;
import repository.MovieRepository;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseService implements Service {
    private final MovieRepository repository;
    public DatabaseService(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getPremiumUsers() {
        return repository.getUserMap().values().stream().filter(User::isPremiumUser).collect(Collectors.toList());
    }

    @Override
    public List<AbstractVideo> getModernMovies() {
        return repository.getVideoMap().values().stream().filter(movie -> movie.getReleaseYear() > 1990).collect(Collectors.toList());
    }

    @Override
    public SortedSet<AbstractVideo> getVideosSortedByLongest() {
        SortedSet<AbstractVideo> result = new TreeSet<>(Comparator.comparingInt(AbstractVideo::getRuntime));
        result.addAll(repository.getVideoMap().values());
        return result;
    }

    @Override
    public Map<Genre, List<AbstractVideo>> getVideosSortedByGenre() {
        Map<Genre, List<AbstractVideo>> genreAbstractVideoMap = new HashMap<>();
        for (AbstractVideo video : repository.getVideoMap().values()) {
            for (Genre genre : video.getGenres()) {
                if (!genreAbstractVideoMap.containsKey(genre)) {
                    genreAbstractVideoMap.put(genre, new ArrayList<>(List.of(video)));
                } else {
                    genreAbstractVideoMap.get(genre).add(video);
                }
            }
        }

        return genreAbstractVideoMap;
    }

    @Override
    public Map<Actor, Integer> getNumberOfMoviesActedIn() {
        return repository.getActorMap().values().stream().collect(Collectors.toMap(actor -> actor, actor -> actor.getFilmography().size()));
    }
}
