package smhrd.model;

import java.sql.Timestamp;

public class CommentVO {
    private int cmtIdx;          // 댓글 ID
    private int postIdx;         // 게시글 ID
    private String userId;       // 작성자
    private String cmtContent;   // 댓글 내용
    private Timestamp createdAt; // 생성일시
    private int cmtLikes;        // 좋아요 수

    // Getter 및 Setter
    public int getCmtIdx() {
        return cmtIdx;
    }

    public void setCmtIdx(int cmtIdx) {
        this.cmtIdx = cmtIdx;
    }

    public int getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(int postIdx) {
        this.postIdx = postIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getCmtLikes() {
        return cmtLikes;
    }

    public void setCmtLikes(int cmtLikes) {
        this.cmtLikes = cmtLikes;
    }
}
