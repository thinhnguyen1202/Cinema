package mockproject.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.IUserDAO;
import mockproject.mapper.UserMapper;
import mockproject.model.UserModel;

@Repository
public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public List<UserModel> listALlUser() {
		String sql = "select * from users";
		
		return query(sql, new UserMapper());
	}

	@Override
	public UserModel findUserByUserNameAndId(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		UserModel model = null;
		List<UserModel> listQuery = query(sql, new UserMapper(), username, password);
		if(listQuery.size() == 0) {
			return null;
		}else {
			model = listQuery.get(0);
		}
		return model;
	}

	@Override
	public UserModel findUserById(int id) {
		String sql = "select * from users where id = ?";
		UserModel model = null;
		List<UserModel> listQuery = query(sql, new UserMapper(), id);
		if(listQuery.size() == 0) {
			return null;
		}else {
			model = listQuery.get(0);
		}
		return model;
	}

	@Override
	public void updateUserWithId( UserModel userModel) {
		String sql = "update users set "
				+ "full_name = ?,"
				+ "email = ?,"
				+ "phone = ?"
				+ "where id = ?";
		update(sql, userModel.getFull_name(), userModel.getEmail(), userModel.getPhone(), userModel.getId());
	}

	@Override
	public UserModel getUserByName(String username) {
		String sql = "select * from users where username = ? limit 1;";
		
		List<UserModel> userModelList = query(sql, new UserMapper(), username);
		if(userModelList != null && userModelList.size() != 0) {
			return userModelList.get(0);
		}
		return null;
		
	}

	@Override
	public int insertUser(UserModel userModel) {
		// TODO Auto-generated method stub
		String sql = "insert into users(username,password,email,full_name, phone, created_at,updated_at) "
				+ "values(?,?,?,?,?,?,?)";
		return insert(sql, userModel.getUsername(), userModel.getPassword(), userModel.getEmail(), userModel.getFull_name(), 
				userModel.getPhone(), userModel.getCreated_at(), userModel.getUpdated_at());
		
	}
	
	
	
	@Override
	public int register(UserModel user) {
		
		
		String query = "INSERT INTO users(username, password, email, full_name, phone)" + "values"  +"(?, ?, ?, ?, ?)";
		
		return insert(query, user.getUsername(), user.getPassword(), user.getEmail(), user.getFull_name(), user.getPhone()) ;
		
		
	}

	@Override
	public boolean checkExistsUsername(String username) {
		String sql = "Select * from users where username = ?";
		return checkUserExists(sql, username);
	}

		
	@Override
	public void updateForWebUser(UserModel userModel) {
		String sql = "update users set "
				+ "full_name = ?,"
				+ "email = ?,"
				+ "phone = ?,"
				+ "password=?"
				+ "updated_at = ?"
				+ "where id = ?";
		update(sql, userModel.getFull_name(), userModel.getEmail(), userModel.getPhone(),userModel.getPassword(),userModel.getUpdated_at(),userModel.getId());
		
	}
	@Override
	public void updatepass(UserModel userModel) {
		String sql = "update users set "
				+ "password = ?,"
				+ "updated_at = ?"
				+ "where id = ?";
		update(sql, userModel.getPassword(),userModel.getUpdated_at(),userModel.getId());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
