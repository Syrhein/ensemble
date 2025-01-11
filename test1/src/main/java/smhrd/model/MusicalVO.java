package smhrd.model;

public class MusicalVO {
    private String musicalId;       // MUSICAL_ID
    private String showIdx;
    private String musicalTitle;    // MUSICAL_TITLE
    private String musicalPoster;   // MUSICAL_POSTER
    private String musicalCreate;   // MUSICAL_CREATE

    // 기본 생성자
    public MusicalVO() {
    }

    // 모든 필드를 포함한 생성자
    public MusicalVO(String musicalId, String musicalTitle, String musicalPoster ,String showIdx) {
        this.musicalId = musicalId;
        this.musicalTitle = musicalTitle;
        this.musicalPoster = musicalPoster;
        this.showIdx = showIdx;
        
    }

    // Getter and Setter
    
    
    public String getMusicalId() {
        return musicalId;
    }

    public String getShowIdx() {
		return showIdx;
	}

	public void setShowIdx(String showIdx) {
		this.showIdx = showIdx;
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

	public String getMusicalCreate() {
		return musicalCreate;
	}

	public void setMusicalCreate(String musicalCreate) {
		this.musicalCreate = musicalCreate;
	}
}
