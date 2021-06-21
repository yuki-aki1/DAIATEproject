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

/**
 * Servlet implementation class waitRoom
 */
@WebServlet("/waitRoom")
public class waitRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public waitRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

			System.out.println("waitRoomのdoGet");

			
			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);			
		}
		else  {
			 request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}