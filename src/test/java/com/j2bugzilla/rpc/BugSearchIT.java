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
import com.j2bugzilla.rpc.BugSearch.SearchLimiter;

@RunWith(Parameterized.class)
public class BugSearchIT {

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
	
	public BugSearchIT(String url) {
		this.url = url;
	}
	
	@Test
	public void testGetSearchResults() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		BugSearch search = new BugSearch(new BugSearch.SearchQuery(SearchLimiter.SUMMARY, "euro"));
		conn.executeMethod(search);
		
		assertThat("At least one search result should be returned", search.getSearchResults().size(), greaterThan(0));
	}

}
