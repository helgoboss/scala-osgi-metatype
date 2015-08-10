**ATTENTION**: `scala-osgi-metatype` is now included in [Domino](https://github.com/domino-osgi/domino)! This repository is deprecated.

Scala OSGi Metatype
===================

Scala OSGi Metatype is basically the [OSGi Metatype API](http://blog.osgi.org/2011/03/metatypes.html) translated to idiomatic Scala. It contains interfaces, adapters and builders for easily building OSGi Metatype descriptions.

For those who don't know the OSGi Metatype API, it's a very practical addition to the OSGi ConfigurationAdmin API for describing configuration GUIs.

## Example

The following example demonstrates how you can describe configuration parameters for a service.

```scala
import org.helgoboss.scala_osgi_metatype.builders._

val myObjectClass = ObjectClass(
  id = "org.helgoboss.my_service",
  name = "My configurable service",
  requiredAttributes = List(
    ElementaryAttribute[Int](id = "size", name = "Size", default = Some(5)),
    ElementaryAttribute[String](id = "user", name = "User", default = Some("root"))
  )
)
```

## Documentation

- [Scaladoc](file:///D:/projects/helgoboss/github/todo/scala-osgi-metatype/target/site/scaladocs/index.html)

## Further reading

Scala OSGi Metatype is nicely integrated into the [Domino library](http://github.com/helgoboss/domino). You might want to have a look at its [user guide](http://www.helgoboss.org/projects/domino/user-guide).
