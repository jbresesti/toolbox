package com.wpers.toolbox.concurrency;

import java.util.concurrent.TimeUnit;

public class TareaThread {

	public static void main(String[] args) {
		Runnable tarea = () -> {
			try {
				String threadName = Thread.currentThread().getName();
				System.out.println("Antes " + threadName);
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Despues " + threadName);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	
		tarea.run();
		
		Thread thread = new Thread(tarea);
		thread.start();
		System.out.println("Hecho");

	}

}
