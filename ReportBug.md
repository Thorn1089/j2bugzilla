# ReportBug #

The `ReportBug` BugzillaMethod allows clients to add new Bug reports to their Bugzilla installation programmatically.


# Filing a new bug #

First, you must construct a new Bug using the BugFactory:

```
BugFactory factory = new BugFactory();
Bug bug = factory.newBug()
    .setOperatingSystem("WINDOWS")
    .setPlatform("PC")
    .setPriority("P1")
    .setProduct("Client")
    .setComponent("Admin Panel")
    .setSummary("Broken.")
    .setVersion("2.0")
    .setDescription("It doesn't work.")
    .createBug();
```

Now, this Bug can be added to the installation by passing it with the `ReportBug` RPC method, using an existing BugzillaConnector. The ID of the newly-created bug can be retrieved.

```
ReportBug report = new ReportBug(bug);
conn.executeMethod(report);
int id = report.getID();
```