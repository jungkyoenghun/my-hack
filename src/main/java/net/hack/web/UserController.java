package net.hack.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.hack.domain.User;
import net.hack.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	// 저장할 곳이 필요하니 자바의 리스트 컬렉션을 하나 만든다,.

	//private List<User> users = new ArrayList<User>();
	
	// 어딘가에 있는 놈을 꺼내 쓴다
	// 직접 인스턴스를 만들거나 하지 않고
	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	// public String create(String userId, String password, String name, String
	// email) {
	public String create(User user) {

		// System.out.println("userId : " + userId + " name : " +name);
		System.out.println("user : " + user);
		//users.add(user);
		//이렇게 save 만 하면 저장된다.
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}

	@GetMapping("")
	public String list(Model model) {

		//model.addAttribute("usersInfo", users);
		model.addAttribute("usersInfo", userRepository.findAll());
		return "/user/list";
	}
	
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model){
		
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	
	// 정보를 수정하는 역활을 하는 구나
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		
		User user = userRepository.findOne(id);
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
		
	}
	
	
}
