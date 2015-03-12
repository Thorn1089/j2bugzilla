# BugSearch #

The `BugSearch` class allows you to find existing bugs in your Bugzilla installation, wherever they hide.


# Searching for bugs with BugSearch #

You can search for bugs in your installation by passing an appropriate `BugSearch` object to your BugzillaConnector, just like all other BugzillaMethod objects.

**Important** There is a bug in the implementation of BugSearch in version 1.1. To create a BugSearch object, one must provide one or more SearchQuery objects. However, SearchQuery is an internal class of BugSearch and requires an enclosing instance. This is fixed in the latest 2.0 release.

For example:

```
BugSearch search = new BugSearch(new SearchQuery(SearchLimiter.ALIAS, "carmen@sandiego.us"));
conn.executeMethod(realSearch);
```

Once you have executed your search, you can retrieve your results from the search object:

```
List<Bug> results = search.getSearchResults();
for(Bug bug : results) {
    //Do something
    System.out.println(bug);
}
```