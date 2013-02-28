package com.j2bugzilla.rpc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

@RunWith(Parameterized.class)
public class UpdateBugIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/", "ASSIGNED", 1058});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/", "ASSIGNED", 1058});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/", "IN_PROGRESS", 1058});
		
		return urls;
	}
	
	private String url;
	
	private String status;
	
	private int id;
	
	public UpdateBugIT(String url, String status, int id) {
		this.url = url;
		this.status = status;
		this.id = id;
	}
	
	@Test
	public void test() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		LogIn logIn = new LogIn("j2bugzilla@gmail.com", "javabugzilla");
		conn.executeMethod(logIn);
		
		GetBug get = new GetBug(id);
		conn.executeMethod(get);
		
		Bug bug = get.getBug();
		bug.setStatus(status);
		
		UpdateBug update = new UpdateBug(bug);
		conn.executeMethod(update);
	}

}
