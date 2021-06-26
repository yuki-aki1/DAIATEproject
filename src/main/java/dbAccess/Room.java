package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {

	public static Connection conn;
	public static PreparedStatement pstmt;
	private String roomId;
	private String hostPlayerName;
	private int roomState;
	private String answerPlayerName;
	private int subjectId;
	private String answer;

	public Room(String roomId, String hostPlayerName, int roomState, String answerPlayerName, int subjectId,
			String answer) {
		this.roomId = roomId;
		this.hostPlayerName = hostPlayerName;
		this.roomState = roomState;
		this.answerPlayerName = answerPlayerName;
		this.subjectId = subjectId;
		this.answer = answer;
	}

	public Room(String roomId, String hostPlayerName) {
		this(roomId, hostPlayerName, 0, null, -1, null);

		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "insert into rooms (room_id, host_player_name, room_state) values (?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setString(1, roomId);
				ps.setString(2, hostPlayerName);
				ps.setInt(3, 0);
				System.out.println("here1");
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
	}

	public static Room getRoom(String roomId) {
		Room room = null;
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select * from rooms where room_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setString(1, roomId);

				try (ResultSet result = ps.executeQuery()) {
					if (result.next()) {
						room = new Room(roomId, result.getString("host_player_name"), result.getInt("room_state"),
								result.getString("answer_player_name"), result.getInt("subject_id"),
								result.getString("answer"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
		return room;
	}

	public static void updateRoomState(String roomId, int roomState) {
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "update rooms set room_state = ? where room_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setInt(1, roomState);
				ps.setString(2, roomId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
	}

	public static void updateAnswerPlayerName(String roomId, String answerPlayerName) {
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "update rooms set answer_player_name = ? where room_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setString(1, answerPlayerName);
				ps.setString(2, roomId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
	}
	
	public static void updateSubjectId(String roomId, int subjectId) {
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER,
				DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "update rooms set subject_id = ? where room_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {

				ps.setInt(1, subjectId);
				ps.setString(2, roomId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}		
	}
	
	public static void deleteRoom(String roomId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "delete from rooms where room_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, roomId);

			pstmt.executeUpdate();

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
	
	public static boolean idExist(String roomId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "select count(*) from rooms where room_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, roomId);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int count = rs.getInt(1);

			if (count == 0) {
				return false;
			}

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
		return true;
	}
	
	public static String getHostName(String roomId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "select host_player_name from rooms where room_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, roomId);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			String hostPlayerName = rs.getString("host_player_name");

			return hostPlayerName;

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
		return "";
	}
	
	public int getRoomState() {
		return this.roomState;
	}
	
	public String getAnswerPlayerName() {
		return this.answerPlayerName;
	}
	
	public int getSubjectId() {
		return this.subjectId;
	}
}
