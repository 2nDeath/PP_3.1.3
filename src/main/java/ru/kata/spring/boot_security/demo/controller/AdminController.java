package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService uService;
	@Autowired
	private RoleRepository roleRepository;

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
	public String saveUser(@ModelAttribute("user") User user) {
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