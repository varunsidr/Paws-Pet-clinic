package org.springframework.samples.petclinic.system;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	private final Map<String, String> accounts = new ConcurrentHashMap<>();

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "auth/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
			HttpSession session, Model model) {
		if (bindingResult.hasErrors()) {
			return "auth/login";
		}
		if (!loginForm.password().equals(accounts.get(loginForm.email().trim().toLowerCase()))) {
			model.addAttribute("loginError", "Email or password is incorrect.");
			return "auth/login";
		}
		session.setAttribute("signedInEmail", loginForm.email().trim().toLowerCase());
		return "redirect:/";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "auth/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "auth/register";
		}
		String email = registrationForm.email().trim().toLowerCase();
		if (accounts.putIfAbsent(email, registrationForm.password()) != null) {
			model.addAttribute("registrationError", "An account with this email already exists.");
			return "auth/register";
		}
		return "redirect:/login?registered";
	}

	public record LoginForm(@jakarta.validation.constraints.Email @jakarta.validation.constraints.NotBlank String email,
			@jakarta.validation.constraints.NotBlank String password) {
		public LoginForm() {
			this("", "");
		}
	}

	public record RegistrationForm(@jakarta.validation.constraints.NotBlank String fullName,
			@jakarta.validation.constraints.Email @jakarta.validation.constraints.NotBlank String email,
			@jakarta.validation.constraints.Size(min = 6) String password) {
		public RegistrationForm() {
			this("", "", "");
		}
	}

}
