package mockproject.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mockproject.dao.GenericDAO;
import mockproject.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	
	// DO GET CONNECTION
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mockprojectdb";
			String username = "root";
			String password = "";
			
			return DriverManager.getConnection(url,username,password);
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Connection connection = null;
	private PreparedStatement statment = null;
	


	@Override
	public <T> List<T> query(String sql,RowMapper<T> rowMapper, Object... params) {
		List<T> results = new ArrayList<>();
		ResultSet resultSet = null;
		 
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			setParameters(statment, params);
			resultSet = statment.executeQuery();
			while(resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			closeConnection();
		}
	}

	@Override
	public void update(String sql, Object... params) {
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			setParameters(statment, params);
			connection.setAutoCommit(false);
			statment.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}finally {
			closeConnection();
		}
		
		
	}
	
	

	@Override
	public Integer insert(String sql, Object... params) {
		ResultSet setKey = null;
		Integer id = null;
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			connection.setAutoCommit(false);
			setParameters(statment, params);
			statment.executeUpdate();
			connection.commit();
			setKey = statment.getGeneratedKeys();
			if(setKey.next()) {
				id = setKey.getInt(1);
			}
			return id;
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			if(connection!= null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}return null;
		}finally {
			closeConnection();
		}
	}

	@Override
	public void delete(String sql, Object... params) {
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			connection.setAutoCommit(false);
			setParameters(statment, params);
			statment.executeUpdate();
			connection.commit();
			return;
			
		} catch (SQLException e) {
			if(connection!= null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}
	public void setParameters(PreparedStatement statement, Object... parameters) {

		try {
			for (int i = 0; i < parameters.length; i++) {
				Object object = parameters[i];
				int index = i + 1;
				if (object instanceof String) {
					statement.setString(index, (String) object);
				}
				else if(object instanceof Long) {
					statement.setLong(index, (Long) object);
				}
				else if(object instanceof Integer) {
					statement.setInt(index, (Integer) object);
				}
				else if(object instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) object);
				}else if(object instanceof Date) {
					statement.setDate(index, (Date) object);
				}
			}
		} catch (SQLException e) {
			return;
		}

	}
	
	
	public void closeConnection() {
		try {
			if(connection!= null) {
				connection.close();
			}
			if(statment != null) {
				statment.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean checkUserExists(String sql, String username) {
		ResultSet resultSet = null;
		boolean check = false;
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			statment.setString(1, username);
			resultSet = statment.executeQuery();
			if(resultSet.next()) {
				check = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
		return check;
	}
	
	public boolean checkAccountExists(String sql, String username, String password) {
		ResultSet resultSet = null;
		boolean check = false;
		try {
			connection = getConnection();
			statment = connection.prepareStatement(sql);
			statment.setString(1, username);
			statment.setString(2, password);
			resultSet = statment.executeQuery();
			if(resultSet.next()) {
				check = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
		return check;
	}
	
	
	
	public static void main(String[] args) {
		AbstractDAO<String> hehe = new AbstractDAO<>();
		Connection connection = null;
		connection = hehe.getConnection();
		
		System.out.println(connection == null);
		
	}
	
	

}
