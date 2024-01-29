package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UpdateProAction Service start...");
		// 1. num , pageNum, writer ,  email , subject , passwd , content   Get
		request.setCharacterEncoding("utf-8");
		String pageNum =request.getParameter("pageNum");
		
		
		try {
			// 2. Board board 생성하고 DTO Value Setting
			Board board = new Board();
			board.setNum(Integer.parseInt(request.getParameter("num")));
			board.setWriter(request.getParameter("writer"));
			board.setEmail(request.getParameter("email"));
			board.setSubject(request.getParameter("subject"));
			board.setPasswd(request.getParameter("passwd"));
			board.setContent(request.getParameter("content"));
			
			
			// 3. BoardDao bd Instance
			BoardDao bd = BoardDao.getInstance();
			int result = bd.update(board);
			
			// 4. request 객체에 result, num , pageNum 
			request.setAttribute("result", result);
			request.setAttribute("num", board.getNum());
			request.setAttribute("pageNum", pageNum);
		}catch(SQLException e) {
			e.printStackTrace();
		}		

		// 5.updatePro.jsp Return		
		return "updatePro.jsp";
		
	}

}
