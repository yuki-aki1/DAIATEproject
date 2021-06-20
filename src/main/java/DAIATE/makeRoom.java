package DAIATE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.Player;

/**
 * Servlet implementation class makeRoom
 */
@WebServlet("/makeRoom")
public class makeRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public makeRoom() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		String playeName = request.getParameter("name");
		String roomId = utility.RandomGenerate.getRandomString(6);
		HttpSession session = request.getSession(true);
		String sessionId = session.getId();
		Player player = new Player(playeName, roomId, sessionId);
		int numOfUser = 1; // とりあえず「1」

		request.setAttribute("name", playeName);
		request.setAttribute("roomId", roomId);
		request.setAttribute("numOfUser", numOfUser);

		request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);

	}

}
