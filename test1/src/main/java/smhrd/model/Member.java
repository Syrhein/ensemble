package smhrd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String userId;      // USER_ID
    private String userPw;      // USER_PW
    private String userName;    // USER_NAME
    private String userEmail;   // USER_EMAIL
    private String userTel;     // USER_TEL
    private Timestamp joinedAt; // JOINED_AT
    private String userImg;       // USER_IMG
}
