package bo.com.cognos.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String irALogin() {
		return "login";
	}
	
}
