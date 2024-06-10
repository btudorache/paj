package domain;

import java.util.List;

public class TVShow extends AbstractVideo {

    private List<Season> seasonList;

    public TVShow(String name, int releaseYear) {
        super(name, releaseYear);
    }

    public TVShow(String name, int releaseYear, List<Season> seasonList) {
        super(name, releaseYear);
        this.seasonList = seasonList;
    }

    @Override
    public int getRuntime() {
        return seasonList.stream().map(Season::getRuntime).reduce(0, Integer::sum);
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }
}
