package com.j2bugzilla.base;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Test;

public class TestXmlExceptionHandler {

	@Test
	public void testHandleKnownFault() {
		XmlRpcException ex = new XmlRpcException(300, "Incorrect username or password");
		BugzillaException bEx = XmlExceptionHandler.handleFault(ex);
		assertThat("The error message appears to be known", bEx.getMessage(), not(containsString("unknown")));
	}
	
	@Test
	public void testHandleUnknownFault() {
		XmlRpcException ex = new XmlRpcException(-1, "Unknown error");
		BugzillaException bEx = XmlExceptionHandler.handleFault(ex);
		assertThat("The error message appears to be unknown", bEx.getMessage(), containsString("unknown"));
	}

}
