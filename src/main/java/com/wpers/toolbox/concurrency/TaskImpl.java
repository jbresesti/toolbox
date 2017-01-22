package com.wpers.toolbox.concurrency;

import java.util.ArrayList;
import java.util.List;

public class TaskImpl implements Task {
	private String name;
	private String text;
	
	List<Task> tasks = new ArrayList<Task>();

	public TaskImpl(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}

	@Override
	public void add(Task task) {
		tasks.add(task);

	}

	@Override
	public void remove(Task task) {
		tasks.remove(task);
	}

	@Override
	public Task getChild(int id) {
		return tasks.get(id);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

}
