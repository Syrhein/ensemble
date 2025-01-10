package smhrd.model;

public class ReviewVO {
    private int reviewIdx;
    private int showIdx;
    private String userId;
    private String reviewContent;
    private int reviewStar; // 변경: String -> int
    private int reviewLikes = 0; // 기본값 설정

    // 기본 생성자
    public ReviewVO() {
    }

    // 매개변수 생성자
    public ReviewVO(int showIdx, String userId, String reviewContent, int reviewStar) {
        this.showIdx = showIdx;
        this.userId = userId;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewLikes = 0; // 기본값 설정
    }

    // Getter and Setter
    public int getReviewIdx() {
        return reviewIdx;
    }

    public void setReviewIdx(int reviewIdx) {
        this.reviewIdx = reviewIdx;
    }

    public int getShowIdx() {
        return showIdx;
    }

    public void setShowIdx(int showIdx) {
        this.showIdx = showIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewStar() { // 변경: int 타입
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) { // 변경: int 타입
        this.reviewStar = reviewStar;
    }

    public int getReviewLikes() {
        return reviewLikes;
    }

    public void setReviewLikes(int reviewLikes) {
        this.reviewLikes = reviewLikes;
    }

    @Override
    public String toString() {
        return "ReviewVO [reviewIdx=" + reviewIdx + ", showIdx=" + showIdx + ", userId=" + userId 
            + ", reviewContent=" + reviewContent + ", reviewStar=" + reviewStar + ", reviewLikes=" + reviewLikes + "]";
    }
}
