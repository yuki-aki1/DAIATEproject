package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player {

	public static Connection conn;
	private String playerName;
	private String roomId;
	private String sessionId;
	private String hint;

	public Player(String playerName, String roomId, String sessionId, String hint) {
		this.playerName = playerName;
		this.roomId = roomId;
		this.sessionId = sessionId;
		this.hint = hint;
	}

	public Player(String playerName, String roomId, String sessionId) {
		this(playerName, roomId, sessionId, "");
		Player player = getPlayersWithSessionId(sessionId);
		if (player != null) {
			this.playerName = player.playerName;
			this.roomId = player.roomId;
			this.hint = player.hint;
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "insert into players (player_name, room_id, session_id, hint) values (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, playerName);
			pstmt.setString(2, roomId);
			pstmt.setString(3, sessionId);
			pstmt.setString(4, "");

//			int num = pstmt.executeUpdate();
			pstmt.executeUpdate();
			
			pstmt.close();
						
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
		}
	}
		
	public static Player getPlayersWithSessionId(String sessionId) {
		Player player = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);			
			
			String sql = "select * from players where session_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, sessionId);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				player = new Player(rs.getString("player_name"), rs.getString("room_id"), sessionId, rs.getString("hint"));					
			}
			pstmt.close();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
		}
		return player;
	}
	
	public static Player[] getPlayersWithRoomId(String roomId) {
		ArrayList<Player> playerList = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);			
			
			String sql = "select * from players where room_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, roomId);
			
			ResultSet rs = pstmt.executeQuery();

			playerList = new ArrayList<>();
			
			while(rs.next()) {
				playerList.add(new Player(rs.getString("player_name"), roomId, rs.getString("session_id"), rs.getString("hint")));
			}
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
		}
		if (playerList != null) {
			Player[] playerArray = new Player[playerList.size()];
			for (int i = 0; i < playerList.size(); i++) {
				playerArray[i] = playerList.get(i);
			}
			return playerArray;
		}
		return null;
	}

	public String getPlayerName() {
		return playerName;
	}


}
