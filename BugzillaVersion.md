# BugzillaVersion #

The `BugzillaVersion` class is a BugzillaMethod which allows you to programmatically check the version of the running Bugzilla installation.

# Checking the version with BugzillaVersion #

Checking the version your Bugzilla installation is running is simple with `BugzillaVersion`. Simply pass an instance to your BugzillaConnector like so:

```
BugzillaVersion versionCheck = new BugzillaVersion();
conn.executeMethod(versionCheck);
String version = versionCheck.getVersion();
```