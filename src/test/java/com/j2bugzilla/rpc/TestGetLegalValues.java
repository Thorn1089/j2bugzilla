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
import java.util.Set;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.rpc.GetLegalValues.Fields;

@RunWith(MockitoJUnitRunner.class)
public class TestGetLegalValues {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void testGetLegalValues() throws BugzillaException {
		GetLegalValues getLegalVals = new GetLegalValues(Fields.SEVERITY);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetLegalValues rpcMethod = (GetLegalValues)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				
				Object[] arr = new Object[1];
				Map<Object, Object> valMap = new HashMap<Object, Object>();
				arr[0] = valMap;
				
				
				Map<String, String> val1 = new HashMap<String, String>();
				val1.put("name", "blocker");
				Map<String, String> val2 = new HashMap<String, String>();
				val2.put("name", "major");
				Map<String, String> val3 = new HashMap<String, String>();
				val3.put("name", "minor");
				
				Object[] values = new Object[] { val1, val2, val3 };
				valMap.put("values", values);
								
				hash.put("fields", arr);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(getLegalVals);
		
		conn.executeMethod(getLegalVals);
		
		Set<String> severities = getLegalVals.getLegalValues();
		
		assertTrue("Severity missing", severities.contains("blocker"));
		assertTrue("Severity missing", severities.contains("major"));
		assertTrue("Severity missing", severities.contains("minor"));
	}

}
