package smhrd.model;

public class FavoriteVO {
    private String userId;
    private int showIdx;

    // 생성자
    public FavoriteVO(String userId, int showIdx) {
        this.userId = userId;
        this.showIdx = showIdx;
    }

    // Getter & Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getShowIdx() {
        return showIdx;
    }

    public void setShowIdx(int showIdx) {
        this.showIdx = showIdx;
    }
}
