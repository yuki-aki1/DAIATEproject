package DAIATE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class enterRoom
 */
@WebServlet("/enterRoom")
public class enterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public enterRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name"); // プレイヤー名の入力を取得
		String roomId = request.getParameter("roomId"); // roomIｄの入力を取得

		request.setAttribute("name", name);
		request.setAttribute("roomId", roomId);

		// プレイヤー名：aa、ルームID：00が入力されたときのみ「waitPlayer.jsp」へ
		if (name.equals("aa") == true && roomId.equals("00") == true) {
			request.setAttribute("name", name);
			request.setAttribute("roomId", roomId);
			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);

		} else if (name.equals("aa") == false && roomId.equals("00") == true) {
			request.setAttribute("nameError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		} else if (name.equals("aa") == true && roomId.equals("00") == false) {
			request.setAttribute("roomIdError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		} else {
			request.setAttribute("nameError", "err発生");
			request.setAttribute("roomIdError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		}

	}

}
