package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel model = new UserModel();
			model.setId(resultSet.getInt("id"));
			model.setUsername(resultSet.getString("username"));
			model.setPassword(resultSet.getString("password"));
			model.setRole(resultSet.getString("role"));
			model.setEmail(resultSet.getString("email"));
			model.setFull_name(resultSet.getString("full_name"));
			model.setPhone(resultSet.getString("phone"));
			model.setCreated_at(resultSet.getTimestamp("created_at"));
			model.setUpdated_at(resultSet.getTimestamp("updated_at"));
			
			 
	
			return model;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
