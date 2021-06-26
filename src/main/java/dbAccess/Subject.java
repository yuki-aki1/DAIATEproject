package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject {

	private int subjectId;
	private String subjectName;
	
	Subject(int subjectId, String subjectName) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
	
	public static Subject getSubject() {
		Subject subject = null;
		int length = 0;
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select count(*) from subjects";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				try (ResultSet result = ps.executeQuery()) {
					if (result.next()) {
						length = result.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
		
		int randomIndex = utility.RandomGenerate.getRandomIndex(length);
		
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select * from subjects where subject_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				ps.setInt(1,  randomIndex);
				try (ResultSet result = ps.executeQuery()) {
					if (result.next()) {
						subject = new Subject(result.getInt("subject_id"), result.getString("subject_name"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
		return subject;		
	}
	
	
	public static Subject getSubject(int subjectId) {
		Subject subject = null;
		try (Connection con = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD)) {
			System.out.println("Connected....");
			String sqlStr = "select * from subjects where subject_id = ?";
			try (PreparedStatement ps = con.prepareStatement(sqlStr)) {
				ps.setInt(1,  subjectId);
				try (ResultSet result = ps.executeQuery()) {
					if (result.next()) {
						subject = new Subject(result.getInt("subject_id"), result.getString("subject_name"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed. : " + e.toString());
			throw new RuntimeException(e);
		}
		return subject;		
	}
	
	
	public int getSubjectId() {
		return this.subjectId;
	}
	
	public String getSubjectName() {
		return this.subjectName;
	}
}
