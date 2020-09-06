package co.com.eam.avanzada.controller;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {
	


	@GetMapping({"/","/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/home"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	//@GetMapping("/admin")
	//public String admin() {
	//	return "admin";
	//}
	
	@GetMapping("/header-admin")
	public String headerAdmin() {
		return "header-admin";
	}
	
}
