package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;

@RestController
public class TestRestFullController {

	@RequestMapping("/getUser/{id}/{name}")
	public Object getUser(@PathVariable("id") String id, @PathVariable("name") String name) {
		User u=new User();
		u.setId(id);
		u.setUserName(name);
		return u;
	}
}
