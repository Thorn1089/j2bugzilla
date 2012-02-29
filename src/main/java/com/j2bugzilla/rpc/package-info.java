/**
 * <p>This package groups all of the remote procedure calls provided by the
 * J2Bugzilla API. Each class in this package represents a method which the
 * Bugzilla XML-RPC interface provides for use by clients. To call these methods
 * on a Bugzilla installation, the user must construct a new instance of the
 * object and pass it to the 
 * {@link com.j2bugzilla.base.BugzillaConnector#executeMethod(com.j2bugzilla.base.BugzillaMethod)}
 * for processing.</p>
 */
package com.j2bugzilla.rpc;