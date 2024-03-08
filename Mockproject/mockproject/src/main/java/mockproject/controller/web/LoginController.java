package mockproject.controller.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import mockproject.model.UserModel;
import mockproject.service.IUserService;
@SessionAttributes(value = {"user", "role"})
@Controller(value = "LoginControllerForWeb")
public class LoginController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "/web/signin";
		}else {
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value = "/signin-thanh-cong" , method = RequestMethod.GET)
	public String loginThanhCong(Model model, Principal principal) {
		
		String userName = principal.getName();
		UserModel user = userService.getUserByName(userName);
		if(user.getRole().equals("ROLE_ADMIN")) {
			model.addAttribute("user", user);
			return "redirect:/admin";
		}
		
		model.addAttribute("user",user);
		return "redirect:/home";
		
	}
	
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	   public String logoutSuccessfulPage(HttpServletRequest request, HttpServletResponse response) {  
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
	        if (auth != null){      
	           new SecurityContextLogoutHandler().logout(request, response, auth);  
	        }  
	      // model.addAttribute("title", "Logout");
	       return "redirect:/home";
	   }
	
	
	@RequestMapping(value = "/home/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		
		//sau khi userlogin thanh cong se co principla
		String userName = principal.getName();
		
		UserModel user = userService.getUserByName(userName);
		
		String role = user.getRole();
		if(role.equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}else {
			System.out.println("UserName: " + userName);
			
			
			return "/web/login/userInfoPage";
			
		}
		
	}
	@RequestMapping(value = "/you-are-not-authorized", method = RequestMethod.GET)
	public String youAreNotAuthorized(Model model, Principal principal, HttpSession session) {
		return "redirect:/403";
	}
	
	@RequestMapping(value ="/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal, HttpSession session) {
		  	
		   String userName = principal.getName();
		   UserModel user = userService.getUserByName(userName);
		   
		   model.addAttribute("user", user);
	       return "/web/login/403Page";
	}
	
	
}
