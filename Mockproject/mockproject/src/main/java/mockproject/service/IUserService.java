package mockproject.service;

import java.util.List;

import mockproject.model.UserModel;

public interface IUserService {
	Integer register(UserModel user);
	List<UserModel> listALlUser();
	UserModel findUserByUserNameAndId(String username, String password);
	UserModel findUserById(int id);
	void updateUserWithId( UserModel userModel);
	UserModel getUserByName(String username);
	int insertUser(UserModel userModel);
	void updateForWebUser(UserModel userModel);
	void updatepass(UserModel userModel);
}
