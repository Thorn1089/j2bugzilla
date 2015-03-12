# LogOut #

The `LogOut` BugzillaMethod will de-authenticate the current user with the Bugzilla installation. Any further RPC calls which require a logged-in user will fail.


# Logging out #

The `LogOut` method takes no parameters and returns no values. Simply pass a new instance to an existing BugzillaConnector:

```
conn.executeMethod(new LogOut());
```