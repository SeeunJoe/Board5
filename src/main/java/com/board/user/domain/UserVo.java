package com.board.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data //: @Getter,@Setter,@ToString,@NoArgsConstructor
      //  +@EqualsAndHashCode,@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
	//Fields
	@NonNull //sql에서 not null인 거
	private String userid;
	@NonNull
	private String passwd;
	@NonNull
	private String username;
	private String email;
	private int    upoint;  //원래는 int로 해야하는데 오류발생
	private String indate;

}
