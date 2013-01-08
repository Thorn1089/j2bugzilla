package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugFactory;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

public class ReportBugIT {

	@Test
	public void testGetID() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo("https://landfill.bugzilla.org/bugzilla-4.4-branch");
		
		LogIn logIn = new LogIn("j2bugzilla@gmail.com", "javabugzilla");
		conn.executeMethod(logIn);
		
		Bug bug = new BugFactory().newBug()
				.setProduct("WorldControl")
				.setComponent("EconomicControl")
				.setVersion("1.0")
				.setPlatform("PC")
				.setOperatingSystem("Windows XP")
				.setDescription("Cannot control economy")
				.setSummary("No control!")
				.createBug();
		
		ReportBug report = new ReportBug(bug);
		conn.executeMethod(report);
		
		assertThat("Bug ID should not be -1", report.getID(), is(not(-1)));
	}

}
