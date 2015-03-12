# BugzillaConnector #

The main access point for all of the J2Bugzilla RPC methods is via a `BugzillaConnector` object, which allows the execution of any `BugzillaMethod`. Each `BugzillaConnector` points to a specific Bugzilla installation.


# Connecting to an installation #

First, you must create a new `BugzillaConnector` object:

`BugzillaConnector conn = new BugzillaConnector();`

Now, you can connect to an active Bugzilla installation. Remember, your Bugzilla installation must have the optional Perl modules installed for XML-RPC calls.

`conn.connectTo("http://landfill.bugzilla.org/");`

This will cause the connector to attempt to contact the XML-RPC endpoint at http://landfill.bugzilla.org/xmlrpc.cgi. If your installation uses [Basic Access Authentication](http://en.wikipedia.org/wiki/Basic_access_authentication) you can also provide the credentials:

`conn.connectTo("http://landfill.bugzilla.org/", "user", "pass");`

Now you can execute various RPC methods. Many methods require logging in first, so you will likely want to use the LogIn method next.