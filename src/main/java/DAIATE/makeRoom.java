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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session != null) {
			String roomId = (String) session.getAttribute("roomId");
			String playerName = (String) session.getAttribute("playerName");
			String hostPlayerName = (String) session.getAttribute("hostPlayerName");
			
			Player[] players = Player.getPlayersWithRoomId(roomId);
			
			List<String> playerNames = new ArrayList<>();
			
			int playerIndex = 0;
			for (int i = 0; i < players.length; i++) {
				String name = players[i].getPlayerName();
				System.out.println(name);
				if (name.equals(hostPlayerName)) {
					playerNames.add(0, name);					
				} else {
					playerNames.add(name);					
				}
				if (name.equals(playerName)) {
					playerIndex = i;
				}
			}
			
			request.setAttribute("roomId", roomId);
			request.setAttribute("playerNames", playerNames.toArray(new String[playerNames.size()]));
			request.setAttribute("playerIndex", playerIndex);

			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);			
		}
		else  {
			 request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		}

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
	
//		waitRoom wr = new waitRoom();
//		wr.doGet(request, response);
		
//		request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);
		request.getRequestDispatcher("/waitRoom").forward(request, response);

	}

}
