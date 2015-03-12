# CommentBug #

The `CommentBug` BugzillaMethod allows clients to append additional comments to an existing bug report within Bugzilla.


# Adding new comments #

Using the `CommentBug` class is the same as any other BugzillaMethod -- you pass an instance to an existing BugzillaConnector object. You can create a new `CommentBug` object like so:

```
BugzillaConnector conn = ...
CommentBug newComment = new CommentBug(bug, "Great Scott!");
conn.executeMethod(newComment);
```

You can also specify a Bug via its unique numerical ID, as in the following example:

```
CommentBug byID = new CommentBug(1944, "Great Scott!");
```

In addition, you can pass a Comment object to the `CommentBug` constructor, instead of a `String`. This may be useful if you want to move comments from one bug, retrieved via BugComments, to another report.

```
CommentBug withComment = new CommentBug(bug, comment);
conn.executeMethod(withComment);
```