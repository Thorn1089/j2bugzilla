package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

@RunWith(Parameterized.class)
public class LogInIT {

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
	
	public LogInIT(String url) {
		this.url = url;
	}
	
	@Test
	public void testGetUserID() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		LogIn logIn = new LogIn("j2bugzilla@gmail.com", "javabugzilla");
		conn.executeMethod(logIn);
		assertThat("User ID should not be -1", logIn.getUserID(), is(not(-1)));
	}

}
