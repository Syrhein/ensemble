package smhrd.model;

import java.sql.Timestamp;

public class ReviewVO {
    private int reviewIdx;
    private int showIdx;
    private String userId;
    private String userName; // 추가
    private String reviewContent;
    private int reviewStar; // 변경: String -> int
    private int reviewLikes = 0; // 기본값 설정
    private Timestamp createdAt; // 생성일 필드 추가
    private String musicalId; // 추가
    private String musicalTitle; // 추가

    // 기본 생성자
    public ReviewVO() {
    }

    // 매개변수 생성자
    public ReviewVO(int showIdx, String userId, String userName, String reviewContent, int reviewStar, String musicalId, String musicalTitle) {
        this.showIdx = showIdx;
        this.userId = userId;
        this.userName = userName; // 추가
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewLikes = 0; // 기본값 설정
        this.musicalId = musicalId; // 추가
        this.musicalTitle = musicalTitle; // 추가
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

    public String getUserName() { // 추가
        return userName;
    }

    public void setUserName(String userName) { // 추가
        this.userName = userName;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public int getReviewLikes() {
        return reviewLikes;
    }

    public void setReviewLikes(int reviewLikes) {
        this.reviewLikes = reviewLikes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getMusicalId() { // 추가
        return musicalId;
    }

    public void setMusicalId(String musicalId) { // 추가
        this.musicalId = musicalId;
    }

    public String getMusicalTitle() { // 추가
        return musicalTitle;
    }

    public void setMusicalTitle(String musicalTitle) { // 추가
        this.musicalTitle = musicalTitle;
    }

    @Override
    public String toString() {
        return "ReviewVO [reviewIdx=" + reviewIdx + ", showIdx=" + showIdx + ", userId=" + userId + ", userName=" + userName 
            + ", reviewContent=" + reviewContent + ", reviewStar=" + reviewStar + ", reviewLikes=" + reviewLikes 
            + ", createdAt=" + createdAt + ", musicalId=" + musicalId + ", musicalTitle=" + musicalTitle + "]";
    }
}
