package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ListAction Service start...");
		
		BoardDao bd = BoardDao.getInstance();
		
		
		try {
			int totCnt = bd.getTotalCnt();
	         // 난잡한 Paging 작업을 해보자....
	         String PageNum = request.getParameter("pageNum");
	         
	         if (PageNum == null || PageNum.equals("")) PageNum = "1";
	         
	         int currentPage = Integer.parseInt(PageNum);
	         int pageSize = 10, blockSize = 10;
	         int startRow = (currentPage - 1) * pageSize + 1;
	         int endRow = startRow + pageSize - 1;
	         int startNum = totCnt - startRow + 1;
	         
	         // Board 조회
	         List<Board> list = bd.boardList(startRow, endRow);
	         										// 38/10
	         int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
	         
	         int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
	         int endPage = startPage + blockSize - 1;
	         
	         // 공갈 page 방지
	         if (endPage > pageCnt) endPage = pageCnt;
	         
	         request.setAttribute("list", list);
	         request.setAttribute("totCnt", totCnt);
	         request.setAttribute("PageNum", PageNum);
	         request.setAttribute("currentPage", currentPage);
	         request.setAttribute("startNum", startNum);
	         request.setAttribute("blockSize", blockSize);
	         request.setAttribute("pageCnt", pageCnt);
	         request.setAttribute("startPage", startPage);
	         request.setAttribute("endPage", endPage);			
			
			request.setAttribute("totCnt", totCnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 38
		
		
		
		// 		View 명칭
		return "listForm.jsp";
	}

}
