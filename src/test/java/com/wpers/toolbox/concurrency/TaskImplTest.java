package com.wpers.toolbox.concurrency;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wpers.toolbox.concurrency.Task;
import com.wpers.toolbox.concurrency.TaskImpl;

public class TaskImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrearTareas() {
		Task desarrollolmqueue = new TaskImpl("desarrollomqueue", "tarea principal");		
		Task revisarmanual = new TaskImpl("revisarmanual", "tarea secundaria");
		desarrollolmqueue.add(revisarmanual);
		assertEquals(desarrollolmqueue.getChild(0).getName(), "revisarmanual");
	}

}
