package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();

    @PostMapping
	public String addTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/tasks': '" + taskdescription + "'");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Task task;
			task = mapper.readValue(taskdescription, Task.class);
			for (Task t : tasks) {
				if (t.getTaskdescription().equals(task.getTaskdescription())) {
					System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
					return "redirect:/"; // duplicates will be ignored
				}
			}
			System.out.println("...adding task: '" + task.getTaskdescription() + "'");
			tasks.add(task);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return List<Task>;
	}

	@CrossOrigin
	@PostMapping("/v1/delete")
	public String delTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/delete': '" + taskdescription + "'");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Task task;
			task = mapper.readValue(taskdescription, Task.class);
			Iterator<Task> it = tasks.iterator();
			while (it.hasNext()) {
				Task t = it.next();
				if (t.getTaskdescription().equals(task.getTaskdescription())) {
					System.out.println("...deleting task: '" + task.getTaskdescription() + "'");
					it.remove();
					return "redirect:/";
				}
			}
			System.out.println(">>>task: '" + task.getTaskdescription() + "' not found!");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}


	@CrossOrigin
	@PostMapping("/v1/idkEither")
	public String fakeAddTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/idkEither': '" + taskdescription + "'");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Task task;
			task = mapper.readValue(taskdescription, Task.class);
			for (Task t : tasks) {
				if (t.getTaskdescription().equals(task.getTaskdescription())) {
					System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
					return "redirect:/"; // duplicates will be ignored
				}
			}
			System.out.println("...adding task: '" + task.getTaskdescription() + "'");
			tasks.add(task);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
