package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	public Connection conn;
	public int add(String sql) {
		int lastId = -1;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prog2","root","");
			conn.setAutoCommit(false);
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				lastId = stmt.executeUpdate();
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			ex.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return lastId;
	}
	
	public int update(String sql) {
		int lastId = -1;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prog2","root","");
			conn.setAutoCommit(false);
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				lastId = stmt.executeUpdate();
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			ex.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		return lastId;
	}

	public int remove(String sql) {
		int lastId = -1;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prog2","root","");
			conn.setAutoCommit(false);
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				lastId = stmt.executeUpdate();
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lastId;
	}

	public HashMap<String, Object> querySingle(String sql) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prog2","root","");
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsMetaData = rs.getMetaData();
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
							map.put(rsMetaData.getColumnName(i + 1), rs.getString(i + 1));
						}
					}
				}
				rs.close();
				stmt.close();

				return map;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

	public ArrayList<HashMap<String, Object>> queryList(String sql) {
		ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prog2","root","");
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsMetaData = rs.getMetaData();
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
							map.put(rsMetaData.getColumnName(i + 1), rs.getString(i + 1));
						}
						mapList.add(map);
					}
				}
				rs.close();
				stmt.close();

				return mapList;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return mapList;
	}
}
