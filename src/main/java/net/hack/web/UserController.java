package net.hack.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	// 저장할 곳이 필요하니 자바의 리스트 컬렉션을 하나 만든다,.

	private List<User> users = new ArrayList<User>();

	@PostMapping("/create")
	// public String create(String userId, String password, String name, String
	// email) {
	public String create(User user) {

		// System.out.println("userId : " + userId + " name : " +name);
		System.out.println("user : " + user);
		users.add(user);

		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {

		model.addAttribute("usersInfo", users);
		return "list";
	}

}
