package t0420;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sungjuk
 */
@SuppressWarnings("serial")
@WebServlet("/sungjukOk")
public class Sungjuk extends HttpServlet {

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
				
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String no = request.getParameter("no");
		String hakyun = request.getParameter("hakyun");
		String hakyunOption = request.getParameter("hakyunOption");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int mat = Integer.parseInt(request.getParameter("mat"));
		String[] language = request.getParameterValues("language");
		String[] sports = request.getParameterValues("sports");
		String otherSports = request.getParameter("otherSports")==null ? "" : request.getParameter("otherSports");
		
		String strSports = "";
		String strLanguage = "";
		
		if(sports != null) {
			for(String sport : sports) {
				if(sport.equals("기타")) {
					if(otherSports.trim().length() <= 0) {
						out.println("<script>");
						out.println("alert('기타항목을 선택하셨으면 기타란에 입력하세요.');");
						out.println("history.back();");
						out.println("</script>");
						return;
					}
					strSports += otherSports;
				}
				if(!sport.equals("기타")) strSports += sport + " / ";
			}
		}
		else {
			out.println("<script>");
			out.println("alert('좋아하는 스포츠를 1개 이상 입력하세요.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		if(language == null) {
			out.println("<script>");
			out.println("alert('사용가능언어를 선택하세요.');");
			out.println("history.back();");
			out.println("</script>");
			return; 
		}
		else {
			strLanguage = "";
			for(String lang : language) {
				strLanguage += lang + " / ";
			}
		}
			
		int tot;
		double avg;
		String grade;
		
		if(hakyun.equals("")) hakyun = hakyunOption;
		
		tot = kor + eng + mat;
		avg = tot / 3.0d;
		
		switch ((tot / 3) / 10) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "F";
		}
		
		SungjukVO vo = new SungjukVO(name, gender, no, hakyun, language, sports, strLanguage, strSports, otherSports, kor, eng, mat, tot, avg, grade);
		
		//1번 방식 : response.sendRedirect() GET방식//모두 String형이므로 jsp에서 vo타입으로 꺼낼 수 없다
		//response.sendRedirect(request.getContextPath()+"/0420/sungjukView.jsp?vo="+vo);
		
		//2번 방식 : forward방식 POST방식
		RequestDispatcher dispatcher = request.getRequestDispatcher("/0420/sungjukView.jsp");
		request.setAttribute("vo", vo);
		dispatcher.forward(request, response);
	}
}
