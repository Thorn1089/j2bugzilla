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

import com.j2bugzilla.base.Attachment;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestAddAttachment {

	@Mock
	private BugzillaConnector conn;
	
	@Mock
	private Attachment attach;
	
	@Test
	public void test() throws BugzillaException {
		AddAttachment add = new AddAttachment(attach, 0);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				AddAttachment rpcMethod = (AddAttachment)invocation.getArguments()[0];
				
				Map<Object, Object> attachmentIDs = new HashMap<Object, Object>();
				attachmentIDs.put("id", 1);
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				hash.put("attachments", attachmentIDs);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
		
		}).when(conn).executeMethod(add);
		
		conn.executeMethod(add);
		
		assertEquals("Incorrect attachment ID", 1, add.getID());		
	}

}
