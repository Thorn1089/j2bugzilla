package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.number.OrderingComparison.greaterThan;

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
public class GetAttachmentsIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/", 6155});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/", 6155});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/", 4315});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/", 18131});
		
		return urls;
	}
	
	private String url;
	
	private int id;
	
	public GetAttachmentsIT(String url, int id) {
		this.url = url;
		this.id = id;
	}
	
	@Test
	public void testGetAttachments() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		GetAttachments attachments = new GetAttachments(id);
		conn.executeMethod(attachments);
		assertThat("At least one attachment should exist", attachments.getAttachments().size(), greaterThan(0));
	}

}
