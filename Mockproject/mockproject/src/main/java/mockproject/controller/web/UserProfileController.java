package mockproject.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.UserModel;
import mockproject.service.IUserService;
import mockproject.utils.DateTimeFormat;

@Controller
@SessionAttributes("user")
public class UserProfileController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/ho-so-cua-toi", method = RequestMethod.GET)
	public String userProfileGet(Model model, HttpSession session) {

		return "/web/userProfile/userprofile";
	}
	@RequestMapping(value = "/doi-mat-khau", method = RequestMethod.GET)
	public String passProfileGet(Model model, HttpSession session) {

		return "/web/userProfile/updatepassword";
	}
	
	@RequestMapping(value = "/ho-so-cua-toi", method = RequestMethod.POST)
	public String userProfilePOST(@ModelAttribute UserModel userModel, Model model) {
		String message = null;
		System.out.println("Nhảy vào đây");
		
	
			message = "Cập nhật thông tin tài khoản thành công";
			model.addAttribute("messageforuserprofile", message);
			model.addAttribute("statusforuserprofile", 1);
			
			userModel.setUpdated_at(DateTimeFormat.getNow());
			userService.updateForWebUser(userModel);
	
		return "/web/userProfile/userprofile";
	}
	
	@RequestMapping(value = "/doi-mat-khau", method = RequestMethod.POST)
	public String passProfilePOST(@ModelAttribute UserModel userModel, Model model) {
		String message = null;
		System.out.println("Nhảy vào đây");
		if(samePassWord(userModel.getPassword(),userModel.getId())) {
			
			message = "Cập nhật mật khẩu thành công";
			model.addAttribute("message", message);
			model.addAttribute("status", 1);
			
			userModel.setUpdated_at(DateTimeFormat.getNow());
			userService.updateForWebUser(userModel);
			
		}else {
			message = "Mật khẩu trùng với mật khẩu cũ, xin vùi lòng nhập lại";
			model.addAttribute("message",message);
			model.addAttribute("status",-1);
			
		}
		return "/web/userProfile/updatepassword";
	}
	
	boolean samePassWord(String password1, int user_id) {
		UserModel userModel = userService.findUserById(user_id);
		if(password1.equals(userModel.getPassword())){
			return false;
		}
		
		return true;
	}
}
