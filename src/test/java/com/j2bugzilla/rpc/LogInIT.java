package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

public class LogInIT {

	@Test
	public void testGetUserID() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo("https://landfill.bugzilla.org/bugzilla-4.4-branch");
		
		LogIn logIn = new LogIn("j2bugzilla@gmail.com", "javabugzilla");
		conn.executeMethod(logIn);
		assertThat("User ID should not be -1", logIn.getUserID(), is(not(-1)));
	}

}
