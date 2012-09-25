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
/**
 * <p>
 * This package contains the base classes required by the library to communicate with a Bugzilla installation. For example, the {@link Bug} class is 
 * defined here, to provide a useful data object for encapsulating the pertinent values of a bug report. The entry point for accessing a remote Bugzilla 
 * installation, {@link BugzillaConnector}, is also provided here. This package further includes the {@link BugzillaMethod} interface, which allows 
 * implementing classes to execute methods defined in the Bugzilla XML-RPC API. Finally, this package contains the {@link XmlExceptionHandler}
 * for translating the Apache {@link org.apache.xmlrpc.XmlRpcException XmlRpcException} objects to a descriptive {@link BugzillaException} based on the 
 * returned fault code.
 * </p>
 * 
 * <p>
 * To begin using this library, a {@link BugzillaConnector} object must be created and used to connect to a remote instance. For example, one would use the 
 * following code snippet to connect to the sample Landfill installation:
 * <br />
 * <br />
 * <code>
 * BugzillaConnector conn = new BugzillaConnector();<br />
 * conn.connectTo("https://landfill.bugzilla.org/bugzilla-tip/");<br />
 * </code>
 * <br />
 * The connectTo method will attempt to connect to the /xmlrpc.cgi endpoint of the provided URL; this can also be directly appended to the URL string. 
 * If a connection cannot be established, a {@link ConnectionException} is thrown with an appropriate message.
 * </p>
 * <p>
 * Once a connection has been established, classes which implement {@link BugzillaMethod} may be passed to the 
 * {@link BugzillaConnector#executeMethod(BugzillaMethod)} method.
 * For example, to authenticate with the server, one would write the following:
 * <br />
 * <br />
 * <code>
 * BugzillaMethod logIn = new LogIn("user@sample.com", "somepass");<br />
 * try {<br />
 * 	conn.executeMethod(logIn);<br />
 * } catch(BugzillaException e) {<br />
 * 	e.printStackTrace();<br />
 * }<br />
 * </code>
 * <br />
 * If the returned XML document includes a fault code, a {@link BugzillaException} will be thrown from {@code executeMethod}.
 * </p>
 */
package com.j2bugzilla.base;