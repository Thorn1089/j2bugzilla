package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

public class UpdateBugIT {

	@Test
	public void test() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo("https://landfill.bugzilla.org/bugzilla-4.4-branch");
		
		LogIn logIn = new LogIn("j2bugzilla@gmail.com", "javabugzilla");
		conn.executeMethod(logIn);
		
		GetBug get = new GetBug(1058);
		conn.executeMethod(get);
		
		Bug bug = get.getBug();
		bug.setStatus("IN_PROGRESS");
		
		UpdateBug update = new UpdateBug(bug);
		conn.executeMethod(update);
	}

}
