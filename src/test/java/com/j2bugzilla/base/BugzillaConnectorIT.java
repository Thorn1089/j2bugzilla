package com.j2bugzilla.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BugzillaConnectorIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/"});
		urls.add(new Object[]{"http://landfill.bugzilla.org/bugzilla-4.0-branch/"});
		urls.add(new Object[]{"http://landfill.bugzilla.org/bugzilla-4.2-branch/"});
		urls.add(new Object[]{"http://landfill.bugzilla.org/bugzilla-4.4-branch/"});
		
		return urls;
	}
	
	private String url;
	
	public BugzillaConnectorIT(String url) {
		this.url = url;
	}
	
	@Test
	public void testConnect() throws ConnectionException {
		BugzillaConnector conn = new BugzillaConnector();
		
		conn.connectTo(url);
	}

}
