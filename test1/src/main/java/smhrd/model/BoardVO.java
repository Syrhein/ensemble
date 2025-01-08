package smhrd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.sql.Clob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private int postIdx;         // POST_IDX
    private String postTitle;    // POST_TITLE
    private Clob postContent;    // POST_CONTENT (CLOB)
    private Timestamp createdAt; // CREATED_AT (TIMESTAMP)
    private int postViews;       // POST_VIEWS
    private int postLikes;       // POST_LIKES
    private String userId;       // USER_ID
    private String postFileName; // POST_FILE_NAME
    private String postFilePath; // POST_FILE_PATH
}
