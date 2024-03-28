package com.board.controller;

import java.util.HashMap;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//public ModelAndView list(String menu_id) 원래 이걸로 해야하는데 
	//MenuVo안에 menu_id존재하므로 MenuVo menuVo로 받아도 된다.
	//public ModelAndViw list(@Param String menu_id)이걸로 해도 됨
	//BoardVo 안에도 menu_id가 존재하므로->BoardVo boardVo로 받아도 된다.
	
	//  /Board/List?menu_id=MENU01
	@RequestMapping("/List") 
	public ModelAndView list(MenuVo menuVo){
	//String menu_id는 위에 List?뒤의 menu_id
	
		log.info("==================menuVo:{}",menuVo);
		
		//메뉴 목록 - 상단바 메뉴 받아온다
		List<MenuVo> menuList = menuMapper.getMenuList();
	
		//게시글 목록
		List<BoardVo> boardList = boardMapper.getBoardList(menuVo);
		System.out.println(boardList);
		//param 쓰면 이거 안써도 됨 -> list에 여기서 사용하기 위해서
		// [<a href="/Board/WriteForm?menu_id=#{@param.menu_id }">새 글 추가</a>]
		// menuMapper에서 menu_id불러옴
		
		MenuVo mVo = menuMapper.getMenu(menuVo.getMenu_id());
		String menu_id = menuVo.getMenu_id();
		String menu_name = menuVo.getMenu_name();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("boardList", boardList);
		mv.addObject("menu_id",menu_id);
		mv.addObject("menu_name", menu_name);
		
		mv.setViewName("board/list");
		return mv;
	}
	
	//write.jsp로 가는...
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(MenuVo menuVo) {
		//상단 메뉴바를 위한
		//메뉴 목록 조회
		List<MenuVo>menuList = menuMapper.getMenuList();
		System.out.println("menuList:"+menuList);
		
		//?menu_id=MENU01 넘어온 menu_id를 처리
		String menu_id = menuVo.getMenu_id();
		
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		
		mv.addObject("menu_id", menu_id);
		
		mv.setViewName("/board/write");
		return mv;
	}
	// /Board/Write
	// menu_id=MENU01, title=aaa, writer-,
	
/*	public String write(BoardVo boardVo) {
		boardMapper.insert(boardVo);
			
		return "redirect:/Board/List?menu_id=";
		
	}*/
	@RequestMapping("/Write")
	public  ModelAndView   write( BoardVo boardVo  )   {
		System.out.println("boardVo : " + boardVo );
		//넘어온 값을 boardVo에 저장
		boardMapper.insert(boardVo);
				
		String        menu_id =  boardVo.getMenu_id();
		
		ModelAndView  mv      =  new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		//mv.setViewName("/Board/List?menu_id=MENU03");
		return        mv;
		
	}

	@RequestMapping("/View")
	public ModelAndView view(BoardVo boardVo) {
		
		//상단에 메뉴바를 위한 메뉴목록 조회
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		//조회수 증가(현재 bno의 hit=hit+1) -bno는 boardVo가 가지고 있다.
		boardMapper.incHit(boardVo);
		
		//bno로 조회한 게시글 정보
		BoardVo vo = boardMapper.getBoard(boardVo);
		
		//vo.content안의 \n을 '<br>'로 변경한다.
		String content = vo.getContent();
		if(content != null) {
			content = content.replace("\n","<br>");
			
			vo.setContent(content);
		}
		
		log.info("boardVo:{}",boardVo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.addObject("menuList", menuList);
		
		mv.setViewName("/board/view");
		return mv;
	}
	
	
	/*상단에 메뉴바를 불러오기 위해 menuList가 필요함
	<table id="menu">
   	 <c:forEach var="menu" items="${menuList}" >
	   <td>
	   <a href="/Board/List?menu_id=${menu.menu_id}">${menu.menu_name}</a>
	   </td> 
     </c:forEach>
     
   @Autowired 
   private MenuMapper menuMapper;
     
   List<MenuVo> menuList = menuMapper.getMenuList();
   
   model.addAttribute("menuList",menuList);
   mv.addObject("menuList",menuList);

	*/
/*	@RequestMapping("/Delete")
	public String delete(BoardVo boardVo,Model model) {
		//상단 메뉴바
		List<MenuVo> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
		
		boardMapper.delete(boardVo);
		
		return "redirect:/Board/List?menu_id=MENU01";
	}  */
	
	@RequestMapping("Delete")
	public ModelAndView delete(BoardVo boardVo) {
		//게시글 삭제
		boardMapper.delete(boardVo);
		
		String menu_id = boardVo.getMenu_id();
		
		//다시 조회
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		return mv;
	}
	
	//  /Board/UpdateForm?menu_id=${menu.menu_id}
/*	@RequestMapping("/UpdateForm")
	public String updateForm(BoardVo boardVo,Model model) {
	//	System.out.println("boardVo :"+boardVo);
		String menu_id=boardVo.getMenu_id();
		
	//  수정할 데이터를 menu_id기준으로 조회
	    BoardVo bv = boardVo.getMenu(menu_id);
		
		model.addAttribute("menu_id", bv);
		
		return "board/update";
	}
	
	@RequestMapping("/Update")
	public String update(BoardVo boardVo, Model model) {
		//상단 메뉴바
		List<MenuVo> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
	
		
		boardMapper.update(boardVo);
		
		return "redirect:/Board/List?menu_id=MENU01";
		
	}
	
*/
}
