# LogIn #

The `LogIn` method allows clients to authenticate with your Bugzilla installation by providing the same credentials they would use in the web interface.


# Logging in #

As with all BugzillaMethod objects, we will pass our `LogIn` object to a pre-existing BugzillaConnector. Once we have a connector, we'll construct our `LogIn` object with our credentials:

```
LogIn logIn = new LogIn("aeinstein@princeton.edu", "e=mc^2");
conn.executeMethod(logIn);
```

At this point, any further calls with this connector will be processed as if the "aeinstein" user had made them. All user access restrictions will be present. If a user is allowed to search and view, but not edit bugs, this will be reflected by an exception when attempting to modify a bug via J2Bugzilla. The client code should be ready to handle any such exceptions thrown by the BugzillaConnector's `executeMethod` method.

We can also retrieve the unique ID of this user after they have logged in:

```
int ID = logIn.getID();
```