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

import java.util.Collections;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;

/**
 * The {@code LogOut} class allows clients to log out of a Bugzilla installation they
 * have previously authenticated with using {@link LogIn}.
 * 
 * @author Tom
 *
 */
public class LogOut implements BugzillaMethod {

	private static final String METHOD_NAME = "User.logout";
	
	@Override
	public void setResultMap(Map<Object, Object> hash) { }

	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.emptyMap();
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
