package domain;

public class Season {

    private final int seasonNumber;
    private final int runtime;

    public Season(int seasonNumber, int runtime) {
        this.seasonNumber = seasonNumber;
        this.runtime = runtime;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getRuntime() {
        return runtime;
    }
}
