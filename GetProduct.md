# GetProduct #

The `GetProduct` BugzillaMethod allows clients to retrieve a Product object representing one of the product categories in Bugzilla by its ID.


# Retrieving a product with GetProduct #

Currently, products can only be retrieved by ID. Later versions may support product listing and searching. For now, you may construct a new `GetProduct` object and pass it to an existing BugzillaConnector to retrieve a Product object with a matching unique ID.

```
GetProduct getProduct = new GetProduct(1);
conn.executeMethod(getProduct);
Product product = getProduct.getProduct();
```