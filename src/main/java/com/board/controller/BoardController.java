package com.board.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardVo;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private MenuMapper menuMapper;
	
	//  /Board/List?menu_id=MENU01
	@RequestMapping("/List") 
	public ModelAndView list(MenuVo menuVo){
	//public ModelAndViw list(String menu_id)이걸로 해도 됨
	
		log.info("menuVo:{}",menuVo);
		
		//메뉴 목록
		List<MenuVo> menuList = menuMapper.getMenuList();
		//게시글 목록
		List<BoardVo> boardList = boardMapper.getBoardList(menuVo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("boardList", boardList);
		
		mv.setViewName("/board/list");
		return mv;
	}
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		LocalDateTime   today = LocalDateTime.now();
		String  now   = today.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		DayOfWeek day = today.getDayOfWeek();
		now +=" " + day;
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("now",now);
		
		mv.setViewName("board/write");

		return mv;
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(BoardVo boardVo) {
		boardMapper.insert(boardVo);
		ModelAndView mv = new ModelAndView();
			
		mv.setViewName("board/list");
		return mv;
		
	}
	
	

}
