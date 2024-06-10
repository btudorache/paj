package domain;

// TODO Movie
public class Movie extends AbstractVideo {

    private final int runtime;
    public Movie(String name, int releaseYear, int runtime) {
        super(name, releaseYear);
        this.runtime = runtime;
    }

    @Override
    public int getRuntime() {
        return runtime;
    }
}
