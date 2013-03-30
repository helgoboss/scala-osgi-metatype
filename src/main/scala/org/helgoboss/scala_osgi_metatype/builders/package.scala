package org.helgoboss.scala_osgi_metatype

/**
 * Contains builder objects for easily creating Scala OSGi metatypes.
 *
 * == Example ==
 *
 * The following example demonstrates how you can describe configuration parameters for a service.
 *
 * {{{
 *   import org.helgoboss.scala_osgi_metatype.builders._
 *
 *   val myObjectClass = ObjectClass(
 *     id = "org.helgoboss.my_service",
 *     name = "My configurable service",
 *     requiredAttributes = List(
 *       ElementaryAttribute[Int](id = "size", name = "Size", default = Some(5)),
 *       ElementaryAttribute[String](id = "user", name = "User", default = Some("root"))
 *     )
 *   )
 * }}}
 */
package object builders {

}
