package com.gis.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gis.dto.LoginRequest;
import com.gis.dto.UnlockRequest;
import com.gis.dto.UserRequest;
import com.gis.service.UserService;

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public String showRegistrationForm(Model model) {
		logger.info(" Load User Registration page");
		model.addAttribute("user", new UserRequest());
		return "signup";
	}

	@PostMapping("/user")
	public String processRegistrationForm(@Validated @ModelAttribute("user") UserRequest user,
			BindingResult bindingResult, Model model) {
		logger.warn(" Load User Registration page");

		if (bindingResult.hasErrors()) {
			return "signup";
		}
		logger.info(" process registration request User Registration page");

		String createUser = userService.createUser(user);
		model.addAttribute("msg", createUser);
		return "signup";
	}

	@GetMapping("/unlock")
	public String showUnlockForm(Model model) {
		logger.info(" request to load unlock page ");

		model.addAttribute("unlockform", new UnlockRequest());
		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockAccount(@Valid @ModelAttribute("unlockform") UnlockRequest unlock, BindingResult bindingResult,
			Model model) {
		logger.warn(" request to process unlock page ");
		if (bindingResult.hasErrors()) {
			return "unlock";
		}
		logger.info(" request to process unlock page ");

		String unlockAccount = userService.unlockAccount(unlock);
		model.addAttribute("msg", unlockAccount);
		return "unlock";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		logger.info(" request to Login  page ");
		model.addAttribute("user", new LoginRequest());
		return "login";
	}

	@PostMapping("/loginlogic")
	public String loginAccount(@Valid @ModelAttribute("user") LoginRequest request, BindingResult bindingResult,
			Model model) {
		logger.warn(" request to process Login  page ");

		if (bindingResult.hasErrors()) {
			return "login";
		}
		logger.info(" request to process Login  page ");

		String status = userService.loginUser(request);
		if (status.contains("success")) {
			logger.warn(" request to Login  page ");
			return "redirect:/Dashboard";

		}
		model.addAttribute("errmsg", status);
		return "login";
	}

}
