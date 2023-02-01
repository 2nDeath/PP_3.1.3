package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserService uService;

	private RoleRepository roleRepository;

	@Autowired
	public AdminController(UserService userService, RoleRepository roleRepository) {
		this.uService = userService;
		this.roleRepository = roleRepository;
	}

	@GetMapping(value = "/allUsers")
	public String usersList(ModelMap model) {
		List<User> users = uService.getAllUsers();
		model.addAttribute("users", users);
		return "usersList";
	}

	@GetMapping(value = "/newUser")
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		List<Role> roles =  roleRepository.findAll();
		model.addAttribute("roles", roles);
		return "addUserForm";
	}

	@PostMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addUserForm";
		}
		uService.saveUser(user);
		return "redirect:/admin/allUsers";
	}

	@RequestMapping(value = "/updateUser/{id}")
	public String updateUser(@PathVariable(name = "id") int id, ModelMap model) {
		User user = uService.getUser(id);
		user.setPassword(new String());
		model.addAttribute("user", user);
		List<Role> roles =  roleRepository.findAll();
		model.addAttribute("roles", roles);
		return "addUserForm";
	}

	@RequestMapping(value = "/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		uService.deleteUser(id);
		return "redirect:/admin/allUsers";
	}
	
}