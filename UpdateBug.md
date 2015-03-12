# UpdateBug #

The `UpdateBug` BugzillaMethod allows clients to modify an existing Bug report in their installation programmatically.


# Updating an existing bug #

First, you must retrieve an existing bug, either via BugSearch or GetBug. This Bug can then be modified normally by changing its fields with the various setter methods provided by the Bug class. Finally, it can be submitted back to the installation using `UpdateBug`.

```
UpdateBug update = new UpdateBug(bug);
conn.executeMethod(update);
```