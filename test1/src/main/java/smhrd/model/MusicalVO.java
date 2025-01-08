package smhrd.model;

public class MusicalVO {
    private String  musicalId;
    private String musicalTitle;
    private String musicalPoster;

    // Getter 및 Setter
    public String getMusicalIdx() {
        return musicalId;
    }

    public void setMusicalIdx(String musicalIdx) {
        this.musicalId = musicalIdx;
    }

    public String getMusicalTitle() {
        return musicalTitle;
    }

    public void setMusicalTitle(String musicalTitle) {
        this.musicalTitle = musicalTitle;
    }

    public String getMusicalPoster() {
        return musicalPoster;
    }

    public void setMusicalPoster(String musicalPoster) {
        this.musicalPoster = musicalPoster;
    }

    @Override
    public String toString() {
        return "MusicalVO{" +
                "musicalIdx=" + musicalId +
                ", musicalTitle='" + musicalTitle + '\'' +
                ", musicalPoster='" + musicalPoster + '\'' +
                '}';
    }
}
