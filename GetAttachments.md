# GetAttachments #

The `GetAttachments` BugzillaMethod allows the retrieval of existing Attachment objects from a Bug report.


# Retrieving posted attachments #

Simply create a new `GetAttachments` object and pass it to the BugzillaConnector with either a Bug or a well-known ID for a report to retrieve the list of posted Attachment objects.

```
GetAttachments get = new GetAttachments(bug);
conn.executeMethod(get);
List<Attachment> attachments = get.getAttachments();

for(Attachment a : attachments) {
    System.out.println(a.getName() + " " + a.getMIMEType());
}
```