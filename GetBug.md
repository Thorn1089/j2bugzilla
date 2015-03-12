# GetBug #

The `GetBug` BugzillaMethod allows the retrieval of a Bug where the ID or alias is known ahead of time.


# Retrieving reports with GetBug #

The `GetBug` class provides two constructors to retrieve Bug objects from your Bugzilla installation. You can either provide the numerical ID, or, if your installation supports aliases, the unique `String` alias:

```
GetBug byID = new GetBug(1337);
GetBug byAlias = new GetBug("DEADBEEF");

conn.executeMethod(byID);
conn.executeMethod(byAlias);
```

Once the Bug has been retrieved, you can obtain a reference to it from the `GetBug` object and operate on it further:

```
Bug bug = byID.getBug();
```