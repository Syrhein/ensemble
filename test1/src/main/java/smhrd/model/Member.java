package smhrd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Member {
	
	private String id;
	private String pw;
	private String nick;
	private String email;
	private String tell;
}
