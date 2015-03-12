# J2Bugzilla : The Java Bugzilla API #

## J2Bugzilla User Group ##
Discussions on the future of J2Bugzilla, as well as questions and other general back-and-forth, can now take place at [the Google Groups page](https://groups.google.com/forum/#!forum/j2bugzilla).

## Version 2.2.1 Now Available ##
Version 2.2.1 includes a patch to fix j2bugzilla's behavior when authenticating with newer Bugzilla versions that do not use cookies.

## J2Bugzilla 3.0 and Beyond ##
Looking to the future, J2Bugzilla's next major version will break from the existing API, which has been mostly unchanged since the alpha releases, to follow a facade pattern based off of SLF4J and similar APIs. There will be a single j2bugzilla-api JAR which specifies the high level behavior, and different bindings JARs for implementations corresponding to Bugzilla versions. This will allow us to implement the core functionality in different ways than just XML-RPC, for example -- support for BzAPI could be included. Development will also move to [Github](https://github.com/TomRK1089/J2Bugzilla-API). If you have suggestions or comments on the next API revision, please open issues there.

## Older News ##

### Version 2.2 Now Available ###
Version 2.2 includes new behavior to retrieve product-specific versions, and finally allows updates for resolution of a bug -- now you can programmatically close tickets! Additional integration tests have also been added.

### Version 2.1.1 Now Available ###
Version 2.1.1 includes a patch for a regression in the 2.1 release, where adding support for flags prevented new bugs from being created. Additional integration tests have been added for bug creation and modification to ensure as broad as possible compatibility across Bugzilla versions.

### Version 2.1 Now Available ###
Version 2.1 includes a few new features and bugfixes.
  * Flags can now be read on retrieved bugs.
  * The resolution of a bug can be updated.
  * Fixed a bug with UpdateBug where properties were not correctly sent to Bugzilla.
  * Fixed compatibility bugs with Bugzilla 3.6 for attachments and bug versions.
  * Various minor quality improvements pointed out by [Sonar](http://j2bugzilla.com:8080/sonar).
  * Lots more code coverage, including integration tests against all existing Bugzilla Landfill installations, to ensure maximum compatibility.

This version is available from the Maven central repositories, as well at the downloads page.

We are also pleased to announce that J2Bugzilla is being used by a sample [OSLC](http://open-services.net/) adapter created by the members of the [Eclipse Lyo](http://www.eclipse.org/lyo/) project to illustrate integrating Bugzilla with IBM's [Rational Team Concert](https://jazz.net/projects/rational-team-concert/?ref_content=ribbon). If you or your team are using J2Bugzilla in the wild, please email us your success stories! If your experience is a bit more tempermental, email us your complaints! ;)

## Description ##
This project aims to provide an API for Java clients to retrieve information from an existing Bugzilla information, and create or update new or existing bug reports. It is somewhat limited in scope by the underlying Bugzilla Remote Procedure Call interface, but is capable of the following:

  * Authenticate users to private installations
  * Search for bugs by almost any field
  * Add comments to existing bugs
  * View comments on existing bugs
  * Submit new bugs

This project can be integrated with other APIs to provide extended functionality. As an example, check out [Log4J-Bugzilla-Connector](http://code.google.com/p/log4j-bugzilla-connector/) which provides a custom appender for Log4J which uses this library to automatically file bug reports based on logging events. Another example would be automatically populating a knowledgebase for customer support based off of completed bugs with workarounds or links to patches.

This library uses the Apache XML-RPC library to communicate with Bugzilla via the XML-RPC specification and Bugzilla's xmlrpc.cgi interface. This requires the Apache libraries for the Java application, and that your Bugzilla installation has the required Perl modules installed.

J2Bugzilla is available via the Central Maven repositories:
```
<dependency>
  <groupId>com.j2bugzilla</groupId>
  <artifactId>j2bugzilla</artifactId>
  <version>2.2</version>
</dependency>
```

This API is targeted at Bugzilla installations of version 3.6 and up. If you encounter issues using the library, please report them at our own Bugzilla instance at http://j2bugzilla.com/tracker -- this also lets us dogfood the library against a current version of Bugzilla, so the more bugs in the system, the more issues we hope to solve!