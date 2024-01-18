package och01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet implementation class Board2
 */
public class Board2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	      String writer = request.getParameter("writer");
	      String content = request.getParameter("content");
	      Date date = new Date();
	      response.setContentType("text/html;charset=utf-8");
	      
	      PrintWriter out = response.getWriter();
	      out.print("<html><body>");
	      out.print("<h1>게시판</h1><p>");
	      out.printf("제목 : %s<p>", title);
	      out.printf("작성자 : %s<p>", writer);
	      out.print("작성일 : "+date+"<p>");
	      out.print("내용<p>");
	      out.printf("%s", content);
	      out.print("</body></html>");
	      out.close();
	}

}
