package mockproject.dao;

import java.util.List;

import mockproject.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	int register(UserModel user);
	boolean checkExistsUsername(String username);
	List<UserModel> listALlUser();
	UserModel findUserByUserNameAndId(String username, String password);
	UserModel findUserById(int id);
	UserModel getUserByName(String username);
	void updateUserWithId(UserModel userModel);
	int insertUser(UserModel userModel);
	void updateForWebUser(UserModel userModel);
	void updatepass(UserModel userModel);
}

