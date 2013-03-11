package org.helgoboss.scala_osgi_metatype.adapters

import org.helgoboss.scala_osgi_metatype.interfaces.ElementaryAttributeDefinition



/**
 * Provides the given Scala ElementaryAttributeDefinition as an OSGi-compliant AttributeDefinition.
 */
class ElementaryAttributeDefinitionAdapter[T](delegate: ElementaryAttributeDefinition[T])
  extends AttributeDefinitionAdapter[T](delegate) {

  def getCardinality = 0

  lazy val getDefaultValue = {
    if (delegate.defaultValue.isEmpty)
      null
    else
      delegate.defaultValue map { _.toString } toArray
  }
}