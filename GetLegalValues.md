# GetLegalValues #

The `GetLegalValues` BugzillaMethod allows clients to query the Bugzilla installation for the restricted set of inputs for particular fields, such as component, version, platform, etc. Since many of these fields are specific to your installation, you should retrieve them beforehand if intending to accept user input for filing or editing bugs.

# Retrieving a field's legal values #

Getting the set of values for a field is simple. Simply construct a new `GetLegalValues` object with the field enum you wish to query, as follows:

```
GetLegalValues get = new GetLegalValues(Fields.PLATFORM);
conn.executeMethod(get);
Set<String> values = get.getLegalValues();

for(String value : values) {
    System.out.println(value);
}
```

If you are querying a field which depends on a Product, such as the legal Components, you can specify this product as well, and receive a filtered set:

```
GetLegalValues get = new GetLegalValues(Fields.COMPONENT, new Product("Server"));
conn.executeMethod(get);
```