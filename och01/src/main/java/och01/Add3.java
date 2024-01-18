package och01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add3
 */
public class Add3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //내부에서 관리하는 버전 방식이다 . 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Add Get Start....");
		// 목표 : 1부터 누적값 전당
		// string 값이라서 강제 parseint해서 강제형변환시킴
		int num = Integer.parseInt(request.getParameter("num"));
		String loc = request.getParameter("loc");
		System.out.println("Add3 num->"+num);
		int sum = 0;
		for(int i=1;i<=num;i++) {
			sum += i;
		}
		System.out.println("Add sum->"+sum);
		// 한글로 변환해주는 거
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 공식 --> 사용자 Browser에 보여주는 주제
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		// %d는 num의 값을 %d로 옴김 %s loc 의 값을 담음
		out.printf("<h1>1부터 %d까지 합계 %s</h1>", num,loc);
		out.println(sum);
		out.println("</body></html>");
		out.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Add post Start....");
		
		
	}
	

}
