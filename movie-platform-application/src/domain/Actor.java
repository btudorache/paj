package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actor {
    private final String name;


    private List<AbstractVideo> filmography;

    public Actor(String name) {
        this.name = name;
        filmography = new ArrayList<>();
    }

    public Actor(String name, List<AbstractVideo> filmography) {
        this.name = name;
        this.filmography = filmography;
    }

    public void addToFilmography(AbstractVideo video) {
        filmography.add(video);
    }

    public String getName() {
        return name;
    }

    public List<AbstractVideo> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<AbstractVideo> filmography) {
        this.filmography = filmography;
    }

    public void addFilm(AbstractVideo video) {
        this.filmography.add(video);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
