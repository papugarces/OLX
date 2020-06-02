package co.com.eam.avanzada.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.repository.IUsuarioRepository;

@Controller
public class AppController {
	
	private final IUsuarioRepository IusuarioRepository;
	
	@Autowired
	public AppController(IUsuarioRepository IusuarioRepository) {
		this.IusuarioRepository = IusuarioRepository;
	}

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
