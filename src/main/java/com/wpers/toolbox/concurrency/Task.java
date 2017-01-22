package com.wpers.toolbox.concurrency;

public interface Task {
	
	public void add(Task task);
	public void remove(Task task);
	public Task getChild(int id);
	public String getName();
	public String getText();
	public void print();
}
