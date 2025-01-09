package smhrd.model;

public class ReviewVO {
    private int reviewIdx;
    private int showIdx;
    private String userId;
    private String reviewContent;
    private String reviewStar;
    private int reviewLikes;

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

    public String getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(String reviewStar) {
        this.reviewStar = reviewStar;
    }

    public int getReviewLikes() {
        return reviewLikes;
    }

    public void setReviewLikes(int reviewLikes) {
        this.reviewLikes = reviewLikes;
    }
}
