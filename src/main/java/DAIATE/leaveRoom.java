package DAIATE;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.Player;
import dbAccess.Room;

/**
 * Servlet implementation class leaveRoom
 */
@WebServlet("/leaveRoom")
public class leaveRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public leaveRoom() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			 request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);			
			return ;
		}
		
		String sessionId = (String) session.getId();
		String roomId = (String) session.getAttribute("roomId");
		String playerName = (String) session.getAttribute("playerName");
		String hostPlayerName = (String) session.getAttribute("hostPlayerName");
		
		Player.deletePlayer(sessionId);

		if (playerName.equals(hostPlayerName)) {
			Room.deleteRoom(roomId);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		
	}

}
