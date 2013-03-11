package org.helgoboss.scala_osgi_metatype.adapters

import org.helgoboss.scala_osgi_metatype.interfaces.ListAttributeDefinition

/**
 * Provides the given Scala ListAttributeDefinition as an OSGi-compliant AttributeDefinition.
 */
class ListAttributeDefinitionAdapter[T](delegate: ListAttributeDefinition[T])
    extends AttributeDefinitionAdapter[T](delegate) {

  def getCardinality = delegate.sizeLimit match {
    case Some(x) => x
    case None => Int.MaxValue
  }

  lazy val getDefaultValue = {
    delegate.defaultValue map { v =>
      v.map { _.toString } toArray
    } orNull
  }
}