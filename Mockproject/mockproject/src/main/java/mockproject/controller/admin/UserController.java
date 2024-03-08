package mockproject.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mockproject.model.FilmModel;
import mockproject.model.UserModel;
import mockproject.service.IUserService;
import mockproject.utils.DateTimeFormat;

@Controller(value = "userControllerForAdmin")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String listAllUser(Model model) {
		List<UserModel> listUser = userService.listALlUser();
		model.addAttribute("listUser", listUser);
		return "/admin/user/listuser";
	}

	@RequestMapping(value = "/admin/user/edituser/{id}", method = RequestMethod.GET)
	public String editUserGet(Model model, @PathVariable int id) {

		UserModel userModel = userService.findUserById(id);

		model.addAttribute("userModel", userModel);

		return "/admin/user/useredit";
	}

	@RequestMapping(value = "/admin/user/edituser/{id}", method = RequestMethod.POST)
	public String editUserPost(@ModelAttribute("userModel") UserModel userModel) {

		System.out.println("vao day");

		UserModel user = userModel;

		user.setUpdated_at(DateTimeFormat.getNow());
		userService.updateUserWithId(user);
		return "redirect:/admin/user";
	}

	// Thêm mới người dùng
	@RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
	public String createUser(Model model) {

		UserModel user = new UserModel();

		model.addAttribute("userModel", user);

		return "/admin/user/usercreate";
	}

	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
	public String createUserPost(@ModelAttribute("userModel") UserModel userModelInForm, Model model) {
		UserModel userModel = userModelInForm;
		userModel.setCreated_at(DateTimeFormat.getNow());
		userModel.setUpdated_at(DateTimeFormat.getNow());
		int validUser = validUserModel(userModel);
		String message = null;
		int status = 0;
		switch (validUser) {
		case 1:
			status = 1;
			
			int id = userService.insertUser(userModel);
			message = "Thêm mới user thành công (id = " + id +"): " + userModel.getUsername();
			model.addAttribute("message",message);
			model.addAttribute("status", status);
			
			
			break;
		
		case -4:
			message = "Đã tồn tại tài khoản, vui lòng nhập lại ";
			status = -4;
			model.addAttribute("message",message);
			model.addAttribute("status", status);
			break;
		default:
			break;

		}
		
		if(validUser == 1) {
			// list all user
			List<UserModel> listUser = userService.listALlUser();
			model.addAttribute("listUser",listUser);
			return "/admin/user/listuser";
		}
		
		// some thing wrong with name
		model.addAttribute("userModel", userModel);
		return "/admin/user/usercreate";
	}

	int validUserModel(UserModel userModel) {

		if (userService.getUserByName(userModel.getUsername()) != null) {
			return -4;
		}

		return 1;
	}

	// xóa người dùng thì phải xóa luôn thể hiện ở chỗ khác
	// xóa thể hiện ở booking
	// lấy được ghế id ở booking_details -> set lại trạng thái cho nó
	// xóa thể hiện booking_details với booking_id
	// xóa thể hiện ở tickets với booking_details_id
}
