package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	private int    bno;
	private String title;
	private String menu_id;
	private String content;
	private String writer;
	private String regdate;
	private int    hit;


}
