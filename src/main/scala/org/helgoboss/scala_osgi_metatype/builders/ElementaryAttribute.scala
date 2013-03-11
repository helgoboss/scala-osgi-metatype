package org.helgoboss.scala_osgi_metatype.builders

import org.helgoboss.scala_osgi_metatype.interfaces.{ValidationResult, ElementaryAttributeDefinition}

/**
 * Convenient builder for ElementaryAttributeDefinitions without advanced validation (type validation is done).
 */
object ElementaryAttribute {

  /**
   * Builds an elementary attribute definition.
   *
   * @param id Unique identity for the attribute
   * @param default Optional default for the attribute
   * @param name Name of the attribute
   * @param description Description of the attribute
   * @param options Optional list of option values that the attribute can take
   * @tparam T attribute type
   */
  def apply[T: ClassManifest](
      id: String,
      default: Option[T] = None,
      name: String = null,
      description: String = "",
      options: Option[Map[T, String]] = None) = {

    val idParam = id
    val nameParam = name
    val descriptionParam = description
    val optionsParam = options

    // Build the definition
    new ElementaryAttributeDefinition[T] {
      val id = idParam
      val name = Option(nameParam) getOrElse id
      val description = descriptionParam
      val options = optionsParam
      val defaultValue = default
      val valueType = classManifest[T].erasure
      def validate(value: T) = ValidationResult.NotValidated
    }
  }
}