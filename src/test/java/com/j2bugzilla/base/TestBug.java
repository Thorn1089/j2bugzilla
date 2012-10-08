package com.j2bugzilla.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestBug {

	@Test(expected = IllegalStateException.class)
	public void testBugWithoutRequiredFields() {
		Map<String, Object> fields = new HashMap<String, Object>();
		new Bug(fields);
	}

}
