package mockproject.controller.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mockproject.model.UserModel;
import mockproject.service.impl.UserService;

@Controller(value = "RegisterControllerWeb")
@SessionAttributes(value = {"user"})
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/signup")
	
	
	public ModelAndView register(HttpSession session) {
		ModelAndView mav  = null;
		if(session.getAttribute("user") == null) {
			mav = new ModelAndView("web/signin");
		}else {
			mav = new ModelAndView("redirect:/home");
		}
		return mav;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String registerSubmit(@ModelAttribute UserModel user, ModelMap modelMap) {
		
		UserModel userModel = user;
		int check = userService.register(user);
		if( check == -1) {
			modelMap.addAttribute("message"," <script>\r\n"
					+ "        alert(\"Tạo tài khoản thất bại Username đã tồn tại\");\r\n"
					+ "    </script>");
			
		}else {
			modelMap.addAttribute("status", true);
			modelMap.addAttribute("message", " <script>\r\n"
					+ "        alert(\"Tạo tài khoản thành công\");\r\n"
					+ "    </script>");
			
		}
		
		return "web/signin";
	}
}
