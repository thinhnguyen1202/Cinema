package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.IUserDAO;
import mockproject.model.UserModel;
import mockproject.service.IUserService;
@Service
public class UserService  implements IUserService{

	@Autowired
	IUserDAO userDAO;
	
	
	@Override
	public Integer register(UserModel user) {
		if(!userDAO.checkExistsUsername(user.getUsername())) {
			
			return userDAO.register(user);
		} else {
			return -1;
		}
		
	}
	@Override
	public List<UserModel> listALlUser() {
		return userDAO.listALlUser();
	}

	@Override
	public UserModel findUserByUserNameAndId(String username, String password) {
		return userDAO.findUserByUserNameAndId(username, password);
	}

	@Override
	public UserModel findUserById(int id) {
		return userDAO.findUserById(id);
	}

	@Override
	public void updateUserWithId(UserModel userModel) {
		userDAO.updateUserWithId(userModel);
	}

	@Override
	public UserModel getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDAO.getUserByName(username);
	}

	@Override
	public int insertUser(UserModel userModel) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(userModel);
	}
	
	@Override
	public void updateForWebUser(UserModel userModel) {
		userDAO.updateForWebUser(userModel);
	}
	
	@Override
	public void updatepass(UserModel userModel) {
		userDAO.updatepass(userModel);
	}
	
	
	
	
	
	
	
	

}
