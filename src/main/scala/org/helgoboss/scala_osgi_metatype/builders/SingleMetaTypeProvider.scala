package org.helgoboss.scala_osgi_metatype.builders

import org.helgoboss.scala_osgi_metatype.interfaces.{ObjectClassDefinition, MetaTypeProvider}

/**
 * A meta type provider which provides an object class definition for one id. Ignores the language.
 */
class SingleMetaTypeProvider(definition: ObjectClassDefinition) extends MetaTypeProvider {
  val locales = Nil

  def getObjectClassDefinition(id: String, locale: Option[String]) = {
    if (id == definition.id) {
      definition
    } else {
      throw new IllegalArgumentException("Unknown ID [" + id + "]")
    }
  }
}