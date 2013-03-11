package org.helgoboss.scala_osgi_metatype.builders

import org.helgoboss.scala_osgi_metatype.interfaces.ObjectClassDefinition

/**
 * Adds some convenience methods to the given ObjectClassDefinition.
 */
trait ObjectClassDefinitionConvenience {
  protected def definition: ObjectClassDefinition

  /**
   * Returns both the attribute definitions of required and optional attributes for this object class.
   */
  lazy val allAttributeDefinitions = definition.requiredAttributeDefinitions ++ definition.optionalAttributeDefinitions

  /**
   * Builds a configuration map containing all the default values.
   */
  lazy val defaultConfig = {
    allAttributeDefinitions flatMap { definition =>
      definition.defaultValue map { value =>
        definition.id -> value
      }
    } toMap
  }
}
