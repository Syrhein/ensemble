package smhrd.model;

public class MyPageVO {
    private String musicalTitle; // 뮤지컬 제목
    private String musicalId;    // 뮤지컬 ID
    private int showIdx;         // 공연 IDX
    private String createdAt;    // 관심 등록 날짜

    // Getter 및 Setter
    public String getMusicalTitle() {
        return musicalTitle;
    }

    public void setMusicalTitle(String musicalTitle) {
        this.musicalTitle = musicalTitle;
    }

    public String getMusicalId() {
        return musicalId;
    }

    public void setMusicalId(String musicalId) {
        this.musicalId = musicalId;
    }

    public int getShowIdx() {
        return showIdx;
    }

    public void setShowIdx(int showIdx) {
        this.showIdx = showIdx;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
