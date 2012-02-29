package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.rpc.BugSearch.SearchLimiter;
import com.j2bugzilla.rpc.BugSearch.SearchQuery;

@RunWith(MockitoJUnitRunner.class)
public class TestBugSearch {

	@Mock
	private BugzillaConnector conn;
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoLimits() {
		new BugSearch();
	}
	
	@Test
	public void testQuery() throws BugzillaException {
		SearchQuery query = new SearchQuery(SearchLimiter.OPERATING_SYSTEM, "Windows");
		BugSearch search = new BugSearch(query);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				BugSearch rpcMethod = (BugSearch)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				
				Object[] bugArray = new Object[1];
				
				Map<String, Object> bug = new HashMap<String, Object>();
				bug.put("product", "Test");
				bug.put("component", "Test");
				bug.put("summary", "Testing the search method");
				bug.put("version", "1.0");
				
				bugArray[0] = bug;
				
				hash.put("bugs", bugArray);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(search);
		
		conn.executeMethod(search);
		
		List<Bug> bugs = search.getSearchResults();
		
		assertEquals("Bug list is incorrect size", 1, bugs.size());
		
		assertEquals("Bug product is incorrect", "Test", bugs.get(0).getProduct());
		assertEquals("Bug component is incorrect", "Test", bugs.get(0).getComponent());
		assertEquals("Bug summary is incorrect", "Testing the search method", bugs.get(0).getSummary());
		assertEquals("Bug version is incorrect", "1.0", bugs.get(0).getVersion());
	}

}
