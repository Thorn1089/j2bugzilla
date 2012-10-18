/*
 * Copyright 2011 Thomas Golden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestGetBug {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		GetBug getBug = new GetBug(1);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetBug rpcMethod = (GetBug)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				
				Object[] bugArray = new Object[1];
				Map<String, Object> bug = new HashMap<String, Object>();
				bug.put("product", "Test");
				bug.put("component", "Test");
				bug.put("summary", "Testing the get method");
				bug.put("version", "1.0.1");
				
				//Add some flags to test those code paths as well
				@SuppressWarnings("unchecked")
				Map<String, Object>[] flags = new HashMap[3];
				
				flags[0] = new HashMap<String, Object>();
				flags[1] = new HashMap<String, Object>();
				flags[2] = new HashMap<String, Object>();
				
				flags[0].put("name", "tested");
				flags[0].put("status", "?");
				flags[1].put("name", "reproduced");
				flags[1].put("status", "+");
				flags[2].put("name", "ui");
				flags[2].put("status", "-");
				bug.put("flags", flags);
				
				bugArray[0] = bug;
				
				hash.put("bugs", bugArray);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(getBug);
		
		conn.executeMethod(getBug);
		
		assertEquals("Bug product is incorrect", "Test", getBug.getBug().getProduct());
		assertEquals("Bug component is incorrect", "Test", getBug.getBug().getComponent());
		assertEquals("Bug summary is incorrect", "Testing the get method", getBug.getBug().getSummary());
		assertEquals("Bug version is incorrect", "1.0.1", getBug.getBug().getVersion());
		assertEquals("Bug does not contain flags", 3, getBug.getBug().getFlags().size());
	}
	
	@Test
	public void testBeforeCall() {
		GetBug getBug = new GetBug(1);
		assertNull("Returned bug is not null", getBug.getBug());
	}

}
