# GetAccessibleProducts #

The `GetAccessibleProducts` BugzillaMethod allows clients to query the Bugzilla installation for a list of Product objects which they may search or add bugs to.


# Retrieving the accessible Product list #

The `GetAccessibleProducts` RPC method requires no parameters, so simply pass a new instance to a pre-existing BugzillaConnector:

```
GetAccessibleProducts get = new GetAccessibleProducts();
conn.executeMethod(get);
int[] ids = get.getIDs();
```

The method returns an array of integer IDs for products which can be retrieved via GetProduct.