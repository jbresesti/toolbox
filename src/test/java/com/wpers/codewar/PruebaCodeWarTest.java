package com.wpers.codewar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wpers.codewar.PruebaCodeWar;

public class PruebaCodeWarTest {

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
	public void test() {
		PruebaCodeWar pcdw = new PruebaCodeWar();
		assertEquals("Hola mundo", pcdw.prueba("mundo"));
	}

}
