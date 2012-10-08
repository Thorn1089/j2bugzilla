package com.j2bugzilla.base;

import org.junit.Test;

public class TestBugFactory {

	@Test(expected = IllegalStateException.class)
	public void testImmutableState() {
		BugFactory factory = new BugFactory();
		factory.newBug().newBug();
	}

	@Test(expected = IllegalStateException.class)
	public void testOrderOfOperations() {
		BugFactory factory = new BugFactory();
		factory.createBug();
	}
}
