package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.Comment;

@RunWith(MockitoJUnitRunner.class)
public class TestBugComments {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		BugComments comments = new BugComments(1);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				BugComments rpcMethod = (BugComments)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				Map<String, Map<String, Object[]>> weird = new HashMap<String, Map<String, Object[]>>();
				
				Map<String, Object[]> commentMap = new HashMap<String, Object[]>();
				
				Object[] commentArray = new Object[2];
				
				Map<Object, Object> comment1 = new HashMap<Object, Object>();
				comment1.put("id", 1);
				comment1.put("text", "First comment");
				commentArray[0] = comment1;
				
				Map<Object, Object> comment2 = new HashMap<Object, Object>();
				comment2.put("id", 2);
				comment2.put("text", "Second comment");
				commentArray[1]	= comment2;
				
				commentMap.put("comments", commentArray);
				
				weird.put("1", commentMap);
				
				hash.put("bugs", weird);
				
				rpcMethod.setResultMap(hash);
				return null;
			}
			
		}).when(conn).executeMethod(comments);
		
		conn.executeMethod(comments);
		
		List<Comment> retrieved = comments.getComments();
		
		assertEquals("First comment has incorrect ID", 1, retrieved.get(0).getID());
		assertEquals("First comment has incorrect text", "First comment", retrieved.get(0).getText());
		
		assertEquals("Second comment has incorrect ID", 2, retrieved.get(1).getID());
		assertEquals("Second comment has incorrect text", "Second comment", retrieved.get(1).getText());
	}

}
