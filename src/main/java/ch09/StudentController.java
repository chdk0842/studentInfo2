package ch09;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class StudentController
 */

//Studentcontroller s = new Studentcontroller(); 서블릿 객체 생성은 톰캣에서 해준다.
@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDAO dao; // 객체생성

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // 서블릿 초기화
		dao = new StudentDAO(); // StudentDAO 객체가 딱 한번만 만들어진다. -> 공유해서 쓸 수 있다.
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 데이터 깨짐 방지
		// request : 뷰에서 넘어온 데이터, 정보가 들어있다.
		String action = request.getParameter("action"); // action="/studentInfo2/studentControl?action=insert"의 action은 key, value는 insert
		String view = "";

		view = insert(request, response); // request와 response 객체를 매개변수로 넘겨준다

		// getServletContext() : ServletContext를 얻어옴
		// getRequestDispatcher(이동할 페이지) : 이동할 페이지의 경로지정
		// forward : 페이지를 이동시키지만 내부에서 이동이 되므로 주소가 변하지 않는다.
		getServletContext().getRequestDispatcher("/ch09/" + view).forward(request, response);

	}

	// request 데이터 받아옴 -> DAO 에 있는 insert 실행(DB에 insert가 됨) -> 페이지명(studentInfo.jsp)
	// 리턴
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());

//			BeanUtils.populate(s, request.getParameterMap()); -> 아래 코드의 역할을 대신 해준다.	
//			s.setUsername(request.getParameter("username"));
//			s.setUniv(request.getParameter("univ"));
//			s.setBirth(request.getParameter("birth"));
//			s.setEmail(request.getParameter("email"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		dao.insert(s); // 컨트롤러는 DAO한테 있는 메소드를 사용한다. DAO한테 데이터 베이스 관련 요청을 해야한다.
		return "studentInfo.jsp";
	}

}
