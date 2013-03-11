package org.helgoboss.scala_osgi_metatype.adapters

import org.osgi.service.metatype.{ ObjectClassDefinition => JObjectClassDefinition, AttributeDefinition => JAttributeDefinition }
import org.helgoboss.scala_osgi_metatype.interfaces.{ListAttributeDefinition, ElementaryAttributeDefinition, ObjectClassDefinition}
import org.helgoboss.scala_osgi_metatype.builders.ElementaryAttribute

/**
 * Provides the given Scala ObjectClassDefinition as an OSGi-compliant ObjectClassDefinition.
 */
class ObjectClassDefinitionAdapter(delegate: ObjectClassDefinition) extends JObjectClassDefinition {
  def getAttributeDefinitions(filter: Int): Array[JAttributeDefinition] = {
    import JObjectClassDefinition._

    val list = filter match {
      case REQUIRED => delegate.requiredAttributeDefinitions
      case OPTIONAL => delegate.optionalAttributeDefinitions
      case ALL => delegate.requiredAttributeDefinitions ++ delegate.optionalAttributeDefinitions
    }

    if (list.isEmpty) {
      null
    } else {
      list map {
        case ed: ElementaryAttributeDefinition[_] => new ElementaryAttributeDefinitionAdapter(ed)
        case ld: ListAttributeDefinition[_] => new ListAttributeDefinitionAdapter(ld)
      } toArray
    }
  }

  def getDescription = delegate.description

  def getIcon(size: Int) = delegate.getIcon(size).orNull

  def getID = delegate.id

  def getName = delegate.name
}
