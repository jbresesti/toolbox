package com.wpers.toolbox.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TareaExecutor {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hola " + threadName);
		});

	}

}
