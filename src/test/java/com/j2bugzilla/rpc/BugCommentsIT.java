package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

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
public class BugCommentsIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/", 1058});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/", 1058});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/", 1058});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/", 1058});
		
		return urls;
	}
	
	private String url;
	
	private int id;
	
	public BugCommentsIT(String url, int id) {
		this.url = url;
		this.id = id;
	}
	
	@Test
	public void testGetComments() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		BugComments comments = new BugComments(id);
		conn.executeMethod(comments);
		assertThat("Comments list should not be empty", comments.getComments().isEmpty(), is(false));
	}

}
