package com.test.sist.broker.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/broker/chat/chatlist.do")
public class ChatList extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		//글 목록
		ChatDAO dao = new ChatDAO();
	
		ArrayList<ChatDTO> list = dao.list();
		
		for (ChatDTO dto : list) {
			
			//날짜에서 시간 잘라내기
			dto.setRegDate(dto.getRegDate().substring(0, 10));
			
			//제목이 너무 길면 자르기
			if (dto.getSubject().length() > 34) {
				dto.setSubject(dto.getSubject().substring(0, 34) + "..");
			}
		}
		
		
		
//		//페이지 바
//		totalCount = dao.getTotalCount(); //총 게시물 수
//		
//		String pageBar = Pagination.getPageBarTag(nowPage, totalCount, pageSize, blockSize, "/sybang/admin/room/boardlist.do");
		
		//목록 전달
		request.setAttribute("list", list);
//		request.setAttribute("pageBar", pageBar);
//		request.setAttribute("nowPage", nowPage);
	   
	   
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/broker/chat/chatlist.jsp");
      dispatcher.forward(request, response);
      
   }

}


