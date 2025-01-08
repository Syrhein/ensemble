package smhrd.model;

import java.sql.Timestamp;
import java.sql.Clob;

public class BoardVO {
    private int postIdx;         // POST_IDX
    private String postTitle;    // POST_TITLE
    private String postContent;    // POST_CONTENT (CLOB)
    private Timestamp createdAt; // CREATED_AT (TIMESTAMP)
    private int postViews;       // POST_VIEWS
    private int postLikes;       // POST_LIKES
    private String userId;       // USER_ID
    private String postFileName; // POST_FILE_NAME
    private String postFilePath; // POST_FILE_PATH

    // Getter 및 Setter
    public int getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(int postIdx) {
        this.postIdx = postIdx;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Clob getPostContent() {
        return postContent;
    }

    public void setPostContent(Clob postContent) {
        this.postContent = postContent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getPostViews() {
        return postViews;
    }

    public void setPostViews(int postViews) {
        this.postViews = postViews;
    }

    public int getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(int postLikes) {
        this.postLikes = postLikes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostFileName() {
        return postFileName;
    }

    public void setPostFileName(String postFileName) {
        this.postFileName = postFileName;
    }

    public String getPostFilePath() {
        return postFilePath;
    }

    public void setPostFilePath(String postFilePath) {
        this.postFilePath = postFilePath;
    }
}
