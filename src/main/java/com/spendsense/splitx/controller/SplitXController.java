package com.spendsense.splitx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.service.GroupService;

@RestController
public class SplitXController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/home/{id}")
	public List<Group> getGroupsByUser(@PathVariable long id) {
		return groupService.getGroupsByUser(id);
	}
}
