package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.core.StringStartsWith.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

@RunWith(Parameterized.class)
public class BugzillaVersionIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/", "3.6"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/", "4.0"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/", "4.2"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/", "4.4"});
		
		return urls;
	}
	
	private String url;
	
	private String version;
	
	public BugzillaVersionIT(String url, String version) {
		this.url = url;
		this.version = version;
	}
	
	@Test
	public void testGetVersion() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		BugzillaVersion version = new BugzillaVersion();
		conn.executeMethod(version);
		assertThat("Returned version should match expected value", version.getVersion(), startsWith(this.version));
	}

}
