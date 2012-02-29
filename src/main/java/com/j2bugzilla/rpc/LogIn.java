package com.j2bugzilla.rpc;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;


/**
 * The <code>LogIn</code> class allows a client to authenticate as a specific user
 * with a specific Bugzilla installation. This is necessary for most installations
 * to view or otherwise modify {@link com.j2bugzilla.base.Bug}s.
 * 
 * @author Tom
 *
 */
public class LogIn implements BugzillaMethod {

	/**
	 * The method Bugzilla will execute via XML-RPC
	 */
	private static final String METHOD_NAME = "User.login";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	/**
	 * This method logs a specified user into the Bugzilla installation via XML-RPC.
	 * The access level of the user specified dictate which bugs can be viewed and
	 * modified, and which products can be accessed or altered.
	 * 
	 * Unless your Bugzilla installation is set up using https://, the user/pass combination
	 * is sent as plaintext. This is also true when visiting the site in your browser, however,
	 * so it is no <em>less</em> safe than normal. If your bug tracking software needs higher
	 * security, configure Bugzilla to use https:// and consider using a different library; as of
	 * this writing (5/23/2010) there is no guarantee that this will connect via SSL.
	 * 
	 * @param username the email address specifying the user
	 * @param password the plaintext password for that user
	 */
	public LogIn(String username, String password) {
		params.put("login", username);
		params.put("password", password);
	}
	
	/**
	 * Returns the integer ID of the logged in user, or -1 if the login failed
	 * @return An <code>int</code> representing the user ID of the logged in user
	 */
	public int getUserID() {
		if(hash.containsKey("id")) {
			Integer i = (Integer)hash.get("id");
			return i.intValue();
		} else {
			return -1;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
