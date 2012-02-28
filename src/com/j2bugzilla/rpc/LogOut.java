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
