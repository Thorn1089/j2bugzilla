# BugComments #

The `BugComments` BugzillaMethod allows clients to request a list of Comments made on a Bug in the Bugzilla installation.


# Getting a comment thread #

Assuming you already have set up a BugzillaConnector, you may construct an instance of a `BugComments` object to retrieve the comment thread on the bug report.

```
BugComments getComments = new BugComments(bug);
conn.executeMethod(getComments);
```

You may also pass the numerical ID of a bug in the `BugComments` constructor.

```
BugComments byID = new BugComments(1);
conn.executeMethod(byID);
```

Assuming the method completes successfully, you may now retrieve the comments from the `BugComments` object:

```
List<Comment> comments = getComments.getComments();
for(Comment comment : comments) {
    //Do something here
    System.out.println(comment.getText());
}
```