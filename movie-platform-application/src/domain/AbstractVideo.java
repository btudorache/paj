package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractVideo {
    private final String name;

    private final int releaseYear;

    private List<Actor> actorList;

    private List<Genre> genres;

    public AbstractVideo(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.actorList = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public AbstractVideo(String name, int releaseYear, List<Actor> actorList) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.genres = new ArrayList<>();
    }

    public AbstractVideo(String name, int releaseYear, List<Actor> actorList, List<Genre> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.genres = genres;
    }

    public abstract int getRuntime();

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public void addActor(Actor actor) {
        this.actorList.add(actor);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVideo that = (AbstractVideo) o;
        return releaseYear == that.releaseYear && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear);
    }
}
