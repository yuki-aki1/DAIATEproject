package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Player {

	public static Connection conn;
	public static PreparedStatement pstmt;
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
		
		
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "insert into players (player_name, room_id, session_id, hint) values (?, ?, ?, ?)";			
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				ps.setString(1, playerName);
				ps.setString(2, roomId);
				ps.setString(3, sessionId);
				ps.setString(4, "");
				
				System.out.println("here2");

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
	}
	
	public static void updateHint(String sessionId, String hint) {
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "update players set hint = ? where sessionId = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setString(1, hint);
				ps.setString(2, sessionId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}	
	}


	public static Player getPlayersWithSessionId(String sessionId) {
		Player player = null;
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select * from players where session_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				ps.setString(1, sessionId);
				try (ResultSet result = ps.executeQuery()) {
					if (result.next()) {
						player = new Player(result.getString("player_name"), result.getString("room_id"), sessionId, result.getString("hint"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
		
		return player;
	}

	public static Player[] getPlayersWithRoomId(String roomId) {
		List<String> playerNameList = new ArrayList<>();
		List<String> sessionIdList = new ArrayList<>();
		List<String> hintLIst = new ArrayList<>();		
		ArrayList<Player> playerList = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select * from players where room_id = ?";			
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				ps.setString(1, roomId);
				try (ResultSet result = ps.executeQuery()) {
					while (result.next()) {
						playerNameList.add(result.getString("player_name"));
						sessionIdList.add(result.getString("session_id"));
						hintLIst.add(result.getString("hint"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}

		for (int i = 0; i < playerNameList.size(); i++) {
			playerList.add(new Player(playerNameList.get(i), roomId, sessionIdList.get(i), hintLIst.get(i)));
		}
		
		Player[] playerArray = new Player[playerList.size()];
		for (int i = 0; i < playerList.size(); i++) {
			playerArray[i] = playerList.get(i);
		}
		return playerArray;
	}

	public static void deletePlayer(String sessionId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "delete from players where session_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, sessionId);

			pstmt.executeUpdate();

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

	public static boolean existsPlayerName(String roomId, String playerName) {
		Player[] players = Player.getPlayersWithRoomId(roomId);

		for (Player player : players) {
			if (player.getPlayerName().equals(playerName)) {
				return true;
			}
		}
		return false;
	}

	public String getPlayerName() {
		return this.playerName;
	}
	
	public String getHint() {
		return this.hint;
	}

}
