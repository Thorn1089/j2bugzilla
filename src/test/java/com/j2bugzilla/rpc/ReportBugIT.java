package com.j2bugzilla.rpc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugFactory;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

@RunWith(Parameterized.class)
public class ReportBugIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/"});
		
		return urls;
	}
	
	private String url;
	
	public ReportBugIT(String url) {
		this.url = url;
	}
	
	@Test
	public void testGetID() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
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
