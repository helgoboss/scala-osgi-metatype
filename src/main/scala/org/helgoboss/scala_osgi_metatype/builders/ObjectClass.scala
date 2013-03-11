package org.helgoboss.scala_osgi_metatype.builders

import org.helgoboss.scala_osgi_metatype.interfaces.{ObjectClassDefinition, AttributeDefinition}

/**
 * Convenient builder for ObjectClassDefinitions.
 *
 * I've chosen not to create a named case class extending ObjectClassDefinition because it would cause name/type conflicts.
 * Moreover, the named class wouldn't add any benefits and would be just another class name to wonder about.
 */
object ObjectClass {
  /**
   * Builds an object class definition.
   *
   * @param id ID of the object class
   * @param name Name of the object class
   * @param description Description of the object class
   * @param requiredAttributes attribute definitions of required attributes for the object class
   * @param optionalAttributes attribute definitions of optional attributes for the object class
   */
  def apply(
    id: String,
    name: String = null,
    description: String = "",
    requiredAttributes: Traversable[AttributeDefinition[_]] = Nil,
    optionalAttributes: Traversable[AttributeDefinition[_]] = Nil) = {

    // Rename parameters in order to avoid name clashes. We don't rename the parameters because
    // they are part of the public interface as they shall be used as named parameters.
    val idParam = id
    val nameParam = name
    val descriptionParam = description

    // Build the definition
    new ObjectClassDefinition with ObjectClassDefinitionConvenience {
      protected def definition = this

      val id = idParam
      val name = Option(nameParam) getOrElse id
      val description = descriptionParam
      val requiredAttributeDefinitions = requiredAttributes
      val optionalAttributeDefinitions = optionalAttributes
      def getIcon(size: Int) = None
    }
  }
}