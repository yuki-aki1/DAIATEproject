package DAIATE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.Player;
import dbAccess.Room;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		String playerName = request.getParameter("name");
		String roomId = utility.RandomGenerate.getRandomString(6);
		HttpSession session = request.getSession(true);
		String sessionId = session.getId();
		
		session.setAttribute("roomId", roomId);
		session.setAttribute("hostPlayerName", playerName);
		session.setAttribute("playerName", playerName);
		
		new Room(roomId, playerName);
		new Player(playerName, roomId, sessionId);
		int playerIndex = 0;
		
		String[] playerNames = {playerName};

		request.setAttribute("roomId", roomId);
		request.setAttribute("playerNames", playerNames);
		request.setAttribute("playerIndex", playerIndex);
	
		request.getRequestDispatcher("/waitRoom").forward(request, response);

	}

}
