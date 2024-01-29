package service;

import java.io.IOException;

import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DeleteProAction start...");
		// HW 2
		// 1. num , passwd , pageNum Get
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd =request.getParameter("passwd");
		String pageNum =request.getParameter("pageNum");
		try {
			// 2. BoardDao bd Instance
				BoardDao bd = BoardDao.getInstance();
			// 3. 본인의 게시판 만 삭제 
				int result = bd.delete(num, passwd);	
			// HW3
			
		// 4. request 객체에  num , pageNum ,result
				request.setAttribute("num", num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("result", result);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return  "deletePro.jsp";
	}

}
