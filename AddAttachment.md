# AddAttachment #

The `AddAttachment` BugzillaMethod allows clients to post attachments to Bug reports on Bugzilla, by transforming a file into its Base64 representation.


# Posting an attachment #

First, you must create an Attachment object using the AttachmentFactory class.

```
AttachmentFactory factory = new AttachmentFactory();
Attachment attachment = factory.newAttachment()
    .setData( new byte[1024])//Transform your File to Base64
    .setMime("img/png")
    .setName("screenshot.png")
    .setSummary("A screenshot of the bug")
    .createAttachment();
```

Then, pass the Attachment with either a Bug or well-known bug ID to the `AddAttachment` constructor. After the attachment is posted, you can retrieve its unique ID.

```
AddAttachment add = new AddAttachment(attachment, bug);
conn.executeMethod(add);
int id = add.getID();
```