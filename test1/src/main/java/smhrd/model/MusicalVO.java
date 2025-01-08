package smhrd.model;

public class MusicalVO {
    private String musicalId;       // MUSICAL_ID
    private String musicalTitle;    // MUSICAL_TITLE
    private String musicalPoster;   // MUSICAL_POSTER

    // 기본 생성자
    public MusicalVO() {
    }

    // 모든 필드를 포함한 생성자
    public MusicalVO(String musicalId, String musicalTitle, String musicalPoster) {
        this.musicalId = musicalId;
        this.musicalTitle = musicalTitle;
        this.musicalPoster = musicalPoster;
    }

    // Getter and Setter
    public String getMusicalId() {
        return musicalId;
    }

    public void setMusicalId(String musicalId) {
        this.musicalId = musicalId;
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
        return "MusicalVO [musicalId=" + musicalId + ", musicalTitle=" + musicalTitle + ", musicalPoster=" + musicalPoster + "]";
    }
}
